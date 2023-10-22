package com.zz.software.communication.websocket.domain.entity;


import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ArrayUtils;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @Describtion: ImageEncode
 * @Author: 张卫刚
 * @Date: 2023/10/22 15:37
 */

public class ImageEncoder implements Encoder.Text<Image> {

    @Override
    public String encode(Image image) throws EncodeException {
        if(image != null && !ArrayUtils.isEmpty(image.getImageByte())){
            String base64Image= Base64.encode(image.getImageByte());
            return JSON.toJSONString(new AjaxResult(AjaxResult.Type.SUCCESS_IMG_BYTE,"获取视频帧成功",base64Image));
        }
        return JSON.toJSONString(AjaxResult.error("获取视频帧失败"));
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
