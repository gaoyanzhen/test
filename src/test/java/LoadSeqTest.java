import com.demo.bean.Derived;

/**
 * 类的初始化顺序：
 * 加载 -> 连接 -> 初始化
 * 代码书写顺序加载父类静态变量和父类静态代码块
 * 代码书写顺序加载子类静态变量和子类静态代码块
 * 父类非静态变量（父类实例成员变量）
 * 父类非静态代码块
 * 父类构造函数
 * 子类非静态变量（子类实例成员变量）
 * 子类非静态代码块
 * 子类构造函数
 *
 * @author gaoyanzhen
 * @since 2021-12-24
 */
public class LoadSeqTest {
    public static void main(String[] args) {
        Derived derived = new Derived();
    }
}
