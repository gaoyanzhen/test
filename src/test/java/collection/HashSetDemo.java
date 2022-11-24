package collection;

import com.demo.bean.Apple;

import java.util.*;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2021-12-30
 */
public class HashSetDemo {
    public static void main(String[] args) {
        retain();
    }

    public static void print() {
        Set<String> set = new HashSet<>(5);
        set.add("Amy");
        set.add("Bob");
        set.add("Carl");
        set.add("David");

        System.out.println("set:" + set.toString());
    }

    /**
     * 交集
     */
    public static void retain() {
        Set<String> aSet = new HashSet<>();
        aSet.add("1");
        aSet.add("2");
        aSet.add("4");
        aSet.add("5");
        Set<String> bSet = new HashSet<>();
        bSet.add("3");
        bSet.add("4");
        bSet.add("6");
        bSet.add("8");

        Set<String> cSet = new HashSet<>(aSet);
        System.out.println("retainAll:" + cSet.retainAll(bSet));
        System.out.println("aSet:" + aSet);
        System.out.println("bSet:" + bSet);
        System.out.println("cSet:" + cSet);
    }
}
