package com.zz.mqtt.domain;

/**
 * @Describtion: MqttMsg
 * @Author: 张卫刚
 * @Date: 2023/9/19 20:54
 */
/**
 * @author WXY
 * @date 2022/6/29 20:44
 */
public class MqttMsg {
    private String name = "";
    private String content = "";
    private String time = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MqttMsg{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

