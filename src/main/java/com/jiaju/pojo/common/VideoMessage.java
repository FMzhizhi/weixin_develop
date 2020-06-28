package com.jiaju.pojo.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 12:47
 */
@XStreamAlias("xml")
public class VideoMessage extends BaseMessage {
    @XStreamAlias("MediaId")
    private String mediaId;
    @XStreamAlias("Title")
    private String title;
    @XStreamAlias("Description")
    private String description;

    public VideoMessage(){}

    public VideoMessage(Map<String, String> requestMap, String mediaId, String title, String description) {
        super(requestMap);
        setMsgType("video");
        this.mediaId = mediaId;
        this.title = title;
        this.description = description;
    }

}
