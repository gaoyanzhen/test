package com.demo.channel.kx;

import com.alibaba.fastjson.JSONObject;
import com.demo.channel.base.IFunctionChoice;
import com.demo.channel.kx.proxy.KxChannelFunctionProxy;
import com.demo.channel.result.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 接口方法跳转
 *
 * @author gaoyanzhen
 * @since 2022-08-02
 */
public class KxFunctionChoice implements IFunctionChoice {

    @Override
    public R goToFunction(String service, String version) {
        KxChannelFunctionProxy kxChannelFunctionProxy = new KxChannelFunctionProxy();
        KxChannelFunction proxy = (KxChannelFunction) kxChannelFunctionProxy.newProxyInstance(KxChannelFunction.class);
        try {
            Class proxyClass = proxy.getClass();
            Method method = proxyClass.getMethod(service);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return (R) method.invoke(service);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return R.fail("未知接口");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return R.fail("接口无访问权限");
        } catch (InvocationTargetException e) {
            return R.fail(e.getMessage());
        }
    }
}
