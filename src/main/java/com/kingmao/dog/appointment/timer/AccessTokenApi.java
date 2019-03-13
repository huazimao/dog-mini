package com.kingmao.dog.appointment.timer;

import com.kingmao.dog.appointment.cacha.SysCacha;
import com.kingmao.dog.utils.CoreUrl;
import com.kingmao.dog.utils.HttpUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Paceage:com.kingmao.dog.appointment.timer
 * Description:
 * Date:2019/3/13
 * Author: KingMao
 **/
@Component
public class AccessTokenApi {
    private static Logger log = Logger.getLogger(AccessTokenApi.class);
    @Value("${mini.appid}")
    private String appid;
    @Value("${mini.secret}")
    private String secret;

    public void getToken(){
        String result = null;
        String accessToken = "";
        result = HttpUtil.doHttpsGetJson(CoreUrl.getAccessTokenURL(appid,secret));

        if (null != result && !"".equals(result)) {
            JSONObject jsonObject = JSONObject.fromObject(result);
            accessToken = jsonObject.getString("access_token");
        }

        if (null != accessToken && !"".equals(accessToken)) {
            SysCacha.refreshToken(accessToken);
            log.info("获取accesstoken成功！---------" + accessToken);
        }
    }
}
