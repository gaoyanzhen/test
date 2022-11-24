package com.demo.view;

import java.util.Arrays;

public class ArrayTest {
    public static double[] divide_1(double[] arr) {
        double[] result = new double[arr.length - 1];
        if (arr[0] == 0) {
            return result;
        }
        for (int i = 1; i < arr.length; i++) {
            result[i - 1] = arr[i] / arr[0];
        }
        return result;
    }

    public static void main(String[] args) {
        double[] arr = {11, 2, 3};
        double[] result = ArrayTest.divide_1(arr);
        System.out.println("result:" + Arrays.toString(result));
    }
}
