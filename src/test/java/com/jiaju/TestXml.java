package com.jiaju;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import com.jiaju.pojo.common.*;
import com.jiaju.service.WxService;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 14:17
 */
public class TestXml {


    //设置APPID/AK/SK
    public static final String APP_ID = "20522559";
    public static final String API_KEY = "XpA0jZM9EQ6H61Gwrw2vBGfn";
    public static final String SECRET_KEY = "hbKAdImOUDaPS0dBObx1LsQbQLqRWLT3";

    @Test
    public void test11(){
        String ticket = WxService.getQrCodeTicket();
        System.out.println(ticket);
    }

    @Test
    public void test22(){
        String userInfo = WxService.getUserInfo("oIK8-uF8Yq_exzn4MkV3kgc63j7c");
        System.out.println(userInfo);
    }


    @Test
    public void testPic() {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "C:\\Users\\Administrator\\Desktop\\2.png";
        org.json.JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString(2));
    }

    @Test
    public void test1(){
        Map<String, String> map=new HashMap<>();
        map.put("FromUserName","FromUserName");
        map.put("ToUserName","ToUserName");
       /* String content="nihao";
        TextMessage TM=new TextMessage(map,content);
        XStream xStream=new XStream();
        xStream.processAnnotations(TextMessage.class);
        String xml = xStream.toXML(TM);
        System.out.println(xml);*/

//        List<Article> articles=new ArrayList<>();
//        Article article=new Article();
//        article.setDescription("aa");
//        article.setPicUrl("1111");
//        article.setUrl("111111");
//        article.setTitle("2222");
//        articles.add(article);
//        NewsMessage newsMessage = new NewsMessage(map,articles);
        Music musci=new Music();
        musci.setDescription("1111");
        musci.setHQMusicUrl("222");
        musci.setMusicURL("3333");
        musci.setThumbMediaId("222");
        musci.setTitle("22222");
        MusicMessage musicMessage = new MusicMessage(map,musci);

        String xml = WxService.beanToXml(musicMessage);
        System.out.println(xml);


    }

    @Test
    public void test2(){

        System.out.println(WxService.getAccessToken());
        System.out.println(WxService.getAccessToken());
    }

    @Test
    public void testButton() {
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
        sb.getSub_button().add(new ViewButton("网易新闻", "http://news.163.com"));
        // 加入第三个一级菜单
        btn.getButton().add(sb);
        // 转为json

        Object json = JSONObject.toJSON(btn);
        System.out.println(json.toString());
    }
}
