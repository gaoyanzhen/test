package com.demo.pattern.chain;

import java.math.BigDecimal;

/**
 * 责任链模式-支付抽象类
 *
 * @author gaoyanzhen
 * @since 2022-07-28
 */
public interface IPay {
    /**
     * 支付优先级从高到低：5->4->3->2->1
     */
    int LEVEL_1 = 1;
    int LEVEL_2 = 2;
    int LEVEL_3 = 3;
    int LEVEL_4 = 4;
    int LEVEL_5 = 5;

    /**
     * 自动扣款
     *
     * @param level  指定最低级别
     * @param amount 扣款金额
     * @return
     */
    boolean pay(int level, BigDecimal amount);

}
