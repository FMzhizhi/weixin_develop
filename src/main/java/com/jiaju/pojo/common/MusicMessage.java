package com.jiaju.pojo.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 12:49
 */
@Data
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage {
    @XStreamAlias("Music")
    private Music music;

    public MusicMessage(){}

    public MusicMessage(Map<String, String> requestMap, Music music) {
        super(requestMap);
        setMsgType("music");
        this.music = music;
    }
}
