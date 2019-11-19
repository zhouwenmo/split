# split
分流工具，针对不同层级对用户进行分流处理，针对试验情况的分流处理。
可用于AB测试。

# 设计描述
采用-分层分流。同一层的分流共享流量，不同层的流量独立分流，支持新增加分流方案，和停止分流方案重新分流，采用责任链模式进行处理，依赖jdk8

# 层级之间是独立分流的，每个分出去的流量都是一个小管道，每个管道中又存在不同版本
![flow](https://github.com/zhouwenmo/split/blob/master/split-flow.png)

# 默认采用随机数分流算法，图示按照千分制级进行分流处理
![algorithm-mew](https://github.com/zhouwenmo/split/blob/master/split-algorithm.png)

