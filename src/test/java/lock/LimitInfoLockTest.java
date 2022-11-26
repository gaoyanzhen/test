package lock;

import com.demo.lock.LimitInfoDao;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2022-11-25
 */
@Slf4j
public class LimitInfoLockTest {
    LimitInfoDao limitInfoDao = new LimitInfoDao();
    CountDownLatch countDownLatch = new CountDownLatch(2);

    @Test
    public void reentrantLock(){
        LimitInfoDao limitInfoDao = new LimitInfoDao();
        BigDecimal amount = new BigDecimal("10");
        limitInfoDao.reduceLimit(1, amount);
        limitInfoDao.recoverLimit(1, amount);
    }
    @Test
    public void multiThreadLock() throws InterruptedException {
        int limitId = 1;
        BigDecimal amount = new BigDecimal("10");
        Random random = new Random();
        ExecutorService executorService = Executors.newCachedThreadPool();
        int threadCount = 1;
        List<Callable<Integer>> taskList = new ArrayList<>(threadCount);
        Stopwatch stopWatch = Stopwatch.createStarted();
        for(int i=0; i<threadCount; i++){
//            reduceTaskList.add(new ReduceTask(limitId, new BigDecimal(random.nextInt(10))));
            taskList.add(new ReduceTask(limitId, amount));
            taskList.add(new RecoverTask(limitId, amount));
        }
        executorService.invokeAll(taskList);
        executorService.shutdown();
        countDownLatch.await();
        stopWatch.stop();
        log.info("执行时长：{} 毫秒", stopWatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private class ReduceTask implements Callable<Integer> {
        /**
         * 额度Id
         */
        private int limitId;
        /**
         * 交易金额
         */
        private BigDecimal transAmount;

        public ReduceTask(int limitId, BigDecimal transAmount){
            this.limitId = limitId;
            this.transAmount = transAmount;
        }
        @Override
        public Integer call() throws Exception {
            int res = 0;
            try {
                res = limitInfoDao.reduceLimitSync(limitId, transAmount) ? 1 : 0;
            } finally {
                countDownLatch.countDown();
            }
            return res;
        }
    }

    private class RecoverTask implements Callable<Integer> {
        /**
         * 额度Id
         */
        private int limitId;
        /**
         * 交易金额
         */
        private BigDecimal transAmount;

        public RecoverTask(int limitId, BigDecimal transAmount){
            this.limitId = limitId;
            this.transAmount = transAmount;
        }
        @Override
        public Integer call() throws Exception {
            int res = 0;
            try {
                res = limitInfoDao.recoverLimitSync(limitId, transAmount) ? 1 : 0;
            } finally {
                countDownLatch.countDown();
            }
            return res;
        }
    }


}
