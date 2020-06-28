package com.jiaju.pojo.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 21:04
 */

public class SubButton extends AbstractButon {

    private List<AbstractButon> sub_button = new ArrayList<>();

    public List<AbstractButon> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<AbstractButon> sub_button) {
        this.sub_button = sub_button;
    }

    public SubButton(String name) {
        super(name);
    }

}
