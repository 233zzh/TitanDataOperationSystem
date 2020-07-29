package edu.neu.titan.titanApp.common.beans;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Wang Kuo
 * @Email: 2383536228@qq.com
 * @Date: 2020/6/17
 * @Time: 12:00
 * @Version: 1.0
 * @Description: 封装名称和值的类
 */
public class NameValue {

    // 键
    private String name;
    // 值
    private Integer value;

    // 无参构造器
    public NameValue() {
    }

    // get/set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
