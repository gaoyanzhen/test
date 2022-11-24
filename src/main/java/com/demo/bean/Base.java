package com.demo.bean;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2021-12-24
 */
public class Base {
    private String name = "父类";

    public Base() {
        tellName();
        printName();
    }

    public void tellName() {
        System.out.println("Base tell name :" + name);
    }

    public void printName() {
        System.out.println("Base print name :" + name);
    }
}
