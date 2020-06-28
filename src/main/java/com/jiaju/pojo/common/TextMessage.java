package com.jiaju.pojo.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 12:33
 */
@Data
@XStreamAlias("xml")
public class TextMessage extends BaseMessage {


    @XStreamAlias("Content")
    private String content;
    public TextMessage(){}
    public TextMessage(Map<String, String> requestMap, String content) {
        super(requestMap);
        // 设置文本消息的msgtype为text
        this.setMsgType("text");
        this.content = content;
    }
}
