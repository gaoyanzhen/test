package com.demo.algorithm.recursion;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2021-11-18
 */
public class RecursiveTest {
    public static final StringBuffer msg = new StringBuffer();

    public static void main(String[] args) {
        long n = 4;
        long result = factorialTailRecursive(n);
        System.out.println(msg + " = " + result);
    }

    public static long factorialRecursive(long n) {
        msg.append(n);
        if (n != 1) {
            msg.append("*");
        }
        return n == 1 ? 1 : n * factorialRecursive(n - 1);
    }

    public static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }

    public static long factorialHelper(long acc, long n) {
        msg.append(acc).append("\n");
        return n == 1 ? acc : factorialHelper(acc * n, n - 1);
    }
}
