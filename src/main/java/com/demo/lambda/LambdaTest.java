package com.demo.lambda;

import com.demo.bean.Apple;

import java.util.function.Supplier;

/**
 * @author Administrator
 */
public class LambdaTest {
    public static void main(String[] args) {
        Supplier<Apple> apple = Apple::new;
        int a = 1;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int a = 2;
                System.out.println(a);
            }
        };
        Runnable runnable1 = () -> {
//            int a = 5;
            System.out.println(a);
        };
    }
}
