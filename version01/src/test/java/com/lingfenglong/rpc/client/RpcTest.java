package com.lingfenglong.rpc.client;

import com.lingfenglong.rpc.client.proxy.ClientProxy;
import com.lingfenglong.rpc.entity.User;
import com.lingfenglong.rpc.message.RpcRequest;
import com.lingfenglong.rpc.message.RpcResponse;
import com.lingfenglong.rpc.server.SimpleRpcServer;
import com.lingfenglong.rpc.server.service.impl.UserServiceImpl;
import com.lingfenglong.rpc.server.provider.DefaultServiceProvider;
import com.lingfenglong.rpc.service.UserService;
import com.lingfenglong.rpc.service.provider.ServiceProvider;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class RpcTest {
    @Test
    void rpcTest() {
        ServiceProvider serviceProvider = new DefaultServiceProvider();
        serviceProvider.registerService(new UserServiceImpl());
        new SimpleRpcServer(serviceProvider).startByAsync(8080);

        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8080);
        UserService userService = clientProxy.proxy(UserService.class);
        User user = userService.findById(1);

        System.out.println("client: " + user);
    }

    @Test
    void server() throws IOException {
        ServiceProvider serviceProvider = new DefaultServiceProvider();
        serviceProvider.registerService(new UserServiceImpl());

        new SimpleRpcServer(serviceProvider).start(8080);
    }

    @Test
    void client() throws IOException, ClassNotFoundException {
        RpcRequest request = new RpcRequest(
                UserService.class.getName(),
                "findById",
                new Object[]{1},
                new Class[]{Integer.class}
        );

        RpcResponse response = new SimpleClient().sendRequest("127.0.0.1", 8080, request);

        System.out.println(response);
    }

    @Test
    void clientProxy() {
        ClientProxy clientProxy = new ClientProxy("localhost", 8080);
        UserService userService = clientProxy.proxy(UserService.class);

        User user = userService.findById(7788);

        System.out.println(user);
    }
}