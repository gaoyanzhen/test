package com.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * FutureTask方式
 *
 * @author gaoyanzhen
 * @since 2022-11-14
 */
@Slf4j
public class CallableTask implements Callable<String> {
    public int flag = 0;

    public CallableTask(){
    }
    public CallableTask(int flag){
        this.flag = flag;
    }
    @Override
    public String call() throws Exception {
        if(flag == 3){
            Thread.sleep(1000);
            log.error("{}，我是异常线程", flag);
            System.err.println(flag +"，我是异常线程");
            throw new RuntimeException("数据异常");
//            return "-1";
        }
        if(flag == 1) {
            Thread.sleep(5000);
        } else {
            Thread.sleep(1000);
        }
        log.info("{}，我是正常线程",flag);
        return flag+ "";
    }
}
