package pattern;

import com.demo.pattern.observer.BinaryObserver;
import com.demo.pattern.observer.HexaObserver;
import com.demo.pattern.observer.OctalObserver;
import com.demo.pattern.observer.Subject;
import org.junit.jupiter.api.Test;

/**
 * 观察者模式测试类
 *
 * @author gaoyanzhen
 * @since 2022-07-28
 */
public class ObserverPatternDemo {
    @Test
    public void test() {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
