package com.demo.channel.base;

import com.alibaba.fastjson.JSONObject;
import com.demo.channel.result.R;

/**
 * 渠道方法接口
 *
 * @author gaoyanzhen
 * @since 2022-08-01
 */
public interface IChannelFunction {
    /**
     * 进件
     *
     * @param json
     * @param channel
     * @param version
     * @param service
     * @return
     */
    R sendAcceptInfo(JSONObject json, String channel, String version, String service);

    /**
     * 查询进件结果
     *
     * @param json
     * @param channel
     * @param version
     * @param service
     * @return
     */
    R queryAcceptResult(JSONObject json, String channel, String version, String service);

    default R querySettleStatus(JSONObject json, String channel, String version, String service) {
        return R.success("sucess");
    }
}
