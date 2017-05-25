package com.dragon.study.java8.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * Created by dragon on 2017/4/6.
 */
public class LocalDate1 {

  public static void main(String[] args) {
    LocalDate today = LocalDate.now();
    LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
    LocalDate yesterday = tomorrow.minusDays(2);

    System.out.println(today);
    System.out.println(tomorrow);
    System.out.println(yesterday);

    LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
    DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
    System.out.println(dayOfWeek);    // FRIDAY
    System.out.println(dayOfWeek.getValue());    // FRIDAY


//    SimpleDateFormat
    LocalDate xmas = LocalDate.parse("24-12-2014", DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.CHINA));
    System.out.println(xmas);   // 2014-12-24


  }
}
