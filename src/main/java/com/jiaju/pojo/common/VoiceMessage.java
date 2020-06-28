package com.jiaju.pojo.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 12:46
 */
@Data
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage {
    @XStreamAlias("MediaId")
    private String mediaId;
    public VoiceMessage(){}
    public VoiceMessage(Map<String, String> requestMap, String mediaId) {
        super(requestMap);
        this.setMsgType("voice");
        this.mediaId = mediaId;
    }

}
