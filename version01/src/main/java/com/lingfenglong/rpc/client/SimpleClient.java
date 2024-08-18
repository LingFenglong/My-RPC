package com.lingfenglong.rpc.client;

import com.lingfenglong.rpc.message.RpcRequest;
import com.lingfenglong.rpc.message.RpcResponse;

import java.io.*;
import java.net.Socket;

public class SimpleClient implements Client {

    public RpcResponse sendRequest(String host, int port, RpcRequest rpcRequest) throws IOException, ClassNotFoundException {
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream socketOutputStream = new ObjectOutputStream(socket.getOutputStream());
            socketOutputStream.writeObject(rpcRequest);
            socketOutputStream.flush();

            ObjectInputStream socketInputStream = new ObjectInputStream(socket.getInputStream());
            return (RpcResponse) socketInputStream.readObject();
        }
    }
}
