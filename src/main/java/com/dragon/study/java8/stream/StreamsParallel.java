package com.dragon.study.java8.stream;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;


public class StreamsParallel {

    public static final int MAX = 5000000;

    public static void sortSequential() {
        List<String> values = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // sequential

        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
    }

    public static void sortParallel() {
        List<String> values = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // parallelStream

        long t0 = System.nanoTime();

        long count = values.parallelStream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));
    }

    public static void main(String[] args) {
//        parallel stream 与sequential one相比有更高的开销，协调线程花费大量时间。我会默认选择sequential streams ，在以下情况下考虑parallel stream
//        1. 有大量items去处理（或者处理每个item需要花费一些时间，并且是可并行的话）
//        2. 性能问题是首要考虑的
//        3. 我没有运行这程序在一个多线程环境中（例如在web容器，如果我已经有许多请求并行处理，在每个请求增加一个额外的并行机制，带来的坏处要大于带来的好处）
//        sortParallel();
//        sortSequential();

        List<String> strings = Arrays
            .asList("a1", "a2", "b1", "c2", "c1","a1", "a2", "b1", "c2", "c1","a1", "a2", "b1", "c2", "c1");

//                test1();
//                test2(strings);
//        test3(strings);
                test4();
    }


    private static void test4() {
        List<String> values = new ArrayList<>(150);
        for (int i = 0; i < 150; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // sequential

        long t0 = System.nanoTime();

        long count = values
            .parallelStream()
            .sorted((s1, s2) -> {
                                System.out.format("sort:    %s <> %s [%s]\n", s1, s2, Thread.currentThread().getName());
                return s1.compareTo(s2);
            })
            .count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));
    }

    private static void test3(List<String> strings) {
        strings
            .parallelStream()
            .filter(s -> {
                System.out.format("filter:  %s [%s]\n", s, Thread.currentThread().getName());
                return true;
            })
            .map(s -> {
                System.out.format("map:     %s [%s]\n", s, Thread.currentThread().getName());
                return s.toUpperCase();
            })
            .sorted((s1, s2) -> {
                System.out.format("sort:    %s <> %s [%s]\n", s1, s2, Thread.currentThread().getName());
                return s1.compareTo(s2);
            })
            .forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
    }

    private static void test2(List<String> strings) {
        strings
            .parallelStream()
            .filter(s -> {
                System.out.format("filter:  %s [%s]\n", s, Thread.currentThread().getName());
                return true;
            })
            .map(s -> {
                System.out.format("map:     %s [%s]\n", s, Thread.currentThread().getName());
                return s.toUpperCase();
            })
            .forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
    }

    private static void test1() {
        // -Djava.util.concurrent.ForkJoinPool.common.parallelism=5

        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());
    }
}
