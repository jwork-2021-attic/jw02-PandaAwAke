## Java - 第二次作业

#### 191220080 马英硕



## 任务一

example 的类图如下：

![PlantUML diagram](Readme.assets/example-类图.png)



Scene 中 main 方法执行过程中的对象时序图如下：

![PlantUML diagram](Readme.assets/example-时序.png)



#### 如何理解 example 的设计呢？

先来讲讲总体设计思路，以及它的好处。

我们可以看出，Linable 是一种接口，Gourd 是一种 Linable 的实现，但多了颜色值和 rank。

Line 是总体用来排序的一个容器类，它用一堆 Position 去包装一堆 Linable。

* Position 是 Linable 的一种包装，它在 Line 中作为 Linable 的包装使用。而每个 Linable 也记录自己对应哪个 Position；

Gourd 提供了与另外一个 Gourd 实例进行交换的函数 `swapPosition`。观察它的实现，可见只交换了 Gourd 内部存的 Position，而且交换前后 Gourd 和其内部的 Position 仍然保持一一对应关系。

* 也就是说，rank 和 颜色值 在 Gourd 层面施行交换前后并不会有任何改变。

Sorter 是 theGeezer 用于排序的工具，同样也是一种接口。它接收一系列的 rank (这是每个元素的键值)，并且执行排序。BubbleSorter 是一种 Sorter 的实现，是一种特定的排序算法。

老头 theGeezer 只需要左手拿着排序器，右手拿着一系列 Gourd 构成的 Line，就可以执行排序。



好处自然是很多，比如：

* 排序算法可以自由选择而不用更改任何使用排序的对象的代码，实现了使用和设计的分离；
* 排序算法实现了初步排序 log 包装，可以实现 log 记录与分析；
* 可以构造其他种类的 Linable，老头 theGeezer 同样能够使用排序算法对它进行排序，同理 Line 和 Position 也能包装它；
* 实际上排序算法和 Linable 并没有什么联系。老头排序时使用的方式是拿到他所有目前顺序的 Linable 内的 value (这里就是 Gourd 的 rank)，然后拿去给排序算法排序。这实现了被排序对象的管理和排序算法分离，操作起来比较灵活；
* 重写了 Gourd 的 toString 方法，可以灵活输出一个 Gourd 对象 (目前这里就是多输出颜色值)。



可改进之处：

* Position 和 Linable 有点过度包装... 所有真正用到 Position 的地方，都要使用它的 Linable 对象；Position 并未提供 对 Linable 更进一步的操作，它更像是纯的壳子。在 Linable 和它的包装类 Position 之间双向绑定貌似没有什么作用。Position 只是一个类似一个 Linable 指针的东东，不如直接保存 Linable，也避免了很多麻烦和额外操作；
* Gourd 的设计采取“硬编码”方式，利用 enum 提供的下标去获取 rank，多少有些不灵活。





## 任务二

