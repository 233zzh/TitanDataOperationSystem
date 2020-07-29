package edu.neu.titan.titanApp.common.beans;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Zhao Lei
 * @Email: 1176066749@qq.com
 * @Date: 2020/6/18
 * @Time: 15:42
 * @Version: 1.0
 * @Description:
 */
public class Trend {
    private String currentNum;
    private String compare;

    public Trend(String currentNum, String compare) {
        this.currentNum = currentNum;
        this.compare = compare;
    }

    public String getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(String currentNum) {
        this.currentNum = currentNum;
    }

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }
}
