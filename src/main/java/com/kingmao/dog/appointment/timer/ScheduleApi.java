package com.kingmao.dog.appointment.timer;

import com.kingmao.dog.appointment.cacha.SysCacha;
import com.kingmao.dog.appointment.provider.model.SystemSetting;
import com.kingmao.dog.appointment.provider.service.SystemSettingService;
import com.kingmao.dog.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Paceage:com.kingmao.dog.appointment.timer
 * Description:
 *  计算店铺能效与当前剩余时间
 * Date:2019/2/22
 * Author: KingMao
 **/
@Component
public class ScheduleApi {
    @Autowired
    private SystemSettingService systemSettingService;
    private static Logger log = Logger.getLogger(ScheduleApi.class);

    public void getSchedul(){
        //根据今天的默认设置，生成第三天默认设置
        SystemSetting ljsystemSettingByDefault = systemSettingService.getSySettingByShopIdAndTime("lj",DateUtil.getYMD2(new Date()));
        SystemSetting lssystemSettingByDefault = systemSettingService.getSySettingByShopIdAndTime("ls",DateUtil.getYMD2(new Date()));

        //获取两天后的时间
        Date twoDaysLater = DateUtil.getDatePlus(DateUtil.getYMD2(new Date()));
        ljsystemSettingByDefault.setWorkTime(twoDaysLater);
        lssystemSettingByDefault.setWorkTime(twoDaysLater);
        if (ljsystemSettingByDefault.getIsAppTow() == 1) {
            ljsystemSettingByDefault.setSwitchStatue(1);
        }
        if (lssystemSettingByDefault.getIsAppTow() == 1) {
            lssystemSettingByDefault.setSwitchStatue(1);
        }

        //生成两天后的默认设置

        boolean flaglj = systemSettingService.insertSysSetting(ljsystemSettingByDefault);
        boolean flagls = systemSettingService.insertSysSetting(lssystemSettingByDefault);

        if (flaglj && flagls) {
            log.info("两店默认设置自动生成------------成功！");
        } else {
            log.info("两店默认设置自动生成------------失败！");
        }

    }
}
