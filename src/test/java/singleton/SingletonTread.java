package singleton;

import com.demo.pattern.singleton.LazyInnerClassSingleton;

public class SingletonTread extends Thread {
    @Override
    public void run() {
//        System.out.println(LazySimpleSingleton.getInstance().hashCode());
//        System.out.println("单例对象hashCode："+LazyInnerClassSingleton.getInstance().hashCode());
        System.out.println(LazyInnerClassSingleton.getInstance().hashCode());
//        System.out.println(EnumSingleton.INSTANCE.hashCode());
    }
}
