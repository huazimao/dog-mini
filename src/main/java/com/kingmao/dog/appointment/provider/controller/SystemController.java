package com.kingmao.dog.appointment.provider.controller;

import com.google.gson.Gson;
import com.kingmao.dog.appointment.cacha.SysCacha;
import com.kingmao.dog.appointment.provider.entity.SystemEntity;
import com.kingmao.dog.appointment.provider.model.ProviderCount;
import com.kingmao.dog.appointment.provider.model.SystemSetting;
import com.kingmao.dog.appointment.provider.service.ProviderService;
import com.kingmao.dog.appointment.provider.service.SystemSettingService;
import com.kingmao.dog.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Paceage:com.kingmao.dog.appointment.provider.controller
 * Description:
 * Date:2019/2/21
 * Author: 伊秦轩
 **/
@Controller
public class SystemController {
    @Autowired
    private SystemSettingService systemSettingService;
    @Autowired
    private ProviderService providerService;

    private static Logger log = Logger.getLogger(ProviderController.class);

    /**
     * 基本设置
     * @param systemSetting
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/provider/saveOrUptSetting.do",  method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=UTF-8"} )
    public String calculateServiceTime(SystemSetting systemSetting) {
        log.info("接收到基本设置参数：" + systemSetting.toString());
        //记录/修改基本设置
        return systemSettingService.saveOrUptSetting(systemSetting) ? "1" : "0";
    }

    /**
     * 根据店铺ID和生效时间查找默认设置
     * @param request
     * @param shopId
     * @param workTime
     * @return
     */
    @RequestMapping("/provider/getSySettingByShopIdAndTime.do")
    public String getSySettingByShopIdAndTime(HttpServletRequest request,String shopId, Date workTime){
        log.info("接收到查询条件：shopId=" + shopId + "workTime=" + workTime);
        Gson gson = new Gson();
        SystemSetting systemSetting = systemSettingService.getSySettingByShopIdAndTime(shopId,workTime);
        request.setAttribute("systemSetting",systemSetting);
        return "d/d";
    }
}
