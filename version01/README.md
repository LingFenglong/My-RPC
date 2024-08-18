# RPC

## 2024年8月9日

JDK Socket 版本

使用套接字进行客户端与服务器的连接

[UserServiceImpl.java](src%2Fmain%2Fjava%2Fcom%2Flingfenglong%2Frpc%2Fserver%2Fservice%2Fimpl%2FUserServiceImpl.java)
具体服务的实现

[DefaultServiceProvider.java](src%2Fmain%2Fjava%2Fcom%2Flingfenglong%2Frpc%2Fserver%2Fprovider%2FDefaultServiceProvider.java)
用于提供服务注册

[SimpleRpcServer.java](src%2Fmain%2Fjava%2Fcom%2Flingfenglong%2Frpc%2Fserver%2FSimpleRpcServer.java)
服务器的具体实现

[SimpleClient.java](src%2Fmain%2Fjava%2Fcom%2Flingfenglong%2Frpc%2Fclient%2FSimpleClient.java)
客户端IO的实现

[ClientProxy.java](src%2Fmain%2Fjava%2Fcom%2Flingfenglong%2Frpc%2Fclient%2Fproxy%2FClientProxy.java)
客户端代理，用于代理接口，封装HTTP请求

## 流程

1. 客户端代理需要调用的服务接口（这里是UserService）。
2. 客户端通过代理返回的对象，直接调用所需方法（这里是findById）。
3. 服务端通过ServiceProvider，获取服务的具体实现（事先已经注册过）。
4. 服务端调用实际的方法（这里是UserServiceImpl.findById()）。
5. 服务端使用RpcResponse封装查询出的对象
6. 客户端解构并使用返回的查询对象。

## 代理的具体内容：

1. 获得相关请求数据。
   1. 接口名
   2. 方法名
   3. 方法参数
   4. 方法参数类型
2. 封装成RpcRequest对象。
3. 创建Socket，连接ServerSocket。
4. 使用ObjectOutputStream发送RpcRequest。
5. 使用ObjectInputStream接收RpcResponse。
6. 将RpcResponse.data转化为具体对象（这里是User）。