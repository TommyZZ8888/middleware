package com.zzz.elasticsearch.start.domain;

import lombok.Data;

/**
 * @Description User
 * @Author 张卫刚
 * @Date Created on 2023/8/31
 */
@Data
public class User {

public String name;

public Integer age;

public String description;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
