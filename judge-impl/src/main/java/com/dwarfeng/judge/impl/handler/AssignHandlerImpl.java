package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.AssignInfo;
import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.exception.DriverException;
import com.dwarfeng.judge.stack.handler.AssignHandler;
import com.dwarfeng.judge.stack.handler.AssignLocalCacheHandler;
import com.dwarfeng.judge.stack.handler.Driver;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class AssignHandlerImpl implements AssignHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssignHandlerImpl.class);

    private final SectionMaintainService sectionMaintainService;
    private final AssignLocalCacheHandler localCacheHandler;

    private final Lock lock = new ReentrantLock();
    private final Set<Driver> usedDrivers = new HashSet<>();
    private boolean onlineFlag = false;

    public AssignHandlerImpl(SectionMaintainService sectionMaintainService, AssignLocalCacheHandler localCacheHandler) {
        this.sectionMaintainService = sectionMaintainService;
        this.localCacheHandler = localCacheHandler;
    }

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
                List<Section> sections = sectionMaintainService.lookup(
                        SectionMaintainService.ENABLED, new Object[]{}).getData();
                // 注册所有驱动成功标志。
                boolean successFlag = true;
                // 获取所有驱动信息。
                for (Section section : sections) {
                    AssignInfo assignInfo = localCacheHandler.getAssignInfo(section.getKey());
                    if (Objects.isNull(assignInfo)) {
                        throw new DriverException("无法在本地缓存中找到有效的驱动上下文: " + section.getKey());
                    }
                    if (!registerDriver(assignInfo)) {
                        successFlag = false;
                    }
                }
                if (successFlag) {
                    LOGGER.info("所有驱动信息注册成功");
                } else {
                    LOGGER.warn("至少一条驱动信息注册失败，请查看警报日志以了解详细原因");
                }
                onlineFlag = true;
            }
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        } finally {
            lock.unlock();
        }
    }

    private boolean registerDriver(AssignInfo assignInfo) {
        boolean successFlag = true;
        Map<DriverInfo, Driver> driverMap = assignInfo.getDriverMap();
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
        return successFlag;
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
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        } finally {
            lock.unlock();
        }
    }
}
