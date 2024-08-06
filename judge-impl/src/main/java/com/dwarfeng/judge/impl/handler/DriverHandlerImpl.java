package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.exception.UnsupportedDriverTypeException;
import com.dwarfeng.judge.stack.handler.Driver;
import com.dwarfeng.judge.stack.handler.DriverHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class DriverHandlerImpl implements DriverHandler {

    private final List<DriverProvider> driverProviders;

    public DriverHandlerImpl(List<DriverProvider> driverProviders) {
        this.driverProviders = Optional.ofNullable(driverProviders).orElse(Collections.emptyList());
    }

    @Override
    public Driver find(String type) throws HandlerException {
        return driverProviders.stream().filter(dp -> dp.supportType(type)).map(DriverProvider::provide)
                .findAny().orElseThrow(() -> new UnsupportedDriverTypeException(type));
    }
}
