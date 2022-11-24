package com.demo.pattern.singleton;

/**
 * @author gaoyanzhen
 */
public class HungrySingleton {
    private static final HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return singleton;
    }
}
