package com.demo.channel.kx;

import com.alibaba.fastjson.JSONObject;
import com.demo.channel.base.BaseChannelFunction;
import com.demo.channel.base.IChannelFunction;
import com.demo.channel.result.R;

/**
 * 科学渠道
 *
 * @author gaoyanzhen
 * @since 2022-08-01
 */
public class KxChannelFunction extends BaseChannelFunction implements IChannelFunction {

    @Override
    public R querySettleStatus(JSONObject json, String channel, String version, String service) {
        return R.success("放款完成");
    }

    public R revokeOfApply(JSONObject json, String channel, String version, String service) {
        return R.success("撤销成功");
    }
}
