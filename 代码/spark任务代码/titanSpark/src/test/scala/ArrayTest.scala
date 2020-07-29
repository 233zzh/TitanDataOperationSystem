/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/7/11 
 * @Time: 17:17
 * @Version: 1.0
 * @Description:
 */
object ArrayTest {
    def main(args: Array[String]): Unit = {
        val array = Array.fill(9)(0)
        array(1) = 1
        array(2) = 2
        println(array.max)

        val array2=Array(1,2,3)
        println(array2(1))

        val array3=(5,6)
        println(array3._2)
    }

}
