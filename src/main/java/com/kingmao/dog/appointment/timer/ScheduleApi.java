package com.kingmao.dog.appointment.timer;

import com.kingmao.dog.appointment.provider.model.SystemSetting;
import com.kingmao.dog.appointment.provider.service.SystemSettingService;
import com.kingmao.dog.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        SystemSetting rgsystemSettingByDefault = systemSettingService.getSySettingByShopIdAndTime("rg",DateUtil.getYMD2(new Date()));

        //获取两天后的时间
        Date twoDaysLater = DateUtil.getDatePlus(DateUtil.getYMD2(new Date()));
        if (null != ljsystemSettingByDefault && ljsystemSettingByDefault.getIsAppTow() == 1) {
            ljsystemSettingByDefault.setWorkTime(twoDaysLater);
            ljsystemSettingByDefault.setSwitchStatue(1);
            boolean flaglj = systemSettingService.insertSysSetting(ljsystemSettingByDefault);
            if (flaglj) {
                log.info("龙江店默认设置自动生成------------成功！");
            }
        }
        if (null != lssystemSettingByDefault && lssystemSettingByDefault.getIsAppTow() == 1) {
            lssystemSettingByDefault.setWorkTime(twoDaysLater);
            lssystemSettingByDefault.setSwitchStatue(1);
            boolean flagls = systemSettingService.insertSysSetting(lssystemSettingByDefault);
            if (flagls) {
                log.info("龙山店默认设置自动生成------------成功！");
            }
        }
        if (null != rgsystemSettingByDefault && rgsystemSettingByDefault.getIsAppTow() == 1) {
            rgsystemSettingByDefault.setWorkTime(twoDaysLater);
            rgsystemSettingByDefault.setSwitchStatue(1);
            boolean flagrg = systemSettingService.insertSysSetting(rgsystemSettingByDefault);
            if (flagrg) {
                log.info("容桂店默认设置自动生成------------成功！");
            }
        }
    }
}
