package test;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ForTest {
    public static void main(String[] args) {
//        ForTest test = new ForTest();
//        System.out.println("main:"+test.test());
        float t = 1.3f;
        String a = "hello";
        link(a);
        System.out.println(a);

    }

    public String test() {
        List<String> list = new ArrayList<String>();
        list.add("123");
        list.add("456");
        list.add("789");

        for (String str : list) {
            System.out.println(str + " start");
            if ("456".equals(str)) {
                return "213";
            }
            System.out.println(str + " end");
        }
        System.out.println("for循环执行完毕");
        return "999";
    }

    @Test
    public void testStr() {
        int month = 7;
        String strMonth = StringUtils.leftPad(month + "", 2, "0");
        System.out.println("strMonth:" + strMonth);
    }


    public static void link(String a) {
        a += "world";
    }
}
