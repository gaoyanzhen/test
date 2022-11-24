package collection;

import com.demo.bean.Apple;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2022-01-11
 */
public class ListTest {

    @Test
    public void print() {
        List<Apple> apples = new ArrayList<>();
        List<Apple> applesLinked = new LinkedList<>();
        Apple apple1 = new Apple();
        apple1.setColor("red");
        apple1.setWeight(20);
        apples.add(apple1);
        applesLinked.add(apple1);

        Apple apple2 = new Apple();
        apple2.setColor("red");
        apple2.setWeight(25);
        apples.add(apple2);
        applesLinked.add(apple2);

        System.out.println("apples = " + apples);
        System.out.println("applesLinked = " + applesLinked);
    }

    @Test
    public static void group() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("1");
        list.add("2");

        Map<String, Long> collect = list.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        for (String key : collect.keySet()) {
            System.out.println(key + "=" + collect.get(key));
        }
    }

    public void collect() {
        List<String> list = Collections.nCopies(50, "hello");
        System.out.println(list.size());
        list.forEach(System.out::println);
    }

    @Test
    public void doubleForEach() {
        List<String> list = new ArrayList<>();
        list.add("A1");
        list.add("A2");
        list.add("B1");
        list.add("B2");
        list.add("B3");
        list.add("C1");

        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            int i = listIterator.nextIndex();
            String str = listIterator.next();
            ListIterator<String> subListIterator = list.listIterator(i + 1);

            while (subListIterator.hasNext()) {
                String subStr = subListIterator.next();
                System.out.println("i=" + i + ", sub-" + subStr);
                if (subStr.startsWith(str.substring(0, 1))) {
                    listIterator.next();
                } else {
                    System.out.println(str.substring(0, 1) + "结束");
                    break;
                }
            }
            System.out.println("i=" + i + ", parent-" + str);
        }
    }

    @Test
    public void doubleForEach_2() {
        List<String> list = new ArrayList<>();
        list.add("A1");
        list.add("A2");
        list.add("B1");
        list.add("B2");
        list.add("B3");
        list.add("C1");

        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            int i = listIterator.nextIndex();
            String str = listIterator.next();
            System.out.println("i=" + i + ", parent-" + str);

            String subStr = null;
            if (listIterator.hasNext()) {
                subStr = list.get(i + 1);
                if (!subStr.startsWith(str.substring(0, 1))) {
                    System.out.println(str.substring(0, 1) + "结束");
                }
            } else {
                System.out.println(str.substring(0, 1) + "结束");
            }
        }
    }

}
