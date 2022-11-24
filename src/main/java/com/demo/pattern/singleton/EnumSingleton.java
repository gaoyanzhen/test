package com.demo.pattern.singleton;

/**
 * 枚举单例
 *
 * @author Administrator
 */
public enum EnumSingleton {
    /**
     * 单例
     */
    INSTANCE;

    /**
     * 获取单例对象
     *
     * @return
     */
    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
