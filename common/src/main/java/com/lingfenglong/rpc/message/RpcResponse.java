package com.lingfenglong.rpc.message;

import java.io.Serializable;

/**
 * @param code  响应码
 * @param msg   响应消息
 * @param data  返回数据
 */
public record RpcResponse(
        int code,
        String msg,
        Object data
) implements Serializable {

    public static RpcResponse success(Object data) {
        return new RpcResponse(200, "success", data);
    }

    public static RpcResponse fail() {
        return new RpcResponse(500, "fail", null);
    }
}
