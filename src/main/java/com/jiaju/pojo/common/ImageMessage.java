package com.jiaju.pojo.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import javax.crypto.interfaces.PBEKey;
import java.util.Map;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 12:45
 */
@Data
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {

    @XStreamAlias("MediaId")
    private String mediaId;

    public ImageMessage(){

    }
    public ImageMessage(Map<String, String> requestMap, String mediaId) {
        super(requestMap);
        this.setMsgType("image");
        this.mediaId=mediaId;
    }
}
