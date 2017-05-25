package com.dragon.study.java8.time;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Created on 2017/5/15.
 */
public class ZoneFeature {

  public static void main(String[] args) {
    Set zoneIds = ZoneId.getAvailableZoneIds();
    System.out.println(zoneIds);
    ZoneId zone1 = ZoneId.of("Europe/Berlin");
    ZoneId zone2 = ZoneId.of("Brazil/East");
    ZoneId zone3 = ZoneId.of("Asia/Shanghai");
    System.out.println(zone1.getRules());
    System.out.println(zone2.getRules());
    System.out.println(zone3.getRules());


    Clock clock = Clock.systemDefaultZone();
    //如果你需要特定时区的日期/时间，那么ZonedDateTime是你的选择。
    final ZonedDateTime zonedDatetime = ZonedDateTime.now();
    final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
    final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
    System.out.println(zonedDatetime);
    System.out.println(zonedDatetimeFromClock);
    System.out.println(zonedDatetimeFromZone);
    System.out.println("================");
  }
}
