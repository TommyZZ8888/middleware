package com.zz.software.communication.websocket.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Describtion: Image
 * @Author: 张卫刚
 * @Date: 2023/10/22 15:36
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Image {
    private byte[] imageByte;
}