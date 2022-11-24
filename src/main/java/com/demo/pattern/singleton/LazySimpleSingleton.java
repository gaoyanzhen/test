package com.demo.pattern.singleton;

public class LazySimpleSingleton {
    private static LazySimpleSingleton singleton;

    private LazySimpleSingleton() {
    }

    public synchronized static LazySimpleSingleton getInstance() {
        if (singleton == null) {
            singleton = new LazySimpleSingleton();
        }
        return singleton;
    }
}
