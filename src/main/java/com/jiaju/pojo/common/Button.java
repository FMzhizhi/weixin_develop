package com.jiaju.pojo.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 20:59
 */
@Data
public class Button  {

    private List<AbstractButon> button = new ArrayList<>();

    public Button(List<AbstractButon> button) {
        this.button = button;
    }

    public Button(){

    }


}
