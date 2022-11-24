package collection;

import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.Arrays;
import java.util.Map;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2022-01-05
 */
public class LinkedCaseInsensitiveMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedCaseInsensitiveMap();
        map.put("ABC", "123");
        map.put("aBC", "456");
        map.put("AbC", "789");
        map.put("ABC", "100");
        map.put("ABC", "200");
        System.out.println("key=ABC,value=" + map.get("ABC"));
        System.out.println("key=aBC,value=" + map.get("aBC"));
        System.out.println("key=AbC,value=" + map.get("AbC"));
    }
}
