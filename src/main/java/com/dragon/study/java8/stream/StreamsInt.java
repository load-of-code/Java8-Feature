package com.dragon.study.java8.stream;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class StreamsInt {

    private static void test4() {
        Stream
            .of(new BigDecimal("1.2"), new BigDecimal("3.7"))
            .mapToDouble(BigDecimal::doubleValue)
            .average()
            .ifPresent(System.out::println);
    }

    private static void test3() {
        IntStream
            .range(0, 10)
            .average()
            .ifPresent(System.out::println);
    }

    private static void test2() {
        IntStream
            .builder()
            .add(1)
            .add(3)
            .add(5)
            .add(7)
            .add(11)
            .build()
            .average()
            .ifPresent(System.out::println);

    }

    private static void test1() {
        int[] ints = {1, 3, 5, 7, 11};
        Arrays
            .stream(ints)
            .average()
            .ifPresent(System.out::println);
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 1) {
                System.out.println(i);
            }
        }

        System.out.println("=======");
        IntStream.range(0, 10)
            .forEach(i -> {
                if (i % 2 == 1) System.out.println(i);
            });

        System.out.println("=======");
        IntStream.range(0, 10)
            .filter(i -> i % 2 == 1)
            .forEach(System.out::println);

        System.out.println("=======");
        OptionalInt reduced1 =
            IntStream.range(0, 10)
                .reduce((a, b) -> a + b);
        System.out.println(reduced1.getAsInt());

        System.out.println("=======");
        OptionalInt reduced2 =
            IntStream.rangeClosed(0, 10)
                .reduce((a, b) -> a + b);
        System.out.println(reduced2.getAsInt());


        System.out.println("=======");
        int reduced3 =
            IntStream.range(0, 10)
                .reduce(7, (a, b) -> a + b);
        System.out.println(reduced3);

        System.out.println("=======");
        IntStream.generate(() -> ThreadLocalRandom.current().nextInt(10)).limit(5).forEach(System.out::println);

        System.out.println("=======");
        int n = 10;
        //for(int i = 3; i < n; i += 2)
        IntStream.iterate(3, i -> i + 2).limit((n - 3) / 2 + 1).forEach(System.out::println);

        System.out.println("=======");
        //类 SecureRandom继承了Random类，它能生成密码强度的随机数。该类能产生真正的随机数，而不是伪随机数。
        //可以用长整数值或字节数组设置内部种子。如果不提供种子，SecureRandom实例将调用自行设置种子的方法来生成真正的随机数。
//        SecureRandom secureRandom = new SecureRandom(new byte[]{1, 3, 3, 7});
        SecureRandom secureRandom = SecureRandom.getInstanceStrong();
        int[] randoms = IntStream.generate(secureRandom::nextInt)
            .filter(i -> i > 0)
            .limit(10)
            .toArray();
        System.out.println(Arrays.toString(randoms));

        test1();
        test2();
        test3();
        test4();

    }
}
