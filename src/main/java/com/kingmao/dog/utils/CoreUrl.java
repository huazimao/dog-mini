package com.kingmao.dog.utils;

/**
 * Paceage:com.kingmao.dog.utils
 * Description:
 * Date:2019/3/4
 * Author: KingMao
 **/
public class CoreUrl {

    public static String getCode2SessionURL(String appid, String secret, String code) {
        return "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
    }
}
