package com.demo.jvm.gc;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2021-12-06
 */
public class MinorGc {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM参数: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails  -XX:SurvivorRatio=8 -XX:+UseSerialGC
     * 备选 -XX:+PrintGCTimeStamps -XX:+PrintHeapAtGC
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        //出现一次Minor GC
        allocation4 = new byte[4 * _1MB];
    }

    /**
     * VM参数: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 -XX:+UseSerialGC
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        //直接分配在老年代
        allocation = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
        testAllocation();
    }
}
