package com.demo.pattern.observer;

/**
 * 观察者抽象类
 *
 * @author gaoyanzhen
 * @since 2022-07-28
 */
public abstract class Observer {
    protected Subject subject;

    public abstract void update();
}
