package com.demo.thread;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;

import java.util.concurrent.CountDownLatch;

/**
 * 继承Thread类并重写run的方法
 * @author Administrator
 */
@Slf4j
public class MyThread extends Thread {
    private int flag;
    private CountDownLatch countDownLatch;
    public MyThread(int flag){
        this.flag = flag;
    }
    public MyThread(int flag, CountDownLatch countDownLatch){
        this.flag = flag;
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {
        try {
            if(flag == 3){
                Thread.sleep(1000);
                log.info("{}，我是异常线程", flag);
                throw new RuntimeException("数据异常");
            }
            log.info("{}，我是正常线程",flag);
            if(flag == 1) {
                Thread.sleep(2000);
            } else {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            new RuntimeException("中断异常");
        } catch (RuntimeException e) {
            new RuntimeException("执行异常");
        } finally {
            if(countDownLatch != null){
                countDownLatch.countDown();
            }
        }
    }
}
