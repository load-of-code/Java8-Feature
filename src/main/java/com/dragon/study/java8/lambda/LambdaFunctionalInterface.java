package com.dragon.study.java8.lambda;

import com.dragon.study.java8.Person;

/**
 * Created by dragon on 2017/3/31.
 */
public class LambdaFunctionalInterface {

  //表示可以把这个接口接受一个函数，输入是F类型，返回是T类型
  @FunctionalInterface
  interface Converter<F, T> {
    T convert(F from);
  }

  private static class Something {
    String startsWith(String s) {
      return s + "!!!";
    }
  }

  //接受一个函数，传入的是2个string对象，返回是P类型
  @FunctionalInterface
  interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
  }

  private static class OtherPerson {
    Person createOther(String f, String l) {
      return new Person(f, l);
    }
  }

  public static void main(String[] args) {
    Converter<String, Integer> integerConverter1 = from -> Integer.valueOf(from);
    System.out.println(integerConverter1.convert("123"));
    System.out.println(integerConverter1.convert("-123"));
    System.out.println(integerConverter1.convert("01234"));

    try {
      System.out.println(integerConverter1.convert("abc1234"));
    } catch (NumberFormatException e) {
      System.err.println(e.getMessage());
    }

    Converter<String, Integer> integerConverter2 = Integer::valueOf;
    Integer converted2 = integerConverter2.convert("123");
    System.out.println(converted2);

    Something something = new Something();
    Converter<String, String> stringConverter = something::startsWith;
    String converted3 = stringConverter.convert("Java");
    System.out.println(converted3);

    //new构造函数也是接受两个参数
    PersonFactory<Person> personFactory = Person::new;
    Person person = personFactory.create("Peter", "Parker");
    System.out.println(person);

    OtherPerson otherPerson = new OtherPerson();
    PersonFactory<Person> personFactory2 = otherPerson::createOther;
    person = personFactory2.create("loc", "tomas");
    System.out.println(person);
  }
}
