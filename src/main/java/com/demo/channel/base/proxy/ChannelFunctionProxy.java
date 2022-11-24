package com.demo.channel.base.proxy;

import com.demo.channel.base.IFunction;
import com.demo.channel.enums.ChannelFunctionEnum;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理处理类
 *
 * @author gaoyanzhen
 * @since 2022-08-01
 */
public class ChannelFunctionProxy<T> implements InvocationHandler {
    /**
     * 中间类持有委托类对象的引用,这里会构成一种静态代理关系
     */
    private T obj;

    /**
     * 有参构造器,传入委托类的对象
     *
     * @param obj 委托类的对象
     */
    public ChannelFunctionProxy(T obj) {
        this.obj = obj;
    }

    /**
     * 动态生成代理类对象,Proxy.newProxyInstance
     *
     * @return 返回代理类的实例
     */
    public Object newProxyInstance() {
        return Proxy.newProxyInstance(
                //指定代理对象的类加载器
                obj.getClass().getClassLoader(),
                //代理对象需要实现的接口，可以同时指定多个接口
                obj.getClass().getInterfaces(),
                //方法调用的实际处理者，代理对象的方法调用都会转发到这里
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (args.length != 4) {
            return method.invoke(obj, args);
        } else {
            String version = args[2].toString();
            ChannelFunctionEnum channelFunction = ChannelFunctionEnum.getByMethod(method.getName());
            IFunction function = channelFunction.getImpl().newInstance();
            Method inMethod = channelFunction.getImpl().getMethod(version, args.getClass());
            System.out.println("代理调用" + channelFunction.getImpl() + "." + inMethod.getName() + "方法");
            return inMethod.invoke(function, args);
        }
    }
}
