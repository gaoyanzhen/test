package pattern;

import com.demo.pattern.chain.*;
import com.demo.pattern.decorator.BalancePay;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 责任链模式测试类
 *
 * @author gaoyanzhen
 * @since 2022-07-28
 */
@Slf4j
public class ChainOfResponsibilityPattern {
    @Test
    public void chainPay() {
        // 银行卡 100
        Pay bankCardPay = new BankCardPay(Pay.LEVEL_1);
        bankCardPay.setBalance(new BigDecimal("100"));
        bankCardPay = new BalancePay(bankCardPay, new BigDecimal("100"));
        // 押金 200
        Pay depositPay = new DepositPay(Pay.LEVEL_2);
        depositPay.setBalance(new BigDecimal("200"));
        // 担保公司 1000
        Pay bondingPay = new BondingPay(Pay.LEVEL_3);
        bondingPay.setBalance(new BigDecimal("1000"));

        bankCardPay.setNext(depositPay);
        depositPay.setNext(bondingPay);

        BigDecimal amount = new BigDecimal("150");
        System.out.println("扣款执行顺序：");

        boolean payResult = bankCardPay.pay(Pay.LEVEL_3, amount);
        if (payResult) {
            log.info("扣款成功，扣款金额：{}！", amount);
        } else {
            log.info("扣款失败，扣款金额：{}！", amount);
        }
    }

    @Test
    public void multiThreadChainPay() {
        // 100
        BankCardPay bankCardPay = new BankCardPay(Pay.LEVEL_3);
        // 200
        DepositPay depositPay = new DepositPay(Pay.LEVEL_2);
        // 1000
        BondingPay bondingPay = new BondingPay(Pay.LEVEL_1);
        bankCardPay.setNext(depositPay);
        depositPay.setNext(bondingPay);

        int threadCount = 5;
        ExecutorService executorService = Executors.newCachedThreadPool();
        Random random = new Random();
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            executorService.execute(() -> {
                int amountInt = random.nextInt(100);
                BigDecimal amount = new BigDecimal(amountInt + "");
//                log.info("["+ Thread.currentThread().getName() + "]扣款执行顺序：");
                boolean payResult = bankCardPay.pay(Pay.LEVEL_3, amount);
                if (payResult) {
                    log.info("序号：{}，金额：" + amount + "，扣款成功！", finalI);
                } else {
                    log.info("序号：{}，金额：" + amount + "，扣款失败！", finalI);
                }
            });
        }
    }
}
