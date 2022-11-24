package collection;

import com.demo.bean.Apple;
import com.demo.collection.Weekday;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;

/**
 * 枚举集与映射
 *
 * @author gaoyanzhen
 * @since 2022-04-18
 */
public class EnumSetDemo {
    public static void main(String[] args) {
        EnumSet<Weekday> always = EnumSet.allOf(Weekday.class);
        EnumSet<Weekday> never = EnumSet.noneOf(Weekday.class);
        EnumSet<Weekday> workday = EnumSet.range(Weekday.MONDAY, Weekday.FRIDAY);
        EnumSet<Weekday> mwf = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
        Weekday[] all = Weekday.values();
        System.out.printf("all:%s\n", Arrays.asList(all));
        System.out.printf("never:%s\n", never);
        System.out.printf("workday:%s\n", workday);
        System.out.printf("mwf:%s\n", mwf);
        System.out.println(workday.contains(Weekday.MONDAY));
        EnumMap<Weekday, Apple> enumMap = new EnumMap<>(Weekday.class);
        enumMap.put(Weekday.MONDAY, new Apple("red", 21));
        enumMap.put(Weekday.TUESDAY, new Apple("red", 22));
        System.out.printf("enumMap:%s\n", enumMap);

    }
}
