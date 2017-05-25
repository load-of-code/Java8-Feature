package com.dragon.study.java8.time;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

public class LocalTime1 {

  //总结
  //1. 流畅api 2.实例不可变 3. 线程安全
  // 日期与时间处理API，在各种语言中，可能都只是个不起眼的API，
  // 如果你没有较复杂的时间处理需求，可能只是利用日期与时间处理API取得系统时间，
  // 简单做些显示罢了，然而如果认真看待日期与时间，
  // 其复杂程度可能会远超过你的想象，天文、地理、历史、政治、文化等因素，都会影响到你对时间的处理。
  // 所以在处理时间上，最好选用JSR310（如果你用java8的话就实现310了），或者Joda-Time。
  public static void main(String[] args) {

    // get the current time
    Clock clock = Clock.systemDefaultZone();
    long t0 = clock.millis();
    System.out.println(t0);

    Instant instant = clock.instant();
    //原来的jdk使用
    Date legacyDate = Date.from(instant);
    System.out.println(legacyDate);
    System.out.println("================");


    ZoneId zone1 = ZoneId.of("Europe/Berlin");
    ZoneId zone2 = ZoneId.of("Brazil/East");
    ZoneId zone3 = ZoneId.of("Asia/Shanghai");
    // time
    LocalTime now1 = LocalTime.now(zone1);
    LocalTime now2 = LocalTime.now(zone2);
    LocalTime now3 = LocalTime.now(zone3);
    System.out.println(now1);
    System.out.println(now2);
    System.out.println(now3);
    System.out.println(now1.isBefore(now2));  // false
    System.out.println(now1.isBefore(now3));  // true
    long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
    long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
    System.out.println(hoursBetween);
    System.out.println(minutesBetween);
    System.out.println("================");


    // create time
    LocalTime now = LocalTime.now();
    System.out.println(now);
    LocalTime late = LocalTime.of(23, 59, 59);
    System.out.println(late);
    LocalTime leetTime = LocalTime
        .parse("13:37:24", DateTimeFormatter.ofPattern("HH:mm:ss", Locale.CHINA));
    System.out.println(leetTime);


    // to legacy date


  }

}