import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import static javafx.scene.input.KeyCode.F;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/7/1
 * @Time: 15:00
 * @Version: 1.0
 * @Description: Description
 */
public class MockUtils {

    public static Random random = new Random();

    public static Properties properties;
    public static String[] provinces;
    public static String[] carriers;
    public static String[] osNames;
    public static String[] osVers;
    public static String[] deviceTypes;
    public static String[] netTypes;
    public static String[] resolutions;
    public static String[] channels;
    public static String[] versions;



    static {
        properties = new Properties();
        InputStream is = Object.class.getResourceAsStream("/mock.properties");
        try {
            InputStreamReader reader = new InputStreamReader(is, "GBK");
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        provinces =  getProperties("province");
        carriers = getProperties("carrier");
        osNames = getProperties("osName");
        osVers = getProperties("osVer");
        deviceTypes = getProperties("deviceType");
        netTypes = getProperties("netType");
        resolutions = getProperties("resolution");
        channels = getProperties("channel");
        versions = getProperties("version");
    }

    public static String[] getProperties(String key) {
        return properties.getProperty(key).split(",");
    }

    public static String getValue(int seed, String[] list) {
        if (list.length==0) return null;
        return list[seed%list.length];
    }

    public static String getSessionId() {
        return UUID.randomUUID().toString();
    }

    public static String getProvince(int seed) {
        return getValue(seed, provinces)+"0000000";
    }

    public static boolean rate(float rate) {
        return rate*1000>random.nextInt(1000);
    }

    public static String getOsName(int seed) {
        return getValue(seed, osNames);
    }

    public static String getOsVer(int seed) {
        return getValue(seed, osVers);
    }

    public static String getDeviceType(int seed) {
        return getValue(seed, deviceTypes);
    }

    public static String getChannel(int seed) {
        return getValue(seed, channels);
    }

    public static String getVersion(int seed){
        return getValue(seed, versions);
    }

    public static String getNetType(int seed){
        return getValue(seed, netTypes);
    }

    public static String getCarrier(int seed) {
        return getValue(seed, carriers);
    }

    public static String getResolution(int seed) {
        return getValue(seed, resolutions);
    }

    public static String getImei(int seed) {
        int IMEI_START = 30000000;
        return String.valueOf(IMEI_START - seed );
    }

    public static String getImsi(int seed) {
        int IMSI_START = 60000000;
        return String.valueOf(IMSI_START - 2*seed);
    }

    public static String getAndroidId(int seed) {
        int IMSI_START = 20000000;
        return String.valueOf(IMSI_START + seed);
    }

    public static String getUid(int seed) {
        return String.format("%06d",seed);
    }

    public static String getPromote_ch(int seed) {
        return String.format("%02d",seed%12);
    }

    public static String getUUid(int seed) {
        int UUID_START = 90000000;
        return String.valueOf(UUID_START - 3*seed);
    }

    public static String getCid_sn(int seed) {
        return String.format("%08d",seed*3);
    }

    public static String getMac(int seed) {
        StringBuilder s = new StringBuilder(String.format("%014x", (seed+1) * 156414));
        int n = 0;
        for (int i = 2; i < 14; i+=2) {
            s.insert(i+n,"-");
            n++;
        }
        return s.toString();
    }

    public static String getIP() {
        return random.nextInt(256)+":"+random.nextInt(256)+":"+random.nextInt(256)+":"+random.nextInt(256);
    }

    public static int dealSeed(int seed) {
        int rand = random.nextInt(100);
        if (rand<25) return seed-1;
        else return seed;
    }

    public static JSONObject getPhone(int seed) {
        JSONObject phone = JSONObject.fromObject(properties.get("json.phone"));
        if (rate(0.99f)) phone.put("imei", getImei(seed));
        if (rate(0.99f)) phone.put("mac", getMac(seed));
        if (rate(0.99f)) phone.put("imsi", getImsi(seed));
        if (rate(0.99f)) phone.put("uuid",getUUid(seed));
        phone.put("osName", getOsName(seed));
        phone.put("osVer", getOsVer(seed));
        if (rate(0.99f)) phone.put("androidId", getAndroidId(seed));
        phone.put("resolution", getResolution(seed));
        phone.put("deviceType", getDeviceType(seed));
        return phone;
    }

    public static JSONObject getApp(int seed) {
        JSONObject app = JSONObject.fromObject(properties.get("json.app"));
        app.put("appVer", getVersion(seed));
        app.put("release_ch", getChannel(seed));
        app.put("promotion_ch", getPromote_ch(seed));
        return app;
    }

    public static String getLongtitude(){
        int ACC = 10000000;
        return String.valueOf((float)(random.nextInt(ACC)/ACC)*100+20);
    }

    public static String getLatitude(){
        int ACC = 10000000;
        return String.valueOf((float)(random.nextInt(ACC)/ACC)*43);
    }

    public static JSONObject getLoc(int seed) {
        JSONObject loc = JSONObject.fromObject(properties.get("json.loc"));
        loc.put("latitude",getLatitude());
        loc.put("longtitude",getLongtitude());
        loc.put("areacode", getProvince(seed));
        loc.put("carrier", getCarrier(seed));
        loc.put("netType",getNetType(seed));
        if (rate(0.99f)) loc.put("cid_sn", getCid_sn(seed));
        loc.put("ip", getIP());
        return loc;
    }

    public static JSONObject getUser(int seed) {
        int seed_dealed = dealSeed(seed);
        JSONObject user = JSONObject.fromObject(properties.get("json.user"));
        if (rate(0.99f)) user.put("uid", getUid(seed));
        user.put("phone",getPhone(seed_dealed));
        user.put("sessionId",getSessionId());
        user.put("loc", getLoc(seed));
        user.put("app", getApp(seed_dealed));
        return user;
    }

    public static JSONObject getAdShowEvent(int pgId, int adId) {
        JSONObject ad = JSONObject.fromObject(properties.get("json.adShow"));
        ad.put("adId", String.valueOf(adId));
        ad.put("pgId",String.valueOf(pgId));
        return ad;
    }

    public static JSONObject getAdClickEvent(int pgId, int adId) {
        JSONObject ad = JSONObject.fromObject(properties.get("json.adClick"));
        ad.put("adId", String.valueOf(adId));
        ad.put("pgId",String.valueOf(pgId));
        return ad;
    }

    public static JSONObject getPageViewEvent(int pgId){
        JSONObject pg = JSONObject.fromObject(properties.get("json.pageView"));
        pg.put("pgId", String.valueOf(pgId));
        return pg;
    }

    public static JSONObject getViewContentDetailEvent(int pgId){
        JSONObject pg = JSONObject.fromObject(properties.get("json.viewContentDetail"));
        pg.put("pgId", String.valueOf(pgId));
        return pg;
    }

}
