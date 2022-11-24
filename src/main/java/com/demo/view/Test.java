package com.demo.view;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt(1000));
        System.out.println(System.currentTimeMillis());
    }
}
