package com.kingmao.dog.appointment.wechat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kingmao.dog.appointment.cacha.SysCacha;
import com.kingmao.dog.utils.CoreUrl;
import com.kingmao.dog.utils.HttpUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Paceage:com.kingmao.dog.appointment.wechat
 * Description:
 * Date:2019/3/4
 * Author: KingMao
 **/
@Component
public class CoreApi {
    private static Logger log = Logger.getLogger(CoreApi.class);

    @Value("${mini.appid}")
    private String appid;
    @Value("${mini.secret}")
    private String secret;

    @ResponseBody
    @RequestMapping("/core/code2Session.do")
    public String code2Session(String code) {
        String result = null;
        String errcode = null;
        String openid = null;
        String session_key = null;
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            result = HttpUtil.doHttpsGetJson(CoreUrl.getCode2SessionURL(appid,secret,code));
            JSONObject jsonStr = JSONObject.fromObject(result);
            errcode = jsonStr.getString("errcode");
            if (errcode.equals("1")) {
                openid = jsonStr.getString("openid");
                session_key = jsonStr.getString("session_key");
                map.put("openid", openid);
                map.put("session_key", session_key);
            }
        } catch (Exception e) {
            log.error("请求小程序API发生错误", e);
        }
        return gson.toJson(map);
    }

    /**
     * 向客户发送模板消息
     * @param textMsg
     * @return
     */
    public static String sendTemplateMessage(String textMsg){
        String jsonStr = "";
        try {
            jsonStr = HttpUtil.executeJsonParamHttpPost(CoreUrl.sendTemplateMessageURL() + SysCacha.getAccessToken(), textMsg);
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = jsonParser.parse(jsonStr).getAsJsonObject();
            //System.out.println(new GsonBuilder().serializeNulls().setPrettyPrinting().create().toJson(jsonObject));
            if (jsonObject.get("errcode").getAsString().equals("0")) {
                log.info("发送模板消息成功！：");
                return "success";
            }
        } catch (Exception e) {

            log.info("调取微信接口向用户发送模板消息出错：" + jsonStr);
        }
        return "fail";
    }
}
