import org.apache.spark.SparkContext
import org.apache.spark.sql.types.{DataTypes, StructType}

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/6 
 * @Time: 14:46
 * @Version: 1.0
 * @Description:
 */
object LoadMapDict {
    def main(args: Array[String]): Unit = {
        val schema = new StructType()
                .add("biaoshi_hashCode", DataTypes.LongType)
                .add("guid", DataTypes.LongType)

        val spark = analysis.spark
        val mapDict = spark.read.schema(schema).parquet("file:///D:\\study\\运营系统项目资料\\day03\\yiee_logs\\2020-07-02\\idmp\\*.parquet").rdd
                .map(row => {
                    val idFlag = row.getAs[Long]("biaoshi_hashCode")
                    val guid = row.getAs[Long]("guid")
                    printf("%s, %d\n", idFlag, guid)
                    (idFlag.toString, guid)
                }).collectAsMap()
    }
}
