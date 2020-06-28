package com.jiaju.pojo.request;

import lombok.Data;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 11:33
 */
@Data
public class WeChatRequest {
    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;

}
