Java8发布已经很长一段时间了，最近Java8的市场使用率也是达到了所有版本中市场份额最高的一个版本。本文主要介绍一下java8新加的一些在工作中一些经常会被使用到的重要特性。

1.	Interface里面的default和static关键字的增加，用于把共有具体实现的代码直接写到interface接口中
2.	Lambda函数表达式的特性以及新增的@FunctionalInterface注解，用于简化操作函数，有效的大幅减少代码量
3.	新增加了Optional关键字，这个关键字可以一定程度减少空指针出现的情况，使得代码更加安全
4.	新增加了Stream类型，以及相对应的很多函数式操作（包括串行和并行），所提供的函数可以减少操作集合所需要的代码。
5.	按照JSR-310的标准新增了时间操作函数，可以通过api来简化对时间相关的操作难度
6.	新增加了性能更高的原子计数类LongAdder，更有效率的进行原子技术
7.	新增加了性能更高的票据锁（乐观锁）StampedLock，对于多读少写的场景能够更有效的提高锁的性能
8.	增加了CompelatableFuture异步多线程的任务合作类，能够用于多线程情况下的任务协作

以上的每个知识点都可以进行深入的掌握和了解，想进行继续学习的可以加入我们的群： 632109190，我们群里录制了以上java8新特性的相关视频会分享给大家。

