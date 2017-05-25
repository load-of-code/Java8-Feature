package com.dragon.study.java8.MonteCarlo;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Created by dragon on 2017/4/19.
 */
public class CalcPi {

  public static void main(String[] args) {
    Random random = new Random();
    int max = 1000000;

    AtomicInteger num = new AtomicInteger();

    IntStream.range(0, max).forEach(i -> {
      double x = random.nextDouble();
      double y = random.nextDouble();

      if(x * x + y * y <= 1) {
        num.incrementAndGet();
      }
    });

    System.out.println((num.get() * 4.0 / max));
  }
}
