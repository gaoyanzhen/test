package com.demo.channel.base;

import com.alibaba.fastjson.JSONObject;
import com.demo.channel.result.R;

/**
 * 接口功能选择
 *
 * @author gaoyanzhen
 * @since 2022-08-01
 */
public interface IFunctionChoice {


    /**
     * 接口功能选择
     *
     * @param version 接口版本号
     * @param service 接口名称
     * @return
     */
    R goToFunction(String service, String version);
}
