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
    @Autowired
    private AccessTokenApi accessTokenApi;


    @Scheduled(cron = "0 0 15 * * ?")
//    @Scheduled(fixedRate = 60 * 1000 * 60)
    public void firstScheduledTasks(){
        System.out.println("开始执行店铺设置任务。");
        scheduleApi.getSchedul();
    }

    //项目启动后执行一次，每隔1h执行
    @Scheduled(fixedRate = 60 * 1000 * 60)
    public void secondScheduledTasks(){
        accessTokenApi.getToken();
    }

}
