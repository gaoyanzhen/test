package collection;

import com.demo.bean.Apple;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author gaoyanzhen
 * @since 2022-01-11
 */
public class ArrayTest {
    public static void main(String[] args) {
        Apple[] apples = new Apple[2];
        Apple apple1 = new Apple();
        apple1.setColor("red");
        apple1.setWeight(20);
        apples[0] = apple1;
        Apple apple2 = new Apple();
        apple2.setColor("red");
        apple2.setWeight(25);
        apples[1] = apple2;
        System.out.println("apples = " + Arrays.toString(apples));
    }

}
