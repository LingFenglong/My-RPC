package com.lingfenglong.rpc.service.provider;

public interface ServiceProvider {

    /**
     * 注册一个服务到 provider
     * @param service 要注册的服务
     */
    void registerService(Object service);

    /**
     * 获取某个接口名对应的服务代理对象
     * @param interfaceName 要获取服务代理对象的接口名
     * @return 服务代理对象
     */
    Object getService(String interfaceName);

    /**
     * 获取某个接口对应的服务代理对象
     * @param clazz 要获取服务代理对象的接口
     * @return 服务代理对象
     */
    Object getService(Class<?> clazz);
}
