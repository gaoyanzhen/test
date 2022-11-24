package com.demo.pattern.proxy.jdk;

/**
 * 委托类接口
 *
 * @author gaoyanzhen
 * @since 2021-12-09
 */
public interface IHelloService {
    /**
     * 方法1
     *
     * @param userName
     * @return
     */
    String sayHello(String userName);

    /**
     * 方法2
     *
     * @param userName
     * @return
     */
    String sayByeBye(String userName);
}
