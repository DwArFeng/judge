package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.exception.UnsupportedDriverTypeException;
import com.dwarfeng.judge.stack.handler.Driver;
import com.dwarfeng.judge.stack.handler.DriverHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DriverHandlerImpl implements DriverHandler {

    @SuppressWarnings("FieldMayBeFinal")
    @Autowired(required = false)
    private List<DriverProvider> driverProviders = new ArrayList<>();

    @Override
    public Driver find(String type) throws HandlerException {
        return driverProviders.stream().filter(dp -> dp.supportType(type)).map(DriverProvider::provide)
                .findAny().orElseThrow(() -> new UnsupportedDriverTypeException(type));
    }
}
