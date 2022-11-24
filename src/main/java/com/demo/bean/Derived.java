package com.demo.bean;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2021-12-24
 */
public class Derived extends Base {
    private String name = "子类";

    public Derived() {
        tellName();
        printName();
    }

    @Override
    public void tellName() {
        System.out.println("Derived tell name :" + name);
    }

    @Override
    public void printName() {
        System.out.println("Derived print name :" + name);
    }
}
