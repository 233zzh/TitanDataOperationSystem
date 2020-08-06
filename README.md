# TitanDataOperationSystem
## 概览
《Titan数据运营系统》，本项目所适用的行业或业务背景有： 主营业务在线上(app/网站)进行的公司！ 这类公司，都需要针对用户的线上访问行为、消费行为、业务操作行为进行统计分析，数据挖掘！以 支撑公司的业务运营、精准画像营销、个性化推荐等，来提高业务转化率，改善公司运营效果！ 这些需求，都需要通过构建一个综合数据处理系统来支撑！

本项目是一个全栈闭环系统，我们先用flume-kafaka-flume对埋点日志服务器中日志进行读取，然后将日志放到我们的hdfs，然后在hive设计数仓，编写spark代码进行数仓表之间的转化以及ads层表到mysql的迁移，之后使用azkaban进行定时任务的调度，最后我们将mysql存的报表数据以web的形式展现出来。使用技术：Java/Scala语言，Hadoop、Spark、Hive、Kafka、Flume、Azkaban、SpringBoot，Bootstrap， Echart等。
 
项目的web展示：http://www.superhao.top:12121/src/material/installation.html **建议到本文档底部去看web界面的展示**

**请注意：1. 我们所拥有的数据从2020.5.25-2020.7.20，但是我们网页一进去他会默认选择今天前一周的日期---今天的日期，所以一定会报错，请手动选择日期在5.25-7.20之间
2. 我们的网站部署到阿里云的学生机，所以会很慢，请耐心等待，谢谢**

博客地址：https://zihao.blog.csdn.net/category_10041667.html

## 功能模块划分
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020072921524085.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

## 系统架构设计
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200803183256878.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

## 系统业务流程
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200729215307388.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

## 数仓分层设计和spark定时任务
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200803182637561.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

## spark任务之间的依赖关系
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200803182631263.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

## azkaban定时任务调度
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200803183015924.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)



## web界面展示
## 一、概况模块
### 整体趋势页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200806115616128.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200806115624225.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)



## 二、用户分析模块
### 新增用户页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020080611570646.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

### 活跃用户页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200806115844910.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

### 启动次数页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200806115900534.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

### 版本分布页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200806115911835.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

## 三、留存分析模块
### 留存用户页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200806120059222.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

### 用户新鲜度页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020080612010477.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

### 用户活跃度页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200806120108378.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

## 四、渠道分析模块
### 渠道列表页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200806120113769.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

## 五、用户参与度模块
### 使用时长页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200806120117999.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

### 使用频率页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200806120122263.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

### 访问页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200806120127841.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

### 访问间隔
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200806120131596.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

## 六、终端属性模块
### 设备终端页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200806120135926.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

### 网络页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020080612014042.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)

### 地域页面
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200806120144155.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzEyNDI3OQ==,size_16,color_FFFFFF,t_70)
