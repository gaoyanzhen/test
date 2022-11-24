package thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 周期性线程池
 *
 * @author gaoyanzhen
 * @since 2022-11-03
 */
public class ScheduledThreadPoolTest {
    ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
    ScheduledExecutorService poolFactory = Executors.newScheduledThreadPool(5, new ThreadFactoryBuilder()
            .setNameFormat("ScheduledThreadPool-%d")
            .setDaemon(true)
            .setPriority(2)
            .build());

    @Test
    public void scheduleAtFixedRate() throws InterruptedException {
        System.out.println(Thread.currentThread() +", scheduleAtFixedRate-start:" + new Date());
        // scheduleAtFixedRate(command, 5, 2, second)，
        // 第一次开始执行是5s后，假如执行耗时1s，那么下次开始执行是7s后，再下次开始执行是9s后
        // 如果执行耗时>等待时间，那么将会在任务执行完成后立马开始执行下一轮任务。
        // 如果执行耗时<等待时间，那么将会在任务执行完成后等待【等待时间-执行耗时】，然后再执行下一轮任务
        poolFactory.scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + ", scheduleAtFixedRate:" + new Date());
        }, 5, 2, TimeUnit.SECONDS);
        Thread.sleep(100000L);
    }


    @Test
    public void scheduleWithFixedDelay() throws InterruptedException {
        System.out.println("scheduleAtFixedRate-start:" + new Date());
        // scheduleWithFixedDelay(command, 5, 2, second)，
        // 第一次开始执行是5s后，假如执行耗时1s，执行完成时间是6s后，那么下次开始执行是8s后，再下次开始执行是11s后
        // 它会在任务执行完成后再等待2秒
        pool.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ",scheduleAtFixedRate:" + new Date());
        }, 5, 2, TimeUnit.SECONDS);
        Thread.sleep(100000L);
    }

    @Test
    public void schedule() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ",schedule-start:" + new Date());
        // 在给定的延迟后执行一次
        poolFactory.schedule(() -> {
            System.out.println(Thread.currentThread() + ",schedule:" + new Date());
        }, 2, TimeUnit.SECONDS);
        Thread.sleep(100000L);
    }

    @Test
    public void scheduledFuture() throws InterruptedException, ExecutionException {
        System.out.println(Thread.currentThread() +",schedule-future-start:" + LocalDateTime.now());
        // 在给定的延迟后执行一次，并返回结果
        ScheduledFuture<LocalDateTime> future = poolFactory.schedule(() -> {
            System.out.println(Thread.currentThread() + ",schedule-future:" + LocalDateTime.now());
            return LocalDateTime.now();
        }, 2, TimeUnit.SECONDS);
        System.out.println("schedule-future:" + future.get());
    }

    @Test
    public void scheduleSubmitFuture() throws InterruptedException, ExecutionException {
        System.out.println("schedule-start:" + new Date());
        // 把要返回的对象传递过去，在执行完成后再返回
        Map<String,String> result = new HashMap<String, String>();
        Future<Map<String,String>> future2 = pool.submit(() -> {
            try {
                Thread.sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            result.put("result", "任务执行完成啦");
            System.out.println("schedule:" + new Date());
        }, result);
        System.out.println("执行完成，返回指定的值:" + future2.get());
    }


}
