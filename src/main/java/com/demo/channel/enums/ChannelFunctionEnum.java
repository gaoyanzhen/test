package com.demo.channel.enums;

import com.demo.channel.base.IFunction;
import com.demo.channel.base.funs.QueryAcceptResult;
import com.demo.channel.base.funs.SendAcceptInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 渠道方法枚举
 *
 * @author gaoyanzhen
 * @since 2022-07-22
 */
@Getter
@AllArgsConstructor
public enum ChannelFunctionEnum {

    /**
     * 进件接口
     */
    SEND_ACCEPT_INFO("sendAcceptInfo", "进件接口", SendAcceptInfo.class),

    /**
     * 进件状态查询
     */
    QUERY_ACCEPT_RESULT("queryAcceptResult", "进件状态查询", QueryAcceptResult.class);

    /**
     * 方法名
     */
    private final String method;

    /**
     * 方法描述
     */
    private final String methodDesc;

    /**
     * 枚举描述
     */
    private final Class<? extends IFunction> impl;

    /**
     * 通过枚举<code>code</code>获得枚举
     *
     * @param code
     * @return ChannelFunctionEnum
     */
    public static ChannelFunctionEnum getByMethod(String code) {
        for (ChannelFunctionEnum _enum : values()) {
            if (_enum.getMethod().equals(code)) {
                return _enum;
            }
        }
        return null;
    }

}
