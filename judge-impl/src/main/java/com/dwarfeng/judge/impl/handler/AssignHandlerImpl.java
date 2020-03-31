package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.exception.DriverException;
import com.dwarfeng.judge.stack.handler.AssignHandler;
import com.dwarfeng.judge.stack.handler.AssignLocalCacheHandler;
import com.dwarfeng.judge.stack.handler.AssignLocalCacheHandler.AssignContext;
import com.dwarfeng.judge.stack.handler.Driver;
import com.dwarfeng.judge.stack.handler.DriverHandler;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class AssignHandlerImpl implements AssignHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssignHandlerImpl.class);

    @Autowired
    private DriverHandler driverHandler;
    @Autowired
    private AssignLocalCacheHandler localCacheHandler;

    private final Lock lock = new ReentrantLock();
    private final Set<Driver> usedDrivers = new HashSet<>();
    private boolean onlineFlag = false;

    @Override
    public boolean isOnline() {
        lock.lock();
        try {
            return onlineFlag;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void online() throws HandlerException {
        lock.lock();
        try {
            LOGGER.info("指派任务上线...");
            if (!onlineFlag) {
                List<LongIdKey> sectionKeys = localCacheHandler.getSectionKeys();
                List<DriverInfo> driverInfos = new ArrayList<>();
                // 获取所有驱动信息。
                for (LongIdKey sectionKey : sectionKeys) {
                    AssignContext assignContext = localCacheHandler.getAssignContext(sectionKey);
                    if (Objects.isNull(assignContext)) {
                        throw new DriverException("无法在本地缓存中找到有效的驱动上下文: " + sectionKey);
                    }
                    driverInfos.addAll(assignContext.getDriverInfos());
                }
                Map<DriverInfo, Driver> driverMap = new HashMap<>();
                // 确认所有的驱动信息全部存在。
                for (DriverInfo driverInfo : driverInfos) {
                    Driver driver = driverHandler.find(driverInfo.getType());
                    driverMap.put(driverInfo, driver);
                }
                // 注册所有驱动。
                boolean successFlag = true;
                for (Map.Entry<DriverInfo, Driver> entry : driverMap.entrySet()) {
                    DriverInfo driverInfo = entry.getKey();
                    Driver driver = entry.getValue();
                    try {
                        driver.register(driverInfo);
                        usedDrivers.add(driver);
                    } catch (Exception e) {
                        successFlag = false;
                        LOGGER.warn("驱动信息 " + driverInfo + " 注册失败，将忽略此条注册信息", e);
                    }
                }
                if (successFlag) {
                    LOGGER.info("所有驱动信息注册成功");
                } else {
                    LOGGER.warn("至少一条驱动信息注册失败，请查看警报日志以了解详细原因");
                }
                onlineFlag = true;
            }
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void offline() throws HandlerException {
        lock.lock();
        try {
            if (onlineFlag) {
                LOGGER.info("解除注册所有驱动...");
                for (Iterator<Driver> iterator = usedDrivers.iterator(); iterator.hasNext(); ) {
                    Driver driver = iterator.next();
                    driver.unregisterAll();
                    iterator.remove();
                }
                onlineFlag = false;
            }
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        } finally {
            lock.unlock();
        }
    }
}
