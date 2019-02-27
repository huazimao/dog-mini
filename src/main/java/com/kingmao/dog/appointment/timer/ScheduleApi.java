package com.kingmao.dog.appointment.timer;

import com.kingmao.dog.appointment.cacha.SysCacha;
import com.kingmao.dog.appointment.provider.model.SystemSetting;
import com.kingmao.dog.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Paceage:com.kingmao.dog.appointment.timer
 * Description:
 *  计算店铺能效与当前剩余时间
 * Date:2019/2/22
 * Author: 伊秦轩
 **/
@Component
public class ScheduleApi {
    private static Logger log = Logger.getLogger(ScheduleApi.class);

   /* public void getSchedul(){
        SystemSetting systemSetting = SysCacha.getSystemSetting();
        int effective_time_today = DateUtil.getMin(systemSetting.getServiceEndTime(), new Date());


    }*/
}
