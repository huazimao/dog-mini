package com.kingmao.dog.appointment.cacha;

import com.kingmao.dog.appointment.provider.entity.SystemEntity;
import org.springframework.stereotype.Component;

/**
 * Paceage:com.kingmao.dog.appointment.cacha
 * Description:
 * Date:2019/2/22
 * Author: 伊秦轩
 **/
@Component
public class SysCacha {
    private static SystemEntity systemEntity;

    public static SystemEntity getSystemEntity() {
        return systemEntity;
    }

    public static void setSystemEntity(SystemEntity systemEntity) {
        SysCacha.systemEntity = systemEntity;
    }
}
