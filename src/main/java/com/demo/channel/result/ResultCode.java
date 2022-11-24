
package com.demo.channel.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务代码枚举
 *
 * @author
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 业务异常
     */
    FAILURE(400, "业务异常"),

    /**
     * 404 没找到请求
     */
    NOT_FOUND(404, "404 没找到请求"),
    /**
     * 参数校验失败
     */
    PARAM_VALID_ERROR(400, "参数校验失败");

    /**
     * code编码
     */
    final int code;
    /**
     * 中文信息描述
     */
    final String message;

}
