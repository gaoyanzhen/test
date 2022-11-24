package com.demo.pattern.singleton;

public class LazyDoubleCheckSingleton {
    private static LazyDoubleCheckSingleton singleton;

    private LazyDoubleCheckSingleton() {
    }

    public static LazyDoubleCheckSingleton getInstance() {
        if (singleton == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (singleton == null) {
                    singleton = new LazyDoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }
}
