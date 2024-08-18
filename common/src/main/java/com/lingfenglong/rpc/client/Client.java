package com.lingfenglong.rpc.client;

import com.lingfenglong.rpc.message.RpcRequest;
import com.lingfenglong.rpc.message.RpcResponse;

import java.io.IOException;

public interface Client {

    RpcResponse sendRequest(String host, int port, RpcRequest rpcRequest) throws IOException, ClassNotFoundException;
}
