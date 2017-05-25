package com.dragon.study.java8.stream;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class Streams1 {

  public static void main(String[] args) {

    List<String> stringCollection = Arrays
        .asList("11", "2222", "243", "aggfhdfh", "bbb3", "ccc", "bbb2", "ddd1");


    // filtering
    for(String str : stringCollection) {
        if(str.startsWith("a")) {
          System.out.println(str);
        }
    }


    stringCollection.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);

    // "aaa2", "aaa1"


    // sorting

    stringCollection.stream().sorted().filter((s) -> s.startsWith("a"))
        .forEach(System.out::println);

    // "aaa1", "aaa2"


    // mapping

    stringCollection.stream()
        //                .map(s -> s.toUpperCase())
        .map(String::toUpperCase).sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);

    // "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"


    // matching

    boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));

    System.out.println(anyStartsWithA);      // true

    boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));

    System.out.println(allStartsWithA);      // false

    boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));

    System.out.println(noneStartsWithZ);      // true


    // counting

    long startsWithB = stringCollection.stream().filter((s) -> s.startsWith("b")).count();

    System.out.println(startsWithB);    // 3


    // reducing

    Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);

    reduced.ifPresent(System.out::println);
    // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"


  }

}
