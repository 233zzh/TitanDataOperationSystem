import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.joda.time.LocalDate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/30
 * @Time: 18:29
 * @Version: 1.0
 * @Description: 模拟数据生成器
 */
public class MockDataGenerator {

    public static JSONObject event_log = new JSONObject();
    public static long time;
    public static BufferedWriter bw;

    private static final String[] order = {"eventid", "event", "user", "timestamp"};
    public static final int DAY_NEW_NUM = 800;
    public static final int DAY_SESSION_NUM_BASE = 5000;
    public static final Random random = MockUtils.random;
    private static final int HOUR = 3600*1000;
    private static final int SECOND = 1000;
    private static final float AD_CLICK_RATE = 0.3f;

    public static void main(String[] args) throws IOException {

        LocalDate start, end;
        String savePath;
        if (args.length==0) return;
        if (args.length==3) {
            start = new LocalDate(args[1]);
            end = new LocalDate(args[2]);
        }else {
//            start = new LocalDate("2020-06-01");
            start=end = new LocalDate();
        }
        savePath = args[0];
        int count = 1;
        while (!start.isAfter(end)) {
            generate(start,count,savePath);
            count++;
            start = start.plusDays(1);
        }

    }

    public static void generate(LocalDate day, int count, String path) throws IOException {

        File file=new File(path+day.toString());
        if (!file.exists()) file.mkdir();
        String savePath = path + day.toString()+"\\doit.mall.access.log";
        bw=new BufferedWriter(new FileWriter(savePath));
        int s_num = getSessionNum(count);
        long base_time = day.toDate().getTime();

        for (int i = 0; i < s_num; i++) {
            int seed = 2*getSeed(count)+1;
            event_log.put("user", MockUtils.getUser(seed));
            setStartEvent(base_time);
//            System.out.println(event_log.toString());
            writeLine();
            for (int j = 0; j < 20&& MockUtils.rate((float) (20-j)/20); j++) {
                int pgId = random.nextInt(100);
                int adId = random.nextInt(100);
                setPageViewEvent(pgId);
                writeLine();
                setViewContentDetailEvent(pgId);
                writeLine();
                setAdShowEvent(pgId, adId);
                writeLine();
                if (MockUtils.rate(AD_CLICK_RATE)) {
                    setAdClickEvent(pgId, adId);
                    writeLine();
                }
            }
            setEndEvent();
            writeLine();
        }

        bw.close();

    }

    public static int getSessionNum(int count) {
        return (int) ((Math.log(count)+0.2*count)*DAY_SESSION_NUM_BASE);
    }

    public static int getSeed(int count) {
        float rate = Math.max((float)1/count,0.15f);
        if (MockUtils.rate(rate)) return random.nextInt(DAY_NEW_NUM)+(count-1)*DAY_NEW_NUM;
        else return random.nextInt((count-1)*DAY_NEW_NUM);
    }

    private static void setTime() {
        event_log.put("timestamp", time);
        timeFlow();
    }

    public static void setStartEvent(long base_time) {
        time = base_time+random.nextInt(23*HOUR);
        setTime();
        event_log.put("eventid","AppStart");
        event_log.put("event","");
    }

    public static void setEndEvent() {
        setTime();
        event_log.put("eventid","AppEnd");
        event_log.put("event","");
    }

    public static void setPageViewEvent(int pgId) {
        setTime();
        event_log.put("eventid", "pageView");
        event_log.put("event", MockUtils.getPageViewEvent(pgId));
    }

    public static void setViewContentDetailEvent(int pgId) {
        setTime();
        event_log.put("eventid", "viewContentDetail");
        event_log.put("event", MockUtils.getPageViewEvent(pgId));
    }

    public static void setAdShowEvent(int pgId, int adId) {
        setTime();
        event_log.put("eventid", "adShow");
        event_log.put("event", MockUtils.getAdShowEvent(pgId, adId));
    }

    public static void setAdClickEvent(int pgId, int adId) {
        setTime();
        event_log.put("eventid", "adClick");
        event_log.put("event", MockUtils.getAdClickEvent(pgId, adId));
    }

    public static void writeLine() throws IOException {
        StringBuilder sb = new StringBuilder("{");
        for (String key: order) {
            if (sb.length() > 1) {
                sb.append(',');
            }
            sb.append(JSONUtils.quote(key));
            sb.append(':');
            sb.append(JSONUtils.valueToString(event_log.get(key)));
        }
        sb.append("}");
//        System.out.println(sb.toString());
        bw.write(sb.toString()+"\n");
        bw.flush();
    }

    public static void timeFlow() {
        time = time+SECOND*3+random.nextInt(5*SECOND);
    }

}
