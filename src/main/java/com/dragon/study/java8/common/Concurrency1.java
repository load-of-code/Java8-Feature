package com.dragon.study.java8.common;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;


public class Concurrency1 {

    public static void main(String[] args) {
        System.out.println("Parallelism: " + ForkJoinPool.getCommonPoolParallelism());

        //这一阈值表示操作并行执行时的最小集合大小。
        // 例如，如果你传入阈值500，而映射的实际大小是499，那么操作就会在单线程上串行执行。
        // 在下一个例子中，我们使用阈值1，始终强制并行执行来展示。

//        test();
//        testForEach();
//        testSearch();
        testReduce();
    }

    private static void test() {
        ConcurrentHashMap<Integer, UUID> concurrentHashMap = new ConcurrentHashMap<>();

        for (int i = 0; i < 100; i++) {
            concurrentHashMap.put(i, UUID.randomUUID());
        }

        int threshold = 1;
        //并行处理
        concurrentHashMap.forEachValue(threshold, System.out::println);

        concurrentHashMap.forEach((id, uuid) -> {
            if (id % 10 == 0) {
                System.out.println(String.format("%s: %s", id, uuid));
            }
        });

        //并行处理
        UUID searchResult = concurrentHashMap.search(threshold, (id, uuid) -> {
            if (String.valueOf(uuid).startsWith(String.valueOf(id))) {
                return uuid;
            }
            return null;
        });

        System.out.println(searchResult);
    }

    private static void testReduce() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo", "bar");
        map.putIfAbsent("han", "solo");
        map.putIfAbsent("r2", "d2");
        map.putIfAbsent("c3", "p0");

        String reduced = map.reduce(1, (key, value) -> {
                System.out.println("Transform: " + Thread.currentThread().getName());
                return key + "=" + value;
            },
            (s1, s2) -> s1 + ", " + s2);

        System.out.println(reduced);
    }

    private static void testSearch() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo", "bar");
        map.putIfAbsent("han", "solo");
        map.putIfAbsent("r2", "d2");
        map.putIfAbsent("c3", "p0");

        System.out.println("\nsearch()\n");

        // search()方法接受BiFunction并为当前的键值对返回一个非空的搜索结果，
        // 或者在当前迭代不匹配任何搜索条件时返回null。
        // 只要返回了非空的结果，就不会往下搜索了。要记住ConcurrentHashMap是无序的。
        // 搜索函数应该不依赖于映射实际的处理顺序。如果映射的多个元素都满足指定搜索函数，结果是非确定的。
        String result1 = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if (key.equals("foo") && value.equals("bar")) {
                return "foobar";
            }
            return null;
        });

        System.out.println(result1);

        System.out.println("\nsearchValues()\n");

        String result2 = map.searchValues(1, value -> {
            System.out.println("value is " + value + ", thread is " + Thread.currentThread().getName());
            if (value.length() > 3) {
                return value;
            }
            return null;
        });

        System.out.println(result2);
    }

    private static void testForEach() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo", "bar");
        map.putIfAbsent("han", "solo");
        map.putIfAbsent("r2", "d2");
        map.putIfAbsent("c3", "p0");

        map.forEach(1, (key, value) -> System.out.printf("key: %s; value: %s; thread: %s\n", key, value, Thread.currentThread().getName()));
//      map.forEach(5, (key, value) -> System.out.printf("key: %s; value: %s; thread: %s\n", key, value, Thread.currentThread().getName()));

        System.out.println(map.mappingCount());


    }
}
