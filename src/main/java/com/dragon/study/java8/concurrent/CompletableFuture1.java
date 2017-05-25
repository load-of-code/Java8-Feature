package com.dragon.study.java8.concurrent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by dragon on 2017/4/23.
 */
public class CompletableFuture1 {

  public static ExecutorService executor = Executors.newFixedThreadPool(5);

  public static void main(String[] args)
      throws ExecutionException, InterruptedException, TimeoutException {
//    test1();
        test2();
    //    test3();
    //    test4();
    //    test5();

    ConcurrentUtils.stop(executor);
  }


  public static void test1() throws InterruptedException, ExecutionException, TimeoutException {
    CompletableFuture<String> future = new CompletableFuture<>();
    future.complete("4");
//    System.out.println(future.get());
//    future.thenAccept(i -> {
//      System.out.println(i);
//      try {
//        Thread.sleep(2000);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    });

    System.out.println(future.get());
  }

  public static void test2() throws ExecutionException, InterruptedException, TimeoutException {
    CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "test2";
    }, executor);

    System.out.println("======" + System.currentTimeMillis());
    CompletableFuture<Integer> f2 = f1.thenApply(t -> {
      System.out.println(t);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return t.length();
    });
    System.out.println("======" + System.currentTimeMillis());

    CompletableFuture<Double> f3 = f2.thenApply(r -> {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return r * 2.0;
    });
    System.out.println("======" + System.currentTimeMillis());
    System.out.println(f3.get(6000, TimeUnit.MILLISECONDS));
//    System.out.println(f3.get(4000, TimeUnit.MILLISECONDS));
  }

  public static void test3() {
    CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "test3";
    }, executor);
    f1.thenRun(() -> {
      System.out.println("finished");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }

  private static void test4() {
    List<CompletableFuture<String>> futures = IntStream.range(1, 5)
        .mapToObj(CompletableFuture1::long2StringTask).collect(Collectors.toList());
    final CompletableFuture<Void> allCompleted = CompletableFuture
        .allOf(futures.toArray(new CompletableFuture[]{}));
    System.out.println("========");
    allCompleted.thenRun(() -> futures.forEach(future -> {
      try {
        System.out
            .println("get future at:" + System.currentTimeMillis() + ", result:" + future.get());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }));
    try {
      Thread.sleep(10000); //wait
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void test5() {
    List<CompletableFuture<String>> futures = IntStream.range(1, 5)
        .mapToObj(CompletableFuture1::long2StringTask).collect(Collectors.toList());
    final CompletableFuture<Object> firstCompleted = CompletableFuture
        .anyOf(futures.toArray(new CompletableFuture[]{}));
    System.out.println("========");
    firstCompleted.thenAccept(result -> System.out
        .println("get at:" + System.currentTimeMillis() + ",first result:" + result));
  }

  private static CompletableFuture<String> long2StringTask(long i) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        System.out.println("f" + i + " start to sleep at:" + System.currentTimeMillis());
        Thread.sleep(3000);
        System.out.println("f" + i + " stop sleep at:" + System.currentTimeMillis());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return String.valueOf(i);
    }, executor);
  }
}
