package collection;

import com.demo.bean.Apple;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * HashMapTest
 *
 * @author gaoyanzhen
 * @since 2021-12-30
 */
public class HashMapTest {
    @Test
    public void hashMapObject() {
        Map<String, Apple> map = new HashMap<>(5);
        ArrayList<Apple> list = new ArrayList<>();

        Apple apple1 = new Apple();
        apple1.setColor("red");
        apple1.setWeight(20);
        map.put("a1", apple1);

        Apple apple2 = new Apple();
        apple2.setColor("green");
        apple2.setWeight(25);
        map.put("a2", apple2);

        System.out.println("keys:" + map.keySet());
    }

    @Test
    public void hashMapString() {
        HashMap<String, String> map = new HashMap<>(64);
        map.put("123", "123");
        map.put("123", "null");
        map.put(null, "null123");
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
    }
}
