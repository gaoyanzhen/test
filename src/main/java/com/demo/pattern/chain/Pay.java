package com.demo.pattern.chain;

import java.math.BigDecimal;

/**
 * 责任链模式-支付抽象类
 *
 * @author gaoyanzhen
 * @since 2022-07-28
 */
public abstract class Pay implements IPay {

    /**
     * 默认级别
     */
    protected int level = LEVEL_3;

    /**
     * 账户余额
     */
    protected BigDecimal balance;

    /**
     * 责任链中的下一个元素
     */
    protected Pay next;

    public void setBalance(BigDecimal balance) {
        if (balance == null) {
            this.balance = BigDecimal.ZERO;
        } else {
            this.balance = balance;
        }
    }

    public BigDecimal getBalance(){
        return this.balance;
    }

    public int getLevel(){
        return this.level;
    }

    public void setNext(Pay nextPay) {
        this.next = nextPay;
    }

    @Override
    public boolean pay(int level, BigDecimal amount) {
        boolean result = false;
        if (level >= this.level) {
            result = doPay(amount);
        }

        if (!result && next != null) {
            result = next.pay(level, amount);
        }
        return result;
    }


    /**
     * 还款支付
     *
     * @param amount
     * @return
     */
    abstract protected boolean doPay(BigDecimal amount);
}
