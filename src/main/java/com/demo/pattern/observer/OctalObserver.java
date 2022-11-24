package com.demo.pattern.observer;

/**
 * 观察者-8进制
 *
 * @author gaoyanzhen
 * @since 2022-07-28
 */
public class OctalObserver extends Observer {
    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
    }
}
