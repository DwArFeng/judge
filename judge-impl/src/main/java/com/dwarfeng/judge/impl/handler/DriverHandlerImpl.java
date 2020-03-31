package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.exception.DriverException;
import com.dwarfeng.judge.stack.exception.UnsupportedDriverTypeException;
import com.dwarfeng.judge.stack.handler.DriveLocalCacheHandler;
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

import static com.dwarfeng.judge.stack.handler.DriveLocalCacheHandler.DriveContext;

@Component
public class DriverHandlerImpl implements DriverHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverHandlerImpl.class);

    @Autowired
    private List<Driver> drivers;
    @Autowired
    private DriveLocalCacheHandler localCacheHandler;

    private final Lock lock = new ReentrantLock();
    private boolean startFlag = false;

    @Override
    public boolean isRegistered() {
        lock.lock();
        try {
            return startFlag;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void register() throws HandlerException {
        lock.lock();
        try {
            LOGGER.info("注册所有驱动...");
            if (!startFlag) {
                List<LongIdKey> sectionKeys = localCacheHandler.getSectionKeys();
                List<DriverInfo> driverInfos = new ArrayList<>();
                // 获取所有驱动信息。
                for (LongIdKey sectionKey : sectionKeys) {
                    DriveContext driveContext = localCacheHandler.getDriveContext(sectionKey);
                    if (Objects.isNull(driveContext)) {
                        throw new DriverException("无法在本地缓存中找到有效的驱动上下文: " + sectionKey);
                    }
                    driverInfos.addAll(driveContext.getDriverInfos());
                }
                Map<DriverInfo, Driver> driverMap = new HashMap<>();
                // 确认所有的驱动信息全部存在。
                for (DriverInfo driverInfo : driverInfos) {
                    Driver driver = drivers.stream().filter(d -> d.supportType(driverInfo.getType())).findAny()
                            .orElseThrow(() -> new UnsupportedDriverTypeException(driverInfo.getType()));
                    driverMap.put(driverInfo, driver);
                }
                // 注册所有驱动。
                boolean successFlag = true;
                for (Map.Entry<DriverInfo, Driver> entry : driverMap.entrySet()) {
                    DriverInfo driverInfo = entry.getKey();
                    Driver driver = entry.getValue();
                    try {
                        driver.register(driverInfo);
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
                startFlag = true;
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
    public void unregister() throws HandlerException {
        lock.lock();
        try {
            if (startFlag) {
                LOGGER.info("解除注册所有驱动...");
                for (Driver driver : drivers) {
                    driver.unregisterAll();
                }
                startFlag = false;
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
