package fun;

import com.demo.bean.Apple;

import java.util.Optional;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2022-02-08
 */
public class OptionalTest {
    public static void main(String[] args) {
        Apple apple = null;
        Apple apple1 = new Apple("red", 2);
        Optional.ofNullable(apple).ifPresent(System.out::println);
        String color = Optional.ofNullable(apple).map(Apple::getColor).orElse("default");
        System.out.println("color=" + color);
        Optional.ofNullable(apple1).ifPresent(System.out::println);
        String color1 = Optional.ofNullable(apple1).map(Apple::getColor).orElse("default");
        ;
        System.out.println("color=" + color1);
    }
}
