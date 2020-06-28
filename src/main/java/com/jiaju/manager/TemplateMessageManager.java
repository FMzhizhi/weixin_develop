package com.jiaju.manager;

import com.jiaju.service.WxService;
import com.jiaju.utils.RobotUtil;
import org.junit.Test;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/28 23:17
 */
public class TemplateMessageManager {


    //设置所属行业
    @Test
    public void setIndustry(){
        String token = WxService.getAccessToken();
        String url="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+token;
        String data="{\n" +
                "    \"industry_id1\":\"1\",\n" +
                "    \"industry_id2\":\"2\"\n" +
                "}";
        String post = RobotUtil.post(url, data);
        System.out.println(post);

    }

    //获取所属行业
    @Test
    public void getIndustry(){
        String token = WxService.getAccessToken();
        String url="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token="+token;
        String s = RobotUtil.get(url);
        System.out.println(s);

    }
}
