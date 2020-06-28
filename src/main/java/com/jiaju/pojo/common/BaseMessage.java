package com.jiaju.pojo.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 12:30
 */
@Data
public class BaseMessage {
    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private String createTime;
    @XStreamAlias("MsgType")
    private String msgType;

    public BaseMessage() {
    }

    public BaseMessage(Map<String, String> requestMap) {
        this.toUserName=requestMap.get("FromUserName");
        this.fromUserName=requestMap.get("ToUserName");
        this.createTime=System.currentTimeMillis()/1000+"";
    }
}
