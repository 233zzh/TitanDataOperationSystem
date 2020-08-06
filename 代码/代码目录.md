# T01代码目录
## Azkaban定时任务代码：
顶级目录：azkaban任务

|--jobs 包含具体任务配置文件的目录

|	|--*.job 对应每个定时任务的单独文件

|--job.zip 可以直接上传到Azkaban调度平台的打包后的程序。
## Hive数仓代码：
顶级目录：hive建表语句
|-- hiveSql 包含每个数仓表对应单独的sql语句
|	|--ads 包含ads层数仓表的建表sql代码文件的目录
|	|--dwd 包含dwd层数仓表的建表sql代码文件的目录
|	|--dws 包含dws层数仓表的建表sql代码文件的目录
|	|--ods 包含ods层数仓表的建表sql代码文件的目录
|	|	|--*.sql 对应每个数仓表的建表语句sql
|--hive.sql 集合所需的数仓全部表的sql文件，用于部署。
## Mysql数据库代码：
顶级目录：mysql建表语句
|--titan.sql 利用navycat工具自数据库中导出的sql文件。包含数据库结构、表结构的所有信息，可用于部署。
## spark任务(分析、迁移)代码：
顶级目录：spark任务代码
|--titanSpark-1.0-SNAPSHOT.jar 打包后的jar文件，可以直接用于部署。
|--tianSpark spark任务源代码
|--src/main/resource 资源文件夹，包括各种配置文件。
|-- src/main/scala	源代码文件
|	|-- cn/edu/neu/titan/titanSpark/common 公用部分包
|	|	|--conf	配置管理包
|	|	|--constan 常量管理包
|	|	|--utils 工具类包
|	|-- cn/edu/neu/titan/titanSpark/analysis 分析部分顶级包
|	|	|--base 基础表分析任务实现包
|	|	|--flow 流量表分析任务实现包
|	|	|--retention 留存表分析任务实现表
|	|	|--dim 维度表初始化包
|	|	|--apl 其他分析任务实现包
|	|	|	|--app 执行将多个任务按依赖关系串联形成的粗粒度的任务的对象包
|	|	|	|--bean 存放封装业务数据的案例类
|	|	|	|--function 其中每一个对象对应每一个定时任务
|	|	|	|--udf 存放spark-sql中使用的自定义函数（UDAF、UDTF）
|	|-- cn/edu/neu/titan/titanSpark/migrantion 迁移部分顶级包
|	|	|--baseAnalysis 包含基本统计分析结果展示表的迁移任务
|	|	|--participation 包含参与度分析结果展示表的迁移任务
|	|	|--retention 包含留存分析结果展示表的迁移任务
|	|	|--terminal 包含终端属性分析结果展示表的迁移任务
|	|	|	|--app 同analysis包内app
|	|	|	|--function 同analysis包内function
## Web项目代码：
顶级目录：web代码
|-- titanApp-0.0.1-SNAPSHOT.jar	打包后的jar文件，可以直接用于部署
|-- titanApp Web源代码
	|--src\main\java\edu\neu\titan\titanApp
|	|--controller		控制器包
|	|--service   业务逻辑包
|	  |--impl		service实现包
|	|---dao   数据库访问包
|	|--impl  dao实现包
|	|--sql  sql语句包
|	|--common  公用模块包
|	|   |--beans		业务对象包
|	|   |--utils			工具包
|	|   |--constant		常量包
|	|   |--sql			数据库访问包
	|-- src\main\resources\static
|	|--src\assets   原生资源包 
|	|--libs  官方标准库
|	|--extra-libs  额外库				模板提供
|	|--images  图片资源
|	|--scss  保存scss文件
|--src\material 保存页面html的包
|--dist
	|--js   js文件包
	|	|--myApp 自编写的与每个页面对应的js文件
	|	|	|--common JS公共模块，存放工具js文件
	|	|	|--channel 渠道分析模块对应的js包
	|	|	|--overall概况模块对应的js包
	|	|	|--retention 留存分析模块对应的js包
	|	|	|--participation参与度分析模块对应的js包
	|	|	|--terminal终端分析模块对应的js包
	|	|	|--userAnalyse基础用户分析模块对应的js包
	|--css  css文件包（根据需求进行了在提供的基础上进行了一定修改）
## 模拟数据生成器：
顶级目录：模拟数据生成代码
|-- MockDataGenerate 模拟数据生成器源代码
	|-- /src/main/java 存放项目的java文件
	|--/src/resource 存放模拟数据的配置文件
## 数据提取代码：
顶级目录：数据提取--flume配置文件
|-- *.properties flume启动配置文件
