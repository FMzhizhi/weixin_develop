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


    @Test
    public void sendTemplateMessage() {
        String at = WxService.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+at;
        String data="{\n" +
                "           \"touser\":\"oIK8-uF8Yq_exzn4MkV3kgc63j7c\",\n" +
                "           \"template_id\":\"lk2aT0SdajYeVUALItKQNLWaguHYJdac-Dynqr6YI8A\",         \n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"您有新的反馈信息啦！\",\n" +
                "                       \"color\":\"#abcdef\"\n" +
                "                   },\n" +
                "                   \"company\":{\n" +
                "                       \"value\":\"哈哈公司\",\n" +
                "                       \"color\":\"#fff000\"\n" +
                "                   },\n" +
                "                   \"time\": {\n" +
                "                       \"value\":\"2020年11月11日\",\n" +
                "                       \"color\":\"#1f1f1f\"\n" +
                "                   },\n" +
                "                   \"result\": {\n" +
                "                       \"value\":\"面试通过\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"请和本公司人事专员联系！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        String result = RobotUtil.post(url, data);
        System.out.println(result);
    }

}
