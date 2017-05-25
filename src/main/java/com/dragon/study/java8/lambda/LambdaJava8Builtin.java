package com.dragon.study.java8.lambda;

import com.dragon.study.java8.Person;

import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by dragon on 2017/3/31.
 */
public class LambdaJava8Builtin {

  public static void main(String[] args) throws Exception {
    //断言，传入一个T类型，返回一个boolean
    Predicate<String> predicate = s -> s.length() > 0;
    System.out.println(predicate.test("123"));
    System.out.println(predicate.negate().test("123"));
    System.out.println("=========");

    Predicate<Boolean> nonNull = Objects::nonNull;
    Predicate<Boolean> isNull = Objects::isNull;

    Predicate<String> isEmpty = String::isEmpty;
    Predicate<String> isNotEmpty = isEmpty.negate();

    Predicate<String> moreThan10 = s -> s.length() > 10;
    Predicate<String> lessThan4 = s -> s.length() < 4;
    //s > 10 || s < 4
    Predicate<String> moreThan10OrLessThan4 = moreThan10.or(lessThan4);
    System.out.println(moreThan10OrLessThan4.test("123"));
    System.out.println(moreThan10OrLessThan4.test("1234"));
    System.out.println(moreThan10OrLessThan4.test("1234567890"));
    System.out.println(moreThan10OrLessThan4.test("12345678901"));

    System.out.println("=========");
    //s <= 10 && s >= 4
    Predicate<String> moreThan4AndLessThan10 = moreThan10.or(lessThan4).negate();
    System.out.println(moreThan4AndLessThan10.test("123"));
    System.out.println(moreThan4AndLessThan10.test("1234"));
    System.out.println(moreThan4AndLessThan10.test("1234567890"));
    System.out.println(moreThan4AndLessThan10.test("12345678901"));
    System.out.println("=========");


    //函数 传入一个T类型， 返回一个R类型
    Function<String, Integer> toInteger = Integer::valueOf;
    Function<String, String> backToString = toInteger.andThen(String::valueOf);
    backToString.apply("123");     // "123"


    //提供者函数，传入没有参数，返回一个R类型
    Supplier<Person> personSupplier = Person::new;
    personSupplier.get();   // new Person


    //消费者函数，传入一个T参数，没有返回
    Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
    greeter.accept(new Person("Luke", "Skywalker"));


    //比较函数，传入两个T参数，返回一个int值
    Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
    Person p1 = new Person("John", "Doe");
    Person p2 = new Person("Alice", "Wonderland");
    comparator.compare(p1, p2);             // > 0
    comparator.reversed().compare(p1, p2);  // < 0


    //运行函数， 不传入也没返回
    Runnable runnable = () -> System.out.println(UUID.randomUUID());
    runnable.run();


    //调用函数，不传入，返回一个V类型的对象
    Callable<UUID> callable = UUID::randomUUID;
    callable.call();
  }
}
