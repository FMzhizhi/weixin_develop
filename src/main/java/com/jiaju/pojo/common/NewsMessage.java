package com.jiaju.pojo.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 12:58
 */
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage {

   @XStreamAlias("ArticleCount")
    private String articleCount;
    @XStreamAlias("Articles")
    private List<Article> articles = new ArrayList<>();
    public NewsMessage() {}
    public NewsMessage(Map<String, String> requestMap, List<Article> articles) {
        super(requestMap);
        setMsgType("news");
        this.articleCount=articles.size()+"";
        this.articles = articles;
    }
}
