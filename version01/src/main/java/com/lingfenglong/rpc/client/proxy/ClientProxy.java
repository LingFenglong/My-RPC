package com.lingfenglong.rpc.client.proxy;

import com.lingfenglong.rpc.client.SimpleClient;
import com.lingfenglong.rpc.message.RpcRequest;
import com.lingfenglong.rpc.message.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Optional;

public class ClientProxy implements InvocationHandler {
    private final String host;
    private final int port;

    private final SimpleClient simpleClient = new SimpleClient();

    public ClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object target, Method method, Object[] params) throws Throwable {
        RpcRequest request = new RpcRequest(
                method.getDeclaringClass().getName(),
                method.getName(),
                params,
                method.getParameterTypes()
        );

        RpcResponse response = simpleClient.sendRequest(host, port, request);

        return Optional.ofNullable(response)
                .filter(r -> r.code() == 200)
                .map(RpcResponse::data)
                .orElseThrow(() -> new RuntimeException("请求失败！"));
    }

    @SuppressWarnings("unchecked")
    public <T> T proxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }

}
