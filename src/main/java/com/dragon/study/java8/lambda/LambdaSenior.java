package com.dragon.study.java8.lambda;

import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * Created by dragon on 2017/3/31.
 */
public class LambdaSenior {

  public static void main(String[] args) {

    //BiConsumer Example
    BiConsumer<String, Integer> printKeyAndValue = (key, value) -> System.out
        .println(key + "-" + value);

    printKeyAndValue.accept("One", 1);
    printKeyAndValue.accept("Two", 2);

    System.out.println("##################");

    //Java Hash-Map foreach supports BiConsumer
    HashMap<String, Integer> dummyValues = new HashMap<>();
    dummyValues.put("One", 1);
    dummyValues.put("Two", 2);
    dummyValues.put("Three", 3);

    dummyValues.forEach((key, value) -> System.out.println(key + "-" + value));

  }
}
