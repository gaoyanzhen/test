package com.demo.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式-目标对象
 *
 * @author gaoyanzhen
 * @since 2022-07-28
 */
public class Subject {
    private List<Observer> observers = new ArrayList<Observer>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
