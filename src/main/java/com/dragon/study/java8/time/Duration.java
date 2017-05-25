package com.dragon.study.java8.time;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Created on 2017/5/15.
 */
public class Duration {

  public static void main(String[] args) {
    // Get duration between two dates
    final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
    final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);
    final java.time.Duration duration = java.time.Duration.between(from, to);
    System.out.println("Duration in days: " + duration.toDays());
    System.out.println("Duration in hours: " + duration.toHours());
    System.out.println("================");
  }
}
