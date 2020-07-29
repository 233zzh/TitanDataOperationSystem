import com.mysql.cj.jdbc.MysqlDataSource;
import edu.neu.titan.titanApp.common.utils.DateUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/21
 * @Time: 15:27
 * @Version: 1.0
 * @Description: 像数据库中插入模拟数据
 */
public class InsertDBData {
    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost:3306/TiTan?serverTimezone=UTC");
        dataSource.setUser("root");
        dataSource.setPassword("12345678");

        String start = "2020-03-15";
        String end = "2020-04-21";
        String[] dates = DateUtils.getDays(start, end);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Random random = new Random();

        String[] duration_range = {"1秒-3秒", "4秒-10秒", "11秒-30秒", "31秒-1分", "1分-3分", "3分-10分", "10分-30分", "30分+"};
        for (String date : dates) {
            for (int version_id = 1; version_id <= 3; version_id++) {
                for (int channel_id = 1; channel_id <= 3; channel_id++) {
                    for (String str : duration_range) {
                        String sql = "insert into base_participation_duration (duration_date, channel_id, version_id, duration_range, start_num, user_num)  values ('" +
                                date + "', " + channel_id + ", " + version_id + ", '" + str+  "', " + random.nextInt(100) + ", " + random.nextInt(100) +")";

                        String sql1 = "insert into base_user_installation_day (installation_date, channel_id, version_id, increase_num) values (" +
                                date + ", " + channel_id + ", " + version_id + ", " + random.nextInt(100) + ")";

//                    String sql = "insert into base_user_installation_day (installation_date, channel_id, version_id, increase_num) values ('" +
//                            date + "', " + channel_id + ", " + version_id + ", " + random.nextInt(100) + ")";


                        String sql2 = "insert into base_participation_duration (duration_date, channel_id, version_id, duration_range, start_num, user_num) values (" +
                                date + ", " + channel_id + ", " + version_id + ", " + str + ", " + random.nextInt(100) + ", " + random.nextInt(100) + ")";

                        /*String sql3 = "insert into base_participation_duration (duration_date, channel_id, version_id,duration_range, start_num,user_num) values ('" +
                                date + "', " + channel_id + ", " + version_id + ", " +str+", "+ random.nextInt(100) +", "+ random.nextInt(100)+")";*/
//                    System.out.println(sql);
                        jdbcTemplate.execute(sql);
                    }

                }
            }
        }
    }
}
