package com.jiaju.pojo.common;

import lombok.Data;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 21:00
 */
@Data
public class ClickButton extends AbstractButon {
    private String type = "click";
    private String key;

    public ClickButton(String name, String key) {
        super(name);
        this.key = key;
    }
}
