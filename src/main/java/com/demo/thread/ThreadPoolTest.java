package com.demo.thread;

import com.demo.thread.factory.NamedThreadFactory;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2022-11-23
 */
@Slf4j
public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolTest test = new ThreadPoolTest();
        test.test();
    }

    public void test() {
        log.info("同步执行 start");
        asynExecute1();
        asynExecute2();
        log.info("同步执行 over");
    }

    private void asynExecute1() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("asynExecute1-%d").build();
//        ThreadFactory threadFactory = new NamedThreadFactory("asynExecute1");
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 10L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());
        executorService.submit(() -> {
            log.info("异步执行1");
        });
        executorService.shutdown();
    }
    private void asynExecute2() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("asynExecute2-%d").build();
//        ThreadFactory threadFactory = new NamedThreadFactory("asynExecute2");
        ExecutorService executorService = new ThreadPoolExecutor(1, 10, 10L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());
        executorService.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("异步执行2");
        });
        executorService.shutdown();
    }
}
