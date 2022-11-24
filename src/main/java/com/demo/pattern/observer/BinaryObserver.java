package com.demo.pattern.observer;

/**
 * 观察者-二进制
 *
 * @author gaoyanzhen
 * @since 2022-07-28
 */
public class BinaryObserver extends Observer {
    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
    }
}
