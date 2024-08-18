package com.lingfenglong.rpc.message;

import java.io.Serializable; /**
 * @param interfaceName 接口名
 * @param methodName    方法名
 * @param params        参数
 * @param paramTypes    参数类型
 */
public record RpcRequest(
        String interfaceName,
        String methodName,
        Object[] params,
        Class<?>[] paramTypes
) implements Serializable {

}
