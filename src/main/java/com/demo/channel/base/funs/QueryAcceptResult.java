package com.demo.channel.base.funs;

import com.alibaba.fastjson.JSONObject;
import com.demo.channel.base.IFunction;
import com.demo.channel.result.R;

/**
 * 进件结果查询实现
 *
 * @author gaoyanzhen
 * @since 2022-08-01
 */
public class QueryAcceptResult implements IFunction {
    public R v1(JSONObject json, String channel, String version, String service) {
        System.out.println("进件查询结果方法版本v1");
        return R.success("进件成功");
    }

}
