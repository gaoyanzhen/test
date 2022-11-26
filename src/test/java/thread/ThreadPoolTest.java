package thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * 线程池
 *
 * @author gaoyanzhen
 * @since 2022-11-03
 */
@Slf4j
public class ThreadPoolTest {

    ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat("ExecutorServiceShutdown-%d")
            .setDaemon(true)
            .build();

    @Test
    public void executorServiceShutdown() {
        log.info("同步执行 start");
        syncExecute1();
        syncExecute2();
        log.info("同步执行 over");
    }

    private void syncExecute1() {
        ExecutorService executorService = new ThreadPoolExecutor(2, 10, 60L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(), threadFactory);
        executorService.submit(() -> {
            log.info("异步执行1");
        });
        executorService.shutdown();
    }
    private void syncExecute2() {
        ExecutorService executorService = new ThreadPoolExecutor(2, 10, 60L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(), threadFactory);
        executorService.submit(() -> {
            log.info("异步执行2");
        });
        executorService.shutdown();
    }
}
