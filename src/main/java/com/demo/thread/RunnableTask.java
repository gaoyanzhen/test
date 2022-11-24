package com.demo.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * Runnable接口的run方法
 *
 * @author gaoyanzhen
 * @since 2021-11-18
 */
@Slf4j
public class RunnableTask implements Runnable {
    public int flag = 0;

    public RunnableTask(){
    }
    public RunnableTask(int flag){
        this.flag = flag;
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
            new RuntimeException("sleep异常");
        }
    }
}
