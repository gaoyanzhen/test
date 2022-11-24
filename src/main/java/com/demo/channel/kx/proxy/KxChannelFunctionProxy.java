package com.demo.channel.kx.proxy;

import com.demo.channel.base.BaseChannelFunction;
import com.demo.channel.base.IChannelFunction;
import com.demo.channel.base.IFunction;
import com.demo.channel.base.proxy.ChannelFunctionProxy;
import com.demo.channel.enums.ChannelFunctionEnum;
import com.demo.channel.kx.KxChannelFunction;
import com.demo.channel.result.R;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理处理类
 *
 * @author gaoyanzhen
 * @since 2022-08-01
 */
public class KxChannelFunctionProxy<T> implements MethodInterceptor {
    /**
     * CGLIB 增强类对象，代理类对象是由 Enhancer 类创建的，
     * Enhancer 是 CGLIB 的字节码增强器，可以很方便的对类进行拓展
     */
    private Enhancer enhancer = new Enhancer();

    /**
     * @param obj    被代理的对象
     * @param method 代理的方法
     * @param args   方法的参数
     * @param proxy  CGLIB方法代理对象
     * @return cglib生成用来代替Method对象的一个对象，使用MethodProxy比调用JDK自身的Method直接执行方法效率会有提升
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (args.length != 4) {
            return proxy.invokeSuper(obj, args);
        } else {
            String version = args[2].toString();
            // 自定义方法
            if (method.getDeclaringClass().equals(KxChannelFunction.class)) {
                return proxy.invokeSuper(obj, args);
            } else {
                ChannelFunctionEnum channelFunction = ChannelFunctionEnum.getByMethod(method.getName());
                if (channelFunction == null) {
                    return R.fail("未知接口");
                }
                IFunction function = channelFunction.getImpl().newInstance();
                Method inMethod;
                try {
                    inMethod = channelFunction.getImpl().getMethod(version, method.getParameterTypes());
                } catch (NoSuchMethodException e) {
                    return R.fail("不存在" + method.getName() + "接口" + version + "版本");
                }
                System.out.println("代理调用" + channelFunction.getImpl() + "." + inMethod.getName() + "方法");
                return inMethod.invoke(function, args);
            }
        }
    }

    /**
     * 使用动态代理创建一个代理对象
     *
     * @param c
     * @return
     */
    public <T> T newProxyInstance(Class<T> c) {
        /**
         * 设置产生的代理对象的父类,增强类型
         */
        enhancer.setSuperclass(c);
        /**
         * 定义代理逻辑对象为当前对象，要求当前对象实现 MethodInterceptor 接口
         */
        enhancer.setCallback(this);
        /**
         * 使用默认无参数的构造函数创建目标对象,这是一个前提,被代理的类要提供无参构造方法
         */
        return (T) enhancer.create();
    }
}
