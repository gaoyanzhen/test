package com.demo.oom;

import java.util.Arrays;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2021-12-01
 */
public class RuntimeConstantPoolOOm {
    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        String str4 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str3 = "计算机软件";
        System.out.println(str3 == str1);
        System.out.println(str3.intern() == str1);
        System.out.println(str3.intern() == str3);
        System.out.println(str4.intern() == str1);
        System.out.println(str4.intern() == str4);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
