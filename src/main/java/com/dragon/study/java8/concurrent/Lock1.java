package com.dragon.study.java8.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

/**
 * Created by dragon on 2017/4/10.
 */
public class Lock1 {

  public static void main(String[] args) {
//    testLock1();
//    testLock2();
//    testReadWriteLock1();
//    testStampedLock1();
    testStampedLock2();
//    testStampedLock3();

  }

  private static final int NUM_INCREMENTS = 10000;
  private static ReentrantLock lock = new ReentrantLock();
  private static int count = 0;
  private static void increment() {
    lock.lock();
    try {
      count++;
    } finally {
      lock.unlock();
    }
  }

  private static void testLock1() {
    count = 0;
    ExecutorService executor = Executors.newFixedThreadPool(2);
    IntStream.range(0, NUM_INCREMENTS)
        .forEach(i -> executor.submit(Lock1::increment));

    ConcurrentUtils.stop(executor);

    System.out.println(count);
  }

  private static void testLock2() {
    ExecutorService executor = Executors.newFixedThreadPool(2);

    ReentrantLock lock = new ReentrantLock();

    executor.submit(() -> {
      lock.lock();
      try {
        ConcurrentUtils.sleep(1);
      } finally {
        lock.unlock();
      }
    });

    executor.submit(() -> {
      System.out.println("Locked: " + lock.isLocked());
      System.out.println("Held by me: " + lock.isHeldByCurrentThread());
      boolean locked = lock.tryLock();
      System.out.println("Lock acquired: " + locked);
    });

    ConcurrentUtils.stop(executor);
  }

  private static void testReadWriteLock1() {
    ExecutorService executor = Executors.newFixedThreadPool(2);

    Map<String, String> map = new HashMap<>();

    ReadWriteLock lock = new ReentrantReadWriteLock();

    executor.submit(() -> {
      lock.writeLock().lock();
      try {
        ConcurrentUtils.sleep(1);
        map.put("foo", "bar");
      } finally {
        lock.writeLock().unlock();
      }
    });

    Runnable readTask = () -> {
      lock.readLock().lock();
      try {
        System.out.println(map.get("foo"));
        ConcurrentUtils.sleep(1);
      } finally {
        lock.readLock().unlock();
      }
    };
    executor.submit(readTask);
    executor.submit(readTask);

    ConcurrentUtils.stop(executor);
  }

  private static void testStampedLock1() {
    //所谓的乐观读模式，也就是若读的操作很多，写的操作很少的情况下，
    // 你可以乐观地认为，写入与读取同时发生几率很少，因此不悲观地使用完全的读取锁定，
    // 程序可以查看读取资料之后，是否遭到写入执行的变更，再采取后续的措施（重新读取变更信息，或者抛出异常） ，
    // 这一个小小改进，可大幅度提高程序的吞吐量！！
    ExecutorService executor = Executors.newFixedThreadPool(2);

    Map<String, String> map = new HashMap<>();

    StampedLock lock = new StampedLock();

    executor.submit(() -> {
      long stamp = lock.writeLock();
      try {
        ConcurrentUtils.sleep(1);
        map.put("foo", "bar");
      } finally {
        lock.unlockWrite(stamp);
      }
    });

    Runnable readTask = () -> {
      long stamp = lock.readLock();
      try {
        System.out.println(map.get("foo"));
        ConcurrentUtils.sleep(1);
      } finally {
        lock.unlockRead(stamp);
      }
    };
    executor.submit(readTask);
    executor.submit(readTask);

    ConcurrentUtils.stop(executor);
  }

  //乐观锁
  private static void testStampedLock2() {
    //我们应当在获取到乐观锁之后,就立即使用它.
    // 因为它随时可能无效.与平常的读锁不同,乐观锁不会阻止其他的线程获得写锁.
    // 也就是说,在一个线程占有乐观锁的时候,其他的线程还是可以获取到写锁的,而不需要等待乐观锁被释放.
    // 当其他线程获得了写锁之后,乐观锁就失效了.即使那个线程后来又释放了写锁.
    ExecutorService executor = Executors.newFixedThreadPool(2);

    StampedLock lock = new StampedLock();

    executor.submit(() -> {
      long stamp = lock.tryOptimisticRead();
      try {
        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
        ConcurrentUtils.sleep(1);
        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
        ConcurrentUtils.sleep(2);
        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
      } finally {
        lock.unlock(stamp);
      }
    });

    executor.submit(() -> {
      long stamp = lock.writeLock();
      try {
        System.out.println("Write Lock acquired");
        ConcurrentUtils.sleep(2);
      } finally {
        lock.unlock(stamp);
        System.out.println("Write done");
      }
    });

    ConcurrentUtils.stop(executor);
  }

  //悲观锁
  private static void testStampedLock3() {
    ExecutorService executor = Executors.newFixedThreadPool(2);

    StampedLock lock = new StampedLock();

    executor.submit(() -> {
      //获得读锁
      long stamp = lock.readLock();
      try {
        if (count == 0) {
          //尝试获得转换成写锁
          stamp = lock.tryConvertToWriteLock(stamp);
          //转换失败
          if (stamp == 0L) {
            System.out.println("Could not convert to write lock");
            //申请写锁
            stamp = lock.writeLock();
          }
          count = 23;
        }
        System.out.println(count);
      } finally {
        //释放票据
        lock.unlock(stamp);
      }
    });

    ConcurrentUtils.stop(executor);
  }
}
