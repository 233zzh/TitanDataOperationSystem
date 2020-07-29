import com.mysql.cj.jdbc.MysqlDataSource;

import edu.neu.titan.titanApp.common.beans.Condition;


import edu.neu.titan.titanApp.common.beans.Condition;
import edu.neu.titan.titanApp.common.beans.RetentionActivityData;

import edu.neu.titan.titanApp.common.sql.SQLResultBuilder;

import edu.neu.titan.titanApp.dao.sql.ChannelTemplate;

import edu.neu.titan.titanApp.dao.sql.RetentionTemplate;

import edu.neu.titan.titanApp.dao.sql.UserAnalyseTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/18
 * @Time: 14:29
 * @Version: 1.0
 * @Description: 测试能否把查出来的小数转成Integer
 */
public class SelectTest {

    private NamedParameterJdbcTemplate template;

    public static void main(String[] args) {
        SelectTest selectTest = new SelectTest();
        selectTest.test();
    }

//        JdbcTemplate template1 = new JdbcTemplate();



    public void test() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/TiTan?serverTimezone=UTC");
        dataSource.setUser("root");
        dataSource.setPassword("12345678");

        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);


        /*List<Integer> list = SQLResultBuilder.newInstance(Integer.class)
                .sql_select(ChannelTemplate.QUERY_INSTALLATION)
||||||| .r1616
        List<Integer> list = SQLResultBuilder.newInstance(Integer.class)
                .sql_select(UserAnalyseTemplate.INSTALLATION_AVG)
=======


        List<RetentionActivityData> list = SQLResultBuilder.newInstance(RetentionActivityData.class)
                .sql_select(RetentionTemplate.QUERY_ACTIVITY_RETENTION)
                .condition(Condition.instance("2020-06-11", "2020-06-20"))
>>>>>>> .r1693
                .build()
                .getResult(template);

        System.out.println(list.toString());
//
//        System.out.println(list);
*/
        Condition condition = new Condition();
        condition.setStart_date("2020-03-15");
        condition.setEnd_date("2020-03-23");
        condition.setVersion_id(-1);
        condition.setChannel_id(-1);

        /*List<Integer> list=SQLResultBuilder.newInstance(Integer.class)
                .sql_select(ChannelTemplate.QUERY_INSTALLATION)
//                .sql_group(ChannelTemplate.GROUP_BY_CLAUSE)
                .condition(condition)
                .build()
                .getResult(template);*/
        /**
         * 求新增用户的TOP10渠道
         * @param condition 查询条件
         * @return 新增用户版本分布列表
         */
        /*List<String> list = SQLResultBuilder.newInstance(String.class)
                .sql_select(ChannelTemplate.QUERY_INSTALLATION_TOP10_CHANNELS_NAME)
                .sql_group(ChannelTemplate.GROUP_BY_CHANNEL_ID)
                .condition(condition)
                .build()
                .getResult(template);
        System.out.println(list);*/
        List<Integer> list=SQLResultBuilder.newInstance(Integer.class)
                    .sql_select(ChannelTemplate.QUERY_INSTALLATION_TOP10_CHANNELS)
                    .sql_group(ChannelTemplate.GROUP_BY_INSTALLATION_DATE_AND_CHANNEL)
                    .sql_order(ChannelTemplate.ORDER_BY_CHANNEL_AND_INSTALLATION_DATE)
                    .condition(condition)
                    .build()
                    .getResult(template);
        /*List<String> list=SQLResultBuilder.newInstance(String.class)
                .sql_select(ChannelTemplate.QUERY_INSTALLATION_TOP10_CHANNELS_NAME)
                .sql_group(ChannelTemplate.GROUP_BY_CHANNEL_ID)
                .sql_order(ChannelTemplate.ORDER_BY_CHANNEL_AND_INSTALLATION_DATE)
                .condition(condition)
                .build()
                .getResult(template);*/
        System.out.println(list);
        System.out.println(list.size());
    }

}
