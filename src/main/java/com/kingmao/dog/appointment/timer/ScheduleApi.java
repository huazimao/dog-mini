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
        //根据今天的默认设置，生成第四天默认设置
        SystemSetting ljsystemSettingByDefault = systemSettingService.getSySettingByShopIdAndTime("lj",DateUtil.getYMD2(new Date()));
        SystemSetting lssystemSettingByDefault = systemSettingService.getSySettingByShopIdAndTime("ls",DateUtil.getYMD2(new Date()));
        SystemSetting rgsystemSettingByDefault = systemSettingService.getSySettingByShopIdAndTime("rg",DateUtil.getYMD2(new Date()));

        //获取第四天的时间
        Date threeDaysLater = DateUtil.getDatePlus(DateUtil.getYMD2(new Date()));
        ljsystemSettingByDefault.setServiceStartTime(DateUtil.getPlusByTime(ljsystemSettingByDefault.getServiceStartTime(),3));
        ljsystemSettingByDefault.setServiceEndTime(DateUtil.getPlusByTime(ljsystemSettingByDefault.getServiceEndTime(),3));
        ljsystemSettingByDefault.setWorkTime(threeDaysLater);
        ljsystemSettingByDefault.setSubmitTime(new Date());
        boolean flaglj = systemSettingService.insertSysSetting(ljsystemSettingByDefault);
        if (flaglj) {
            log.info("龙江店默认设置自动生成------------成功！");
        }
        lssystemSettingByDefault.setServiceStartTime(DateUtil.getPlusByTime(lssystemSettingByDefault.getServiceStartTime(),3));
        lssystemSettingByDefault.setServiceEndTime(DateUtil.getPlusByTime(lssystemSettingByDefault.getServiceEndTime(),3));
        lssystemSettingByDefault.setWorkTime(threeDaysLater);
        lssystemSettingByDefault.setSubmitTime(new Date());
        boolean flagls = systemSettingService.insertSysSetting(lssystemSettingByDefault);
        if (flagls) {
            log.info("龙山店默认设置自动生成------------成功！");
        }
        rgsystemSettingByDefault.setServiceStartTime(DateUtil.getPlusByTime(rgsystemSettingByDefault.getServiceStartTime(),3));
        rgsystemSettingByDefault.setServiceEndTime(DateUtil.getPlusByTime(rgsystemSettingByDefault.getServiceEndTime(),3));
        rgsystemSettingByDefault.setWorkTime(threeDaysLater);
        rgsystemSettingByDefault.setSubmitTime(new Date());
        boolean flagrg = systemSettingService.insertSysSetting(rgsystemSettingByDefault);
        if (flagrg) {
            log.info("容桂店默认设置自动生成------------成功！");
        }
    }
}
