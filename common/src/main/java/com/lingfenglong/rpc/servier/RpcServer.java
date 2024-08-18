package com.lingfenglong.rpc.servier;

import java.io.IOException;

public interface RpcServer {
    /**
     * 开启服务器
     * @param port 服务器要监听的端口号
     */
    void start(int port) throws IOException;

    /**
     * 异步开启服务器
     * @param port 服务器要监听的端口号
     */
    default void startByAsync(int port) {
        new Thread(() -> {
            try {
                start(port);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }


    /**
     * 终止服务器
     */
    void stop();
}
