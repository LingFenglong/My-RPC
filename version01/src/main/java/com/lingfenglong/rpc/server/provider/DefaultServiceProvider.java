package com.lingfenglong.rpc.server.provider;

import com.lingfenglong.rpc.service.provider.ServiceProvider;

import java.util.HashMap;
import java.util.Map;

public class DefaultServiceProvider implements ServiceProvider {

    private final Map<String, Object> interfaceProvider;

    public DefaultServiceProvider() {
        interfaceProvider = new HashMap<>();
    }

    public void registerService(Object service) {
        for (var clazz : service.getClass().getInterfaces()) {
            interfaceProvider.put(clazz.getName(), service);
        }
    }

    public Object getService(String interfaceName) {
        return interfaceProvider.get(interfaceName);
    }

    public Object getService(Class<?> clazz) {
        return interfaceProvider.get(clazz.getName());
    }
}
