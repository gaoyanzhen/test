package com.demo.pattern.proxy.cglib;

/**
 * 测试类
 *
 * @author gaoyanzhen
 * @since 2021-12-09
 */
public class MainCglibProxy {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        CglibHelloClass cglibHelloClass = (CglibHelloClass) cglibProxy.newProxyInstance(CglibHelloClass.class);
        cglibHelloClass.sayHello("isole");
        cglibHelloClass.sayByeBye("sss");
    }
}