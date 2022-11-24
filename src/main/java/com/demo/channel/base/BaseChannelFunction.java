package com.demo.channel.base;

import com.alibaba.fastjson.JSONObject;
import com.demo.channel.base.proxy.ChannelFunctionProxy;
import com.demo.channel.result.R;

import java.lang.reflect.Proxy;

/**
 * 渠道方法默认实现类
 *
 * @author gaoyanzhen
 * @since 2022-08-01
 */
public class BaseChannelFunction implements IChannelFunction {


    @Override
    public R sendAcceptInfo(JSONObject json, String channel, String version, String service) {
        return R.success("进件完成");
    }

    @Override
    public R queryAcceptResult(JSONObject json, String channel, String version, String service) {
        return R.success("进件成功");
    }
}
