package com.jiaju.pojo.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 12:49
 */
@Data
public class Music {
    @XStreamAlias("Title")
    private String title;
    @XStreamAlias("Description")
    private String description;
    @XStreamAlias("MusicUrl")
    private String musicURL;
    @XStreamAlias("HQMusicUrl")
    private String hQMusicUrl;
    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;

    public Music() {
    }

    public Music(String title, String description, String musicURL, String hQMusicUrl, String thumbMediaId) {
        super();
        this.title = title;
        this.description = description;
        this.musicURL = musicURL;
        this.hQMusicUrl = hQMusicUrl;
        this.thumbMediaId = thumbMediaId;
    }
}
