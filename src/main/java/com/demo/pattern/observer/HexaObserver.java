package com.demo.pattern.observer;

/**
 * 观察者-16进制
 *
 * @author gaoyanzhen
 * @since 2022-07-28
 */
public class HexaObserver extends Observer {
    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase());
    }
}
