package com.dwarfeng.judge.impl.handler.provider.groovy;

import com.dwarfeng.judge.sdk.handler.provider.AbstractProviderSession;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 模拟提供器会话。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GroovyProviderSession extends AbstractProviderSession {

    private final Processor processor;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public GroovyProviderSession(Processor processor) {
        this.processor = processor;
    }

    @Override
    protected void doOpenSession() throws Exception {
        lock.writeLock().lock();
        try {
            processor.openSession();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    protected List<Map<String, Object>> doLookupData(String preset, Object[] objs) throws Exception {
        lock.readLock().lock();
        try {
            return processor.lookupData(preset, objs);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    protected void doCloseSession() throws Exception {
        lock.writeLock().lock();
        try {
            processor.closeSession();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public String toString() {
        return "GroovyProviderSession{" +
                "processor=" + processor +
                ", lock=" + lock +
                '}';
    }
}
