package com.jiaju.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import com.jiaju.pojo.common.*;
import com.jiaju.utils.RobotUtil;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author zhijiaju
 * @version 1.0
 * @date 2020/6/20 10:40
 */
public class WxService {
    private static final String TOKEN = "zhijiaju";
    private static final String APPKEY="1fec136dbd19f44743803f89bd55ca62";
    private static final String GET_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //微信公众号
    private static final String APPID="wx41e41cafa4f506b3";
    private static final String APPSECRET="aa2fbc69a5b017fea26a04abd8e81c19";



    //设置APPID/AK/SK  百度AI
    public static final String APP_ID = "20522559";
    public static final String API_KEY = "XpA0jZM9EQ6H61Gwrw2vBGfn";
    public static final String SECRET_KEY = "hbKAdImOUDaPS0dBObx1LsQbQLqRWLT3";


    //用于存储token
    private static AccessToken at;

    public static void getToken() {
        String url = GET_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        String tokenStr = RobotUtil.get(url);
        System.out.println(tokenStr);
        JSONObject jsonObject = JSONObject.parseObject(tokenStr);
        String token = jsonObject.getString("access_token");
        String expireIn = jsonObject.getString("expires_in");
        //创建token对象,并存起来。
        at = new AccessToken(token, expireIn);
    }

    /**
     * 向处暴露的获取token的方法
     * @return
     */
    public static String getAccessToken() {
        if(at==null||at.isExpired()) {
            getToken();
        }
        return at.getAccessToken();
    }


    /**
     * 验证签名
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    public static boolean check(String timestamp, String nonce, String signature) {
        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] strs = new String[] {TOKEN,timestamp,nonce};
        Arrays.sort(strs);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        String str = strs[0]+strs[1]+strs[2];
        String mysig = sha1(str);
        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        return mysig.equalsIgnoreCase(signature);
    }

    /**
     * 进行sha1加密
     * @param src
     * @return
     */
    private static String sha1(String src) {
        try {
            //获取一个加密对象
            MessageDigest md = MessageDigest.getInstance("sha1");
            //加密
            byte[] digest = md.digest(src.getBytes());
            char[] chars= {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuilder sb = new StringBuilder();
            //处理加密结果
            for(byte b:digest) {
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析xml数据包
     * @param is
     * @return
     */
    public static Map<String, String> parseRequest(InputStream is) {
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        try {
            //读取输入流，获取文档对象
            Document document = reader.read(is);
            //根据文档对象获取根节点
            Element root = document.getRootElement();
            //获取根节点的所有的子节点
            List<Element> elements = root.elements();
            for(Element e:elements) {
                map.put(e.getName(), e.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String getRespose(Map<String, String> requestMap) {
        BaseMessage msg=null;
        String msgType = requestMap.get("MsgType");
        switch (msgType) {
            //处理文本消息
            case "text":
                msg=dealTextMessage(requestMap);
                break;
            case "image":
                msg=dealImage(requestMap);
                break;
            case "voice":

                break;
            case "video":

                break;
            case "shortvideo":

                break;
            case "location":

                break;
            case "link":

                break;
            case "event":
                msg = dealEvent(requestMap);
                break;
            default:
                break;
        }
        System.out.println(msg);
        //把消息对象处理为xml数据包
        if(msg!=null) {
            return beanToXml(msg);
        }
        return null;
    }

    private static BaseMessage dealEvent(Map<String, String> requestMap) {
        String event = requestMap.get("Event");
        switch (event) {
            case "CLICK":
                return dealClick(requestMap);
            case "VIEW":
                return dealView(requestMap);
            default:
                break;
        }
        return null;
    }

    private static BaseMessage dealView(Map<String, String> requestMap) {
        return null;
    }

    private static BaseMessage dealClick(Map<String, String> requestMap) {
        String key = requestMap.get("EventKey");
        switch (key) {
            //点击一菜单点
            case "1":
                //处理点击了第一个一级菜单
                return new TextMessage(requestMap, "你点了一点第一个一级菜单");
            case "32":
                //处理点击了第三个一级菜单的第二个子菜单
                return new TextMessage(requestMap, "第三个一级菜单的第二个子菜单");
               
            default:
                break;
        }
        return null;
    }

    //对象转为xml
    public static String beanToXml(BaseMessage msg) {
        XStream stream = new XStream();
        //设置需要处理XStreamAlias("xml")注释的类
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(NewsMessage.class);
        stream.processAnnotations(VideoMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        String xml = stream.toXML(msg);
        return xml;
    }

    private static BaseMessage dealImage(Map<String, String> requestMap) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 调用接口
       // String path = requestMap.get("PicUrl");

       /* //进行网络图片的识别
        org.json.JSONObject res = client.generalUrl(path, new HashMap<String,String>());
        String json = res.toString();*/
        String path = requestMap.get("PicUrl");
        //进行网络图片的识别
        org.json.JSONObject json = client.generalUrl(path, new HashMap<String,String>());
        //转为jsonObject
        JSONObject jsonObject = JSONObject.parseObject(json.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("words_result");
        System.out.println("jsonArray"+jsonArray);
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<jsonArray.size();i++){
            JSONObject object = jsonArray.getJSONObject(i);
            String words = (String) object.get("words");
            sb.append(words);
        }
        /*Iterator<Object> it = jsonArray.iterator();
        //Iterator<JSONObject> it = jsonArray.iterator();
        StringBuilder sb = new StringBuilder();
        while(it.hasNext()) {
            Object next = it.next();
            sb.append(next.toString());
        }*/
        return new TextMessage(requestMap, sb.toString());
    }


    /**
     * 处理文本消息
     * @param requestMap
     * @return
     */
    private static BaseMessage dealTextMessage(Map<String, String> requestMap) {

        //用户发来的内容
        String msg = requestMap.get("Content");
        if(msg.equals("图文")) {
            List<Article> articles = new ArrayList<>();
            articles.add(new Article("有你想要的", "哈哈哈！！！！！！！", "http://mmbiz.qpic.cn/mmbiz_jpg/c14kcuJ7MRPuxZM3pL4POibZiaow4D0WXE6JDevLdqMzCPjPqJSYLnlhF6lqXbjXGvC65TOWkqUHbN4xNCy0Z2MA/0", "http://www.baidu.com"));
            NewsMessage nm = new NewsMessage(requestMap, articles);
            return nm;
        }
        //调用方法返回聊天的内容
        String resp = chat(msg);
        TextMessage tm = new TextMessage(requestMap, resp);
        return tm;
    }

    /**
     *调用聚合机器人聊天
     * @param msg
     * @return
     */
    private static String chat(String msg) {
        String result =null;
        String url ="http://op.juhe.cn/robot/index";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//您申请到的本接口专用的APPKEY
        params.put("info",msg);//要发送给机器人的内容，不要超过30个字符
        params.put("dtype","");//返回的数据的格式，json或xml，默认为json
        params.put("loc","");//地点，如北京中关村
        params.put("lon","");//经度，东经116.234632（小数点后保留6位），需要写为116234632
        params.put("lat","");//纬度，北纬40.234632（小数点后保留6位），需要写为40234632
        params.put("userid","");//1~32位，此userid针对您自己的每一个用户，用于上下文的关联
        try {
            result = RobotUtil.net(url, params, "GET");
            System.out.println(result);
            //解析json
            JSONObject jsonObject = JSONObject.parseObject(result);
            //取出error_code
            //int code = jsonObject.getInt("error_code");
            int code =jsonObject.getIntValue("error_code");
            System.out.println("code:"+code);
            if(code!=0) {
                return "机器人出现故障...维护中";
            }
            //取出返回的消息的内容
            String resp = jsonObject.getJSONObject("result").getString("text");
            System.out.println("resp:"+resp);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //上传临时素材
    public static String uploadMaterial(String path,String type){
        File file = new File(path);
        String url=" https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        url=url.replace("ACCESS_TOKEN",getAccessToken()).replace("TYPE",type);
        System.out.println(url);
        try {
            URL urlObj=new URL(url);
            //使用https协议
            HttpsURLConnection conn = (HttpsURLConnection) urlObj.openConnection();

            //设置连接信息
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);

            //设置请求头信息
            conn.setRequestProperty("Connection","Keep-Alive");
            conn.setRequestProperty("Charset","utf8");
            //数据边界
            String boundary="-----"+System.currentTimeMillis();
            conn.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);

            //获取输出流
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(file);

            //头部信息
            StringBuilder sb=new StringBuilder();
            sb.append("--");
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition:form-data;name=\"media\";filename=\""+file.getName()+"\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            outputStream.write(sb.toString().getBytes());
            System.out.println(sb.toString());
            //文件内容
            byte[] b=new byte[1024];
            int len=0;
            while ((len=inputStream.read(b))!=-1){
                outputStream.write(b,0,len);
            }
            //尾部信息
            String foot="\r\n--"+boundary+"--\r\n";
            outputStream.write(foot.getBytes());
            outputStream.flush();
            outputStream.close();
            InputStream inputStream2 = conn.getInputStream();
            StringBuilder resp=new StringBuilder();
            while ((len=inputStream2.read(b))!=-1){
                resp.append(new String(b,0,len));
            }
            inputStream2.close();
            inputStream.close();
            return resp.toString();
        } catch (Exception e) {
            throw new RuntimeException("运行时异常",e);
        }

    }

}
