package com.demo.channel.kx.funs;

import com.demo.channel.base.IFunction;
import com.demo.channel.result.R;

/**
 * 放款结果查询实现
 *
 * @author gaoyanzhen
 * @since 2022-08-01
 */
public class QuerySettleStatus implements IFunction {
    public R v1() {
        return R.success("放款成功");
    }

}
