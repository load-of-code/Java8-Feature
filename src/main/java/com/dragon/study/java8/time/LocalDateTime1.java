package com.dragon.study.java8.time;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

public class LocalDateTime1 {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber1 = sylvester.get(weekFields.weekOfYear());
        System.out.println(weekNumber1);
        int weekNumber2 = sylvester.get(weekFields.weekOfWeekBasedYear());
        System.out.println(weekNumber2);

        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);      // WEDNESDAY

        Month month = sylvester.getMonth();
        System.out.println(month);          // DECEMBER

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);    // 1439

//        SimpleDateFormat
        DateTimeFormatter formatter =
                DateTimeFormatter
                        .ofPattern("dd-MM-yyyy HH:mm", Locale.CHINA);

        LocalDateTime parsed = LocalDateTime.parse("03-11-2014 07:13", formatter);
        String string = parsed.format(formatter);
        System.out.println(string);     // Nov 03, 2014 - 07:13


        Clock clock = Clock.system(ZoneId.of("Asia/Shanghai"));
        LocalDateTime now2 = LocalDateTime.now(clock);
        LocalDateTime tomorrow = now2.plusDays(1);

        //设置cookie的过期时间
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.CHINA);
        System.out.println(dateTimeFormatter.format(tomorrow));
    }

}