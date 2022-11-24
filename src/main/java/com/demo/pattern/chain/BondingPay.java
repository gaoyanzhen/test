package com.demo.pattern.chain;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * 担保公司支付
 *
 * @author gaoyanzhen
 * @since 2022-07-28
 */
@Slf4j
public class BondingPay extends Pay {

    public BondingPay(int level) {
        this.level = level;
    }

    @Override
    protected boolean doPay(BigDecimal amount) {
        synchronized (balance) {
            if (amount.compareTo(balance) > 0) {
                log.error("担保公司代偿扣款失败，账户余额：{}，划扣金额：{}，余额不足", balance, amount);
                return false;
            }

            balance = balance.subtract(amount);
            log.info("担保公司代偿扣款成功，划扣金额：{}，余额：{}", amount, balance);
        }
        return true;
    }
}
