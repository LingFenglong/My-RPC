package com.lingfenglong.rpc.server;

import com.lingfenglong.rpc.message.RpcRequest;
import com.lingfenglong.rpc.message.RpcResponse;
import com.lingfenglong.rpc.service.provider.ServiceProvider;
import com.lingfenglong.rpc.servier.RpcServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleRpcServer implements RpcServer {

    private final ServiceProvider serviceProvider;

    public SimpleRpcServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void start(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("服务器已启动");

            while (true) {
                // 监听
                Socket socket = serverSocket.accept();

                new Thread(() -> {
                    try {
                        ObjectInputStream socketInputStream = new ObjectInputStream(socket.getInputStream());
                        RpcRequest rpcRequest = (RpcRequest) socketInputStream.readObject();
                        RpcResponse rpcResponse = getResponse(rpcRequest);

                        ObjectOutputStream socketOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        socketOutputStream.writeObject(rpcResponse);
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        }
    }

    private RpcResponse getResponse(RpcRequest rpcRequest) {
        Object service = serviceProvider.getService(rpcRequest.interfaceName());

        try {
            // 反射调用
            Method method = service.getClass().getMethod(rpcRequest.methodName(), rpcRequest.paramTypes());
            System.out.println("invoke " + method.getName());

            Object result = method.invoke(service, rpcRequest.params());
            System.out.println("return " + result);

            return RpcResponse.success(result);

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return RpcResponse.fail();
        }
    }

    @Override
    public void stop() {

    }
}
