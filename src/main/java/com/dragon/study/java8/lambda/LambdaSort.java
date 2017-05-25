package com.dragon.study.java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Created by dragon on 2017/3/31.
 */
public class LambdaSort {
  //简单来说，编程中提到的 lambda 表达式，通常是在需要一个函数，但是又不想费神去命名一个函数的场合下使用，也就是指匿名函数。
  public static void main(String[] args) {
    List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

    Collections.sort(names, new Comparator<String>() {
      //正向对比
      @Override
      public int compare(String a, String b) {
        return a.compareTo(b);
      }
    });
    System.out.println(names);

    Collections.sort(names, (String a, String b) -> {
      //反向对比
      return b.compareTo(a);
    });
    System.out.println(names);

    //反向对比
    Collections.sort(names, (String a, String b) -> b.compareTo(a));
    System.out.println(names);

    //反向对比
    Collections.sort(names, (a, b) -> b.compareTo(a));
    System.out.println(names);

    //反向对比
    names.sort(Collections.reverseOrder());
    System.out.println(names);

    //反向的反向对比
    names.sort(Collections.reverseOrder().reversed());
    System.out.println(names);

    List<String> names2 = Arrays.asList("peter", null, "anna", "mike", "xenia");
    names2.sort(Comparator.nullsLast(String::compareTo));
    System.out.println(names2);

  }
}
