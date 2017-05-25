package com.dragon.study.java8.stream;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by dragon on 2017/4/5.
 */
public class OptionalFeature {

  static class Outer {
    Nested nested = new Nested();

    public Nested getNested() {
      return nested;
    }
  }

  static class Nested {
    Inner inner = new Inner();

    public Inner getInner() {
      return inner;
    }
  }

  static class Inner {
    String foo = "boo";

    public String getFoo() {
      return foo;
    }
  }


  public static <T> Optional<T> resolve(Supplier<T> resolver) {
    try {
      T result = resolver.get();
      return Optional.ofNullable(result);
    }
    catch (NullPointerException e) {
      return Optional.empty();
    }
  }

  private static void test3() {
    Outer outer = new Outer();
    resolve(() -> outer.getNested().getInner().getFoo())
        .ifPresent(System.out::println);
  }

  private static void test2() {
    Optional.of(new Outer())
        .map(Outer::getNested)
        .map(Nested::getInner)
        .map(Inner::getFoo)
        .ifPresent(System.out::println);
  }

  private static void test1() {
    Optional.of(new Outer())
        .flatMap(o -> Optional.ofNullable(o.nested))
        .flatMap(n -> Optional.ofNullable(n.inner))
        .flatMap(i -> Optional.ofNullable(i.foo))
        .ifPresent(System.out::println);
  }

  private static void test0() {
    Outer outer = new Outer();
    Nested nested = outer.getNested();
    if(nested != null) {
      Inner inner = nested.getInner();
      if(inner != null) {
        String foo = inner.getFoo();
        if(foo != null) {
          System.out.println(foo);
        }
      }
    }
  }

  public static void main(String[] args) {
    Optional<String> optional1 = Optional.of("bam");

    System.out.println(optional1.isPresent());           // true
    System.out.println(optional1.get());                 // "bam"
    System.out.println(optional1.orElse("fallback"));    // "bam"

    optional1.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"

//    Optional<String> optional2 = Optional.of(null);
    Optional<String> optional2 = Optional.empty();
//    Optional<String> optional2 = Optional.ofNullable(null);
    System.out.println(optional2.isPresent());           // false
    System.out.println(optional2.orElse("fallback"));    // "fallback"
    optional2.ifPresent((s) -> System.out.println(s.charAt(0)));

    System.out.println("=========");
    test0();
    System.out.println("=========");
    test1();
    System.out.println("=========");
    test2();
    System.out.println("=========");
    test3();
  }
}
