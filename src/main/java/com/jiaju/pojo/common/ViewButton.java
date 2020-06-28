package com.jiaju.pojo.common;

import lombok.Data;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 21:01
 */
@Data
public class ViewButton extends AbstractButon {
    private String type = "view";
    private String url;

    public ViewButton(String name, String url) {
        super(name);
        this.url = url;
    }

}
