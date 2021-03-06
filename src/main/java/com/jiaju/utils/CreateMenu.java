package com.jiaju.utils;

import com.alibaba.fastjson.JSONObject;
import com.jiaju.pojo.common.*;
import com.jiaju.service.WxService;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 21:45
 */
public class CreateMenu {

    public static void main(String[] args) {
        // 菜单对象
        Button btn = new Button();
        // 第一个一级菜单
        btn.getButton().add(new ClickButton("一级点击", "1"));
        // 第二个一级菜单
        btn.getButton().add(new ViewButton("一级跳转", "http://www.baidu.com"));
        // 创建第三个一级菜单
        SubButton sb = new SubButton("有子菜单");
        // 为第三个一级菜单增加子菜单
        sb.getSub_button().add(new PhotoOrAlbumButton("传图", "31"));
        sb.getSub_button().add(new ClickButton("点击", "32"));
        sb.getSub_button().add(new ViewButton("网易新闻",  "http://news.163.com"));
        // 加入第三个一级菜单
        btn.getButton().add(sb);
        // 转为json
        Object json = JSONObject.toJSON(btn);

        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", WxService.getAccessToken());
        System.out.println(json.toString());
        String post = RobotUtil.post(url, json.toString());
        System.out.println(post);

    }
}
