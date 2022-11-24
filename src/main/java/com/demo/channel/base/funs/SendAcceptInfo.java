package com.demo.channel.base.funs;

import com.alibaba.fastjson.JSONObject;
import com.demo.channel.base.IFunction;
import com.demo.channel.result.R;

/**
 * 进件实现
 *
 * @author gaoyanzhen
 * @since 2022-08-01
 */
public class SendAcceptInfo implements IFunction {
    public R v1(JSONObject json, String channel, String version, String service) {
        System.out.println("进件方法版本v1");
        return R.success("进件完成");
    }
}
