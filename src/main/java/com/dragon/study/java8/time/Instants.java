package com.dragon.study.java8.time;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created on 2017/5/15.
 */
public class Instants {

  public static void main(String[] args) {

    Clock clock = Clock.systemDefaultZone();
    long t0 = clock.millis();
    System.out.println(t0);

    Instant instant1 = clock.instant();
    //原来的jdk使用
    Date legacyDate1 = Date.from(instant1);
    System.out.println(legacyDate1);
    System.out.println("================");

    LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
    Instant instant2 = sylvester
        .atZone(ZoneId.systemDefault())
        .toInstant();

    Date legacyDate2 = Date.from(instant2);
    System.out.println(legacyDate2);     // Wed Dec 31 23:59:59 CST 2014
  }
}
