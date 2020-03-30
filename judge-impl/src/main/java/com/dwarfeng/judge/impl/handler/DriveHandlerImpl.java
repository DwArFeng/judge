package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.handler.DriveHandler;
import com.dwarfeng.judge.stack.handler.DriveLocalCacheHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class DriveHandlerImpl implements DriveHandler {

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
    public void register() {
        lock.lock();
        try {
            if (!startFlag) {

                startFlag = true;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void unregister() {
        lock.lock();
        try {
            if (startFlag) {

                startFlag = false;
            }
        } finally {
            lock.unlock();
        }
    }
}
