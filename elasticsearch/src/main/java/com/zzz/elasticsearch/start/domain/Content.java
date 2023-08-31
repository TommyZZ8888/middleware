package com.zzz.elasticsearch.start.domain;

import lombok.Data;

/**
 * @Description User
 * @Author 张卫刚
 * @Date Created on 2023/8/31
 */
@Data
public class Content {

    public String title;

    public String img;

    public String price;


    public Content(String title, String img, String price) {
        this.title = title;
        this.img = img;
        this.price = price;
    }
}
