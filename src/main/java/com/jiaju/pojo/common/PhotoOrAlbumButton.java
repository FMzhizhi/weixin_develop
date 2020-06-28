package com.jiaju.pojo.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 21:11
 */
@Data
public class PhotoOrAlbumButton extends AbstractButon {
    private String type="pic_photo_or_album";
    private String key;
    private List<AbstractButon> sub_button = new ArrayList<>();

    public PhotoOrAlbumButton(String name,String key) {
        super(name);
        this.key=key;
    }
}
