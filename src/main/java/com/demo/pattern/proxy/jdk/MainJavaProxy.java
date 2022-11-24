package com.demo.pattern.proxy.jdk;

/**
 * 测试动态代理类
 *
 * @author gaoyanzhen
 * @since 2021-12-09
 */
public class MainJavaProxy {
    public static void main(String[] args) {
        JavaProxyInvocationHandler proxyInvocationHandler = new JavaProxyInvocationHandler(new HelloService());
        IHelloService helloService = (IHelloService) proxyInvocationHandler.newProxyInstance();
        helloService.sayByeBye("paopao");
        helloService.sayHello("yupao");
    }
}
