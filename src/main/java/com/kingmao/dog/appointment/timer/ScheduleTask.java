package com.kingmao.dog.appointment.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Paceage:com.kingmao.dog.appointment.timer
 * Description:
 * Date:2019/2/22
 * Author: KingMao
 **/
@Component
public class ScheduleTask{
    @Autowired
    private ScheduleApi scheduleApi;
    @Scheduled(cron = "0 0 0 * * ?")
    public void firstScheduledTasks(){
        System.out.println("定时任务执行，现在时间是 : "+ new Date());
        scheduleApi.getSchedul();
    }

}
