package thread;

import com.demo.thread.CallableTask;
import com.demo.thread.MyThread;
import com.demo.thread.RunnableTask;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 简单线程实现测试类
 *
 * @author gaoyanzhen
 * @since 2022-11-14
 */
@Slf4j
public class SimpleThreadTest {
    @Test
    public void threadCountDownLatchTest(){
        // 任务个数
        int threadCount = 4;
        // 计数
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        List<Thread> threadList = new ArrayList<>(threadCount);
        MyThread thread1 = new MyThread(1, countDownLatch);
        MyThread thread2 = new MyThread(2, countDownLatch);
        MyThread thread3 = new MyThread(3, countDownLatch);
        MyThread thread4 = new MyThread(4, countDownLatch);
        threadList.add(thread1);
        threadList.add(thread2);
        threadList.add(thread3);
        threadList.add(thread4);

        //读取当前CPU个数，决定线程池的大小
        final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
        log.info("CPU个数：{}", POOL_SIZE);

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("threadThreadPoolTest-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(threadCount, POOL_SIZE, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), threadFactory);
        Stopwatch stopwatch = Stopwatch.createStarted();
        threadList.stream().forEach(thread -> executorService.execute(thread));
        executorService.shutdown();
        try {
            countDownLatch.await();
            log.info("执行完毕");
        } catch (InterruptedException e) {
            log.error("中断异常，{}", e.getMessage());
        } catch (Exception e) {
            log.error("系统异常，{}", e.getMessage(), e);
        }
        stopwatch.stop();
        // 执行时间（单位：秒） %n 为换行
        log.info("执行时长：{} 毫秒", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
    @Test
    public void threadThreadPoolTest(){
        // 任务个数
        int threadCount = 4;
        // 计数
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        List<Thread> threadList = new ArrayList<>(threadCount);
        MyThread thread1 = new MyThread(1, countDownLatch);
        MyThread thread2 = new MyThread(2, countDownLatch);
        MyThread thread3 = new MyThread(3, countDownLatch);
        MyThread thread4 = new MyThread(4, countDownLatch);
        threadList.add(thread1);
        threadList.add(thread2);
        threadList.add(thread3);
        threadList.add(thread4);

        //读取当前CPU个数，决定线程池的大小
        final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
        log.info("CPU个数：{}", POOL_SIZE);

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("threadThreadPoolTest-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(threadCount, POOL_SIZE, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), threadFactory);
        List<Future<?>> futureList = new ArrayList<>(threadCount);
        Stopwatch stopwatch = Stopwatch.createStarted();
        log.info("执行开始");
        threadList.stream().forEach(thread -> futureList.add(executorService.submit(thread)));

        futureList.stream().forEach(future -> {
            try {
                log.info("result:{}", future.get());
            } catch (InterruptedException e) {
                log.error("中断异常，{}", e.getMessage());
            } catch (ExecutionException e) {
                log.error("系统异常，{}", e.getMessage(), e);
                return;
            }
        });
        executorService.shutdown();
        log.info("执行完毕");
        stopwatch.stop();
        // 执行时间（单位：秒） %n 为换行
        log.info("执行时长：{} 毫秒", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
    @Test
    public void threadTest(){
        MyThread thread = new MyThread(1);
        thread.start();
    }
    @Test
    public void runnableTaskExeTest(){
        RunnableTask runnableTask1 = new RunnableTask(2);
        Thread thread1 = new Thread(runnableTask1);
        thread1.start();
        RunnableTask runnableTask2 = new RunnableTask(1);
        Thread thread2 = new Thread(runnableTask2);
        thread2.start();
    }
    @Test
    public void runnableTaskTest(){
        RunnableTask runnableTask = new RunnableTask();
        Thread thread = new Thread(runnableTask);
        System.out.println("threadName=" + Thread.currentThread().getName() + ",threadId=" + Thread.currentThread().getId() + ",begin =" + System.currentTimeMillis());
        thread.start();
        System.out.println("threadName=" + Thread.currentThread().getName() + ",threadId=" + Thread.currentThread().getId() + ",end =" + System.currentTimeMillis());
    }
    @Test
    public void callableTaskTest(){
        FutureTask<String> futureTask = new FutureTask(new CallableTask());
        FutureTask<String> futureTask2 = new FutureTask(new CallableTask(-1));
        Thread thread = new Thread(futureTask);
        System.out.println("threadName=" + Thread.currentThread().getName() + ",threadId=" + Thread.currentThread().getId() + ",begin =" + System.currentTimeMillis());
        thread.start();
        try {
            String result = futureTask.get();
            System.out.println("result: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("threadName=" + Thread.currentThread().getName() + ",threadId=" + Thread.currentThread().getId() + ",end =" + System.currentTimeMillis());
    }
    @Test
    public void callableTaskInvokeAllTest() {
        // flag=3测试异常
        CallableTask task1 = new CallableTask(1);
        CallableTask task2 = new CallableTask(2);
        CallableTask task3 = new CallableTask(3);
        CallableTask task4 = new CallableTask(4);

        List<CallableTask> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
//        taskList.add(task3);
        taskList.add(task4);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("CallableTaskInvokeAll-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(2, 10, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), threadFactory);
        List<Future<String>> futureList = null;

        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            futureList = executorService.invokeAll(taskList);
        } catch (InterruptedException e) {
            log.info("系统异常，{}", e.getMessage(), e);
        }
        try {
            futureList.stream().forEach(future -> {
                String result = "";
                String msg = "系统异常";
                Throwable throwable = null;
                try {
                    result = future.get();
                    log.info("{}，执行完成", result);
                    // 执行完2之后就中断
                    if("1".equals(result)){
                        log.info("中断线程池");
                        executorService.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    result = "-1";
                    throwable = e;
                    log.info("中断异常，{}", e.getMessage());
                } catch (ExecutionException e) {
                    throwable = e.getCause();
                    result = "-1";
                    msg = throwable.getMessage();
                    log.info("执行异常，关闭线程池，{}", e.getMessage());
                    executorService.shutdownNow();
                }
                if("-1".equals(result)){
                    if(throwable == null){
                        throw new RuntimeException(msg);
                    }  else {
                        throw new RuntimeException(msg, throwable);
                    }
                }
            });
        } catch (RuntimeException e) {
            log.info("回滚事务，原因：{}", e.getMessage());
        } catch (Exception e) {
            log.info("回滚事务，原因：{}", e.getMessage());
        }
        executorService.shutdown();
        stopwatch.stop();
        // 执行时间（单位：秒） %n 为换行
        log.info("执行时长：{} 毫秒", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
    @Test
    public void callableTaskSubmitTest() {
        CallableTask task1 = new CallableTask(1);
        CallableTask task2 = new CallableTask(2);
        CallableTask task3 = new CallableTask(3);
        CallableTask task4 = new CallableTask(4);

        List<CallableTask> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        ExecutorService executorService = new ThreadPoolExecutor(2, 10, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        try {
            List<Future<String>> futureList = new ArrayList<>(taskList.size());
            taskList.stream().forEach(task -> {
                Future<String> future = executorService.submit(task);
                futureList.add(future);
            });
            executorService.shutdown();

            futureList.stream().forEach(future -> {
                String result = "";
                try {
                    result = future.get();
                    log.info("{}，执行完成", result);
                } catch (InterruptedException e) {
                    result = "-1";
                    log.info("InterruptedException - {}", e.getMessage());
                } catch (ExecutionException e) {
                    if(e.getCause() instanceof RuntimeException){
                        log.info("测试异常 - {}", e.getMessage());
                    }
                    result = "-1";
                    log.info("ExecutionException - {}", e.getMessage());
                }
                if("-1".equals(result)){
                    log.info("返回结果异常，{}", result);
                    throw new RuntimeException("数据异常");
                }
            });

        } catch (RuntimeException e) {
            log.info("事务回滚");
        }
    }
    @Test
    public void callableTaskSubmitParamTest() {
        CallableTask task1 = new CallableTask(1);
        CallableTask task2 = new CallableTask(2);
        CallableTask task3 = new CallableTask(3);
        CallableTask task4 = new CallableTask(4);

        List<CallableTask> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        ExecutorService executorService = new ThreadPoolExecutor(2, 10, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        try {
            List<Future<String>> futureList = new ArrayList<>(taskList.size());
            taskList.stream().forEach(task -> {
                Future<String> future = executorService.submit(task);
                futureList.add(future);
            });

            futureList.stream().forEach(future -> {
                String result = "";
                try {
                    result = future.get();
                    log.info("{}，执行完成", result);
                } catch (InterruptedException e) {
                    result = "-1";
                    log.info("InterruptedException，{}", e.getMessage());
                } catch (ExecutionException e) {
                    if(e.getCause() instanceof RuntimeException){
                        log.info("手动抛出异常，{}", e.getMessage());
                    }
                    result = "-1";
                    log.info("ExecutionException，{}", e.getMessage());
                }
                if("-1".equals(result)){
                    log.info("返回结果异常，{}", result);
                    throw new RuntimeException("数据异常");
                }
            });

        } catch (RuntimeException e) {
            log.info("事务回滚");
        }
    }

}
