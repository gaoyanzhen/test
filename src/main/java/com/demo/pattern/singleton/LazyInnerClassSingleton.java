package com.demo.pattern.singleton;

/**
 * @author Administrator
 */
public class LazyInnerClassSingleton {

    /**
     * 私有化构造方法
     */
    private LazyInnerClassSingleton() {
        System.out.println("LazyInnerClassSingleton无参构造");
        if (LazyHolder.SINGLETON != null) {
            new RuntimeException("不允许创建多个实例");
        }
    }

    public static final LazyInnerClassSingleton getInstance() {
        return LazyHolder.SINGLETON;
    }

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class LazyHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static LazyInnerClassSingleton SINGLETON = new LazyInnerClassSingleton();
    }
}
