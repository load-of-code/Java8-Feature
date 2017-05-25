package com.dragon.study.java8.time;

import org.joda.time.DateTime;

/**
 * Created by dragon on 2017/4/6.
 */
public class JodaTimeDate {

  public static void main(String[] args) {
    DateTime today = DateTime.now();
    DateTime tomorrow = today.plusDays(1);
    DateTime yesterday = tomorrow.minusDays(2);

    System.out.println(today);
    System.out.println(tomorrow);
    System.out.println(yesterday);

    DateTime independenceDay = new DateTime(2014, 12, 24, 0, 0, 0);
    String dayOfWeek = independenceDay.dayOfWeek().getAsString();
    System.out.println(dayOfWeek);    // 3
    String weekOfWeekYear = independenceDay.weekOfWeekyear().getAsString();
    System.out.println(weekOfWeekYear);    // 52
  }
}
