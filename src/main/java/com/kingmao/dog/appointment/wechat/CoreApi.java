package com.kingmao.dog.appointment.wechat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kingmao.dog.appointment.cacha.SysCacha;
import com.kingmao.dog.appointment.customer.model.Client;
import com.kingmao.dog.appointment.customer.service.CustomerService;
import com.kingmao.dog.utils.CoreUrl;
import com.kingmao.dog.utils.HttpUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Paceage:com.kingmao.dog.appointment.wechat
 * Description:
 * Date:2019/3/4
 * Author: KingMao
 **/
@Controller
public class CoreApi {
    private static Logger log = Logger.getLogger(CoreApi.class);

    @Value("${mini.appid}")
    private String appid;
    @Value("${mini.secret}")
    private String secret;

    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @RequestMapping("/core/code2Session.do")
    public String code2Session(String code) {
        log.info("进入到core方法，获取code：" + code);
        String result = null;
        String openid = null;
        String session_key = null;
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            result = HttpUtil.doHttpsGetJson(CoreUrl.getCode2SessionURL(appid,secret,code));
            log.info("请求API返回结果：" + result);
            JSONObject jsonStr = JSONObject.fromObject(result);
            if (jsonStr.has("openid")) {
                openid = jsonStr.getString("openid");
                session_key = jsonStr.getString("session_key");
                map.put("openid", openid);
                map.put("session_key", session_key);
                log.info("openid:：" + openid + "session_key: " + session_key);
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

    /**
     * 保存用户信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/core/saveClientInfo.do")
    public String dealuserInfo(HttpServletRequest request) {
        log.info("接收到参数：" + request.getParameter("nickName"));
        String openid = request.getParameter("openid");
        String nickName = request.getParameter("nickName");
        String wxImg = request.getParameter("wxImg");
        Client client = new Client();
        client.setOpenid(openid);
        client.setNickName(nickName);
        client.setWxImg(wxImg);
        if (customerService.insertClient(client)) {
            return "0ok0";
        } else {
            return "falisesesssss";
        }
    }
}
