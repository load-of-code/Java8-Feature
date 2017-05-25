package com.dragon.study.java8.lambda;

import java.util.function.Supplier;

/**
 * Created by dragon on 2017/4/5.
 */
public class InterfaceFeature {

  public interface interface1 {
    String INTERFACE_STRING_ARG = "123";
    int INTERFACE_INTEGER_ARG = 123;

    String fun1(String arg);

    String fun2(String arg1, String arg2);

    default String addStr(String arg) {
      return arg + "!!!";
    }

    default String call(String arg) {
      return fun1(arg) + fun2(arg, arg);
    }
  }


  public static class Service implements interface1 {

    @Override
    public String fun1(String arg) {
      return addStr(arg);
    }

    @Override
    public String fun2(String arg1, String arg2) {
      return addStr(arg1) + addStr(arg2);
    }
  }

  private interface Defaultable {
    default String notRequired() {
      return "Default implementation";
    }
  }

  private static class DefaultableImpl implements Defaultable {
  }


  private interface DefaultableFactory {
    static Defaultable create(Supplier<Defaultable> supplier) {
      return supplier.get();
    }
  }


  public static void main(String[] args) {
    Service service = new Service();
    System.out.println(service.addStr("1234"));
    System.out.println(service.fun1("1234"));
    System.out.println(service.fun2("abcd", "1234"));
    System.out.println(service.call("===="));

    System.out.println("=======================================");
    System.out.println(interface1.INTERFACE_INTEGER_ARG);
    System.out.println(interface1.INTERFACE_STRING_ARG);

    System.out.println("=======================================");
    Defaultable defaulable = DefaultableFactory.create(DefaultableImpl::new);
    System.out.print(defaulable.notRequired());
  }
}
