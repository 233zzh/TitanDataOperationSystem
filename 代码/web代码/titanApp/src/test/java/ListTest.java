import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: 张志浩 Zhang Zhihao
 * @Email: 3382885270@qq.com
 * @Date: 2020/6/24
 * @Time: 17:32
 * @Version: 1.0
 * @Description: Description
 */
public class ListTest {
    public static void main(String[] args) {
        /*List<String> phone=new ArrayList<>();
        phone.add("三星");    //索引为0
        phone.add("苹果");    //索引为1
        phone.add("锤子");    //索引为2
        phone.add("华为");    //索引为3
        phone.add("小米");    //索引为4
        //原list进行遍历
        for(String pho:phone){
            System.out.println(pho);
        }
        //生成新list
        phone=phone.subList(1, 4);  //.subList(fromIndex, toIndex)      //利用索引1-4的对象重新生成一个list，但是不包含索引为4的元素，4-1=3
        for (int i = 0; i < phone.size(); i++) { // phone.size() 该方法得到list中的元素数的和
            System.out.println("新的list包含的元素是"+phone.get(i));
        }

        System.out.print("Top"+(13+1));*/

        List<String> phone=new ArrayList<>();
        phone.add("三星");    //索引为0
        phone.add("苹果");    //索引为1
        phone.add("锤子");    //索引为2
        phone.add("华为");    //索引为3
        phone.add("小米");    //索引为4
        System.out.println(phone.size());
        for (String str:phone){
            System.out.print(str+" ");
        }
        String[] b=phone.toArray(new String[0]);
        System.out.println(Arrays.toString(b));
        System.out.println(b.length);

        int[] a={1,2,3};
        Integer dw=122;
        int bw=1;
        bw=dw;
        System.out.println(bw);
        dw=bw;
        System.out.println(dw);
    }
}
