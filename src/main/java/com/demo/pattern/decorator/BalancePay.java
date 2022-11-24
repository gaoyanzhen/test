package com.demo.pattern.decorator;

import com.demo.pattern.chain.Pay;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * 结余户转还款户
 *
 * @author gaoyanzhen
 * @since 2022-11-04
 */
@Slf4j
public class BalancePay extends Pay {
    private Pay delegate;
    /**
     * 结余户金额
     */
    private BigDecimal charge;

    public BalancePay(Pay pay){
        this(pay, BigDecimal.ZERO);
    }

    public BalancePay(Pay pay, BigDecimal charge){
        this.delegate = pay;
        this.charge = charge;
    }

    @Override
    synchronized protected boolean doPay(BigDecimal amount) {
        BigDecimal bal = delegate.getBalance();
        if(bal.compareTo(amount) >= 0) {
            return delegate.pay(delegate.getLevel(), amount);
        }
        if (amount.compareTo(charge.add(bal)) <= 0) {
            // 转账金额
            BigDecimal transAmount = amount.subtract(bal);
            delegate.setBalance(bal.add(transAmount));
            this.charge = this.charge.subtract(transAmount);
            log.info("结余户转账到还款户金额：{}，余额：{}", transAmount, this.charge);
        }
        return delegate.pay(delegate.getLevel(), amount);
    }
}
