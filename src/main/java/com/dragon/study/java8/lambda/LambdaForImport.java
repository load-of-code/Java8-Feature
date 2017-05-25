package com.dragon.study.java8.lambda;

/**
 * Created by dragon on 2017/3/31.
 */
public class LambdaForImport {

  int outPutNum;

  void test() {
    int num = 1;

    LambdaFunctionalInterface.Converter<Integer, String> stringConverter = from -> String
        .valueOf(from + num);
    String result = stringConverter.convert(99);
    System.out.println(result);

    LambdaFunctionalInterface.Converter<Integer, String> stringConverter2 = (from) -> {
      outPutNum = 9999;
      return String.valueOf(from);
    };
    String result2 = stringConverter2.convert(1000);
    System.out.println(result2);
    System.out.println(outPutNum);


    String[] array = new String[1];
    LambdaFunctionalInterface.Converter<Integer, String> stringConverter3 = from -> {
      array[0] = "Hi there";
      return String.valueOf(from);
    };

    String result3 = stringConverter3.convert(23);
    System.out.println(result3);
    System.out.println(array[0]);
  }

  public static void main(String[] args) {
    new LambdaForImport().test();
  }
}
