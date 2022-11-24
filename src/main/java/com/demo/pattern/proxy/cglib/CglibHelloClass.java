package com.demo.pattern.proxy.cglib;

/**
 * 委托类,是一个简单类
 *
 * @author gaoyanzhen
 * @since 2021-12-09
 */
public class CglibHelloClass {
    /**
     * 方法1
     *
     * @param userName
     * @return
     */
    public String sayHello(String userName) {
        System.out.println(userName + " sayHello");
        return userName + " sayHello";
    }

    public String sayByeBye(String userName) {
        System.out.println(userName + " sayHello");
        return userName + " sayByeBye";
    }

}