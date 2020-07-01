package com.jiaju.web.wechat;

import com.alibaba.fastjson.JSONObject;
import com.jiaju.pojo.request.WeChatRequest;
import com.jiaju.service.WxService;
import com.jiaju.utils.RobotUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 11:07
 */
@RestController
@RequestMapping("/weChat")
public class AccessController {

    @GetMapping
    public void weChatAccess(WeChatRequest request , HttpServletResponse response) throws IOException {
        String signature = request.getSignature();
        String timestamp = request.getTimestamp();
        String nonce = request.getNonce();
        String echostr = request.getEchostr();
        //校验证签名
        if(WxService.check(timestamp,nonce,signature)) {
            System.out.println("接入成功");
            PrintWriter out = response.getWriter();
            //原样返回echostr参数  微信要求返回
            out.print(echostr);
            out.flush();
            out.close();
        }else {
            System.out.println("接入失败");
        }
    }

    /**
     * 处理消息请求
     * @param request
     * @param response
     */
    @PostMapping
    public void ReceiveWeChat(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf8");
            response.setCharacterEncoding("utf8");
            //处理消息和事件推送
            Map<String, String> requestMap = WxService.parseRequest(request.getInputStream());
            System.out.println(requestMap);
            //准备回复的数据包
            String respXml = WxService.getRespose(requestMap);
            System.out.println(respXml);
            PrintWriter out = response.getWriter();
            out.print(respXml);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/GetUserInfo")
    public void GetUserInfo(HttpServletRequest request, HttpServletResponse response){

        String code = request.getParameter("code");
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        url=url.replace("APPID", "wx41e41cafa4f506b3").replace("SECRET", "aa2fbc69a5b017fea26a04abd8e81c19").replace("CODE", code);
        String result = RobotUtil.get(url);
        System.out.println(result);
        String at = JSONObject.parseObject(result).getString("access_token");
        String openid = JSONObject.parseObject(result).getString("openid");
        url="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        url=url.replace("ACCESS_TOKEN", at).replace("OPENID", openid);
        result = RobotUtil.get(url);
        System.out.println(result);


    }
}
