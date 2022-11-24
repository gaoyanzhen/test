package singleton;

import com.demo.pattern.singleton.LazyInnerClassSingleton;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonTest {
    @Test
    public void testSingleton() {
        int n = 10;
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < n; i++) {
            int finalI = i;
            Thread thread = new Thread(() ->
                    System.out.println(finalI + "-" + LazyInnerClassSingleton.getInstance().hashCode())
            );
            executorService.submit(thread);
        }
        executorService.shutdown();
    }

    @Test
    public void testLazyInnerClassSingleton() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        int n = 10;
        for (int i = 0; i < n; i++) {
            Thread thread = new Thread(() ->
                    System.out.println(LazyInnerClassSingleton.getInstance().hashCode())
            );
            executorService.execute(thread);
        }
        executorService.shutdown();
    }
}
