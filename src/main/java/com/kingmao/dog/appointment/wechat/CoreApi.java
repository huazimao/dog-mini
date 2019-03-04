package com.kingmao.dog.appointment.wechat;

import com.google.gson.Gson;
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
}
