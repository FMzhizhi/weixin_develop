package com.jiaju.pojo.common;

import lombok.Data;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 20:58
 */
@Data
public abstract class AbstractButon {

    private String name;

    public AbstractButon(String name) {
        super();
        this.name = name;
    }
}
