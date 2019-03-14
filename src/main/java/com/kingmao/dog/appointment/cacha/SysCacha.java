package com.kingmao.dog.appointment.cacha;

import com.kingmao.dog.appointment.provider.entity.SystemEntity;
import com.kingmao.dog.appointment.timer.AccessTokenApi;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Paceage:com.kingmao.dog.appointment.cacha
 * Description:
 * Date:2019/2/22
 * Author: KingMao
 **/
@Component
public class SysCacha {
    private static Logger log = Logger.getLogger(SysCacha.class);
    private static SystemEntity systemEntity;

    public static SystemEntity getSystemEntity() {
        return systemEntity;
    }

    public static void setSystemEntity(SystemEntity systemEntity) {
        SysCacha.systemEntity = systemEntity;
    }

    private static String accessToken = null;

    public static void refreshToken(String token){
        accessToken = token;
        log.info("token刷新成功！");
    }

    public static String getAccessToken(){
        return accessToken;
    }
}
