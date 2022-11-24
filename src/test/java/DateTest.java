import net.sf.cglib.core.Local;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Date测试类
 *
 * @author gaoyanzhen
 * @since 2022-01-05
 */
public class DateTest {
    @Test
    public void localDate1() {
        LocalDate currentDate = LocalDate.now();
        LocalDate minus3 = currentDate.minusDays(3);
        String out = String.format("currentDate=%s,minus3=%s", currentDate, minus3);
        System.out.println(out);
    }

    @Test
    public void localDate2() {
        long dateLong = 1625564216000l;
        String date = "2021-07-06T09:36:56";
        String date2 = "2021-07-06 09:36:56";
        LocalDateTime dateTime = LocalDateTime.parse(date);
        LocalDateTime dateTime1 = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateLong), ZoneOffset.UTC);
        System.out.println(dateTime);
        System.out.println(dateTime1);
    }

    @Test
    public void localDate3() {
        long dateLong = 1625564216000l;
        String date = "2021-07-06T09:36:56";
        String date2 = "2021-07-06 09:36:56";
        LocalDateTime dateTime = LocalDateTime.parse(date);
        dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        LocalDateTime dateTime1 = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateLong), ZoneOffset.UTC);
        System.out.println(dateTime);
        System.out.println(dateTime1);
    }

    @Test
    public void localDate4() {
        String date = "202107";
        System.out.println(date.substring(4, 6));
        System.out.println(date.substring(0, 4));
    }


    @Test
    public void localDate5() {
        String format = "yyyyMMddHHmmssSSSSSS";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        System.out.println(format + ": " + dtf.format(LocalDateTime.now()));
    }

}
