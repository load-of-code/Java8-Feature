package com.dragon.study.java8.common;

import java.util.HashMap;
import java.util.Map;


public class Maps1 {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            //如果有key就拿出来，没有的话则放入
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));

        //如果有key，则进行计算
        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3));             // val33

        //如果有key，则进行计算，这个计算就是删除key
        map.computeIfPresent(9, (num, val) -> null);
        System.out.println(map.containsKey(9));     // false

        //如果没有key，则放入
        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println(map.containsKey(23));    // true

        //如果没有key，则进行计算
        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.get(3));             // val33

        //设置默认值
        System.out.println(map.getOrDefault(42, "not found"));      // not found

        //key和value同时满足则删除，否则不变
        map.remove(3, "val3");
        System.out.println(map.get(3));             // val33

        //key和value同时满足则删除
        map.remove(3, "val33");
        System.out.println(map.get(3));             // null

        //Merge做的事情是如果键名不存在则插入，否则则对原键对应的值做合并操作并重新插入到map中。
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));             // val9

        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));             // val9concat
    }

}