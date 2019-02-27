package com.kingmao.dog.appointment.customer.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingmao.dog.appointment.customer.model.CustomerAppointment;
import com.kingmao.dog.appointment.customer.service.CustomerService;
import com.kingmao.dog.appointment.provider.model.ProviderCount;
import com.kingmao.dog.appointment.provider.model.SystemSetting;
import com.kingmao.dog.appointment.provider.service.ProviderService;
import com.kingmao.dog.appointment.provider.service.SystemSettingService;
import com.kingmao.dog.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Paceage:com.kingmao.dog.appointment.customer.controller
 * Description:
 * Date:2019/2/21
 * Author: 伊秦轩
 **/
@Controller
public class CustomerController {
    private static Logger log = Logger.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private SystemSettingService systemSettingService;

    /**
     * 进入预约页面，展示详情
     * 客户是否预约了，预计开始服务时间
     * 总开关，是否约满
     * @param request
     * @param customerAppointment
     * @return
     */
    @RequestMapping("/customer/appointmentPage.do")
    public String appointmentPage(HttpServletRequest request, CustomerAppointment customerAppointment) {
        log.info("进入到客户预约界面，收到参数为：" + "shopId=" + customerAppointment.getShopId() + "workTime=" + customerAppointment.getWorkTime());
        Gson gson = new Gson();
        customerAppointment.setOpenid("openId");
        CustomerAppointment retApp = null;
        Map<String, Object> map = new HashMap<String, Object>();

        //查询客户是否预约和预约详情
        retApp = customerService.getAppInfo(customerAppointment);
        if (null != retApp) {
            request.setAttribute("isApp",1); //isApp 是否预约过
            request.setAttribute("CustomerAppointment",retApp);
        } else {
            request.setAttribute("isApp",0);
        }

        //查询当日该店总开关
        SystemSetting systemSetting = systemSettingService.getSySettingByShopIdAndTime(customerAppointment.getShopId(), customerAppointment.getWorkTime());
        int sysStatue = systemSetting.getSwitchStatue();
        request.setAttribute("sysStatue",sysStatue);

        //查询当日该店是否已约满，及可预约时间
        ProviderCount providerCount = providerService.getPorivderCountInfo(customerAppointment.getShopId(), customerAppointment.getWorkTime());
        Integer appStatue = 0; //当日该店是否可以预约 1 可以，0不可以
        Integer earnTime = providerCount.getEarnTime();
        Integer consumeTime = providerCount.getConsumeTiime();
        if (consumeTime < earnTime) {
            appStatue = 1;
            Date appTime = DateUtil.countRat(systemSetting.getServiceStartTime(),systemSetting.getServiceEndTime(),earnTime, consumeTime);
            Gson gson1 = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
            request.setAttribute("appTime",gson1.toJson(appTime));
        }
        request.setAttribute("appStatue",appStatue);

        return "customer/appointment";
    }

    /**
     * 客户预约洗护服务
     * 先进行二次校验，再进行预约
     * @param customerAppointment
     * @return
     */
    @ResponseBody
    @RequestMapping("/customer/appointment.do")
    public String getAppointment(CustomerAppointment customerAppointment){
        log.info("客户预约接收到的参数为：" + customerAppointment.toString());
        customerAppointment.setOpenid("openId");
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();

        //预约前进行二次校验
        CustomerAppointment retApp = null;
        ProviderCount providerCount = providerService.getPorivderCountInfo(customerAppointment.getShopId(), customerAppointment.getWorkTime());
        Integer earnTime = providerCount.getEarnTime();
        Integer consumeTime = providerCount.getConsumeTiime();
        if (consumeTime < earnTime) {
            if (customerService.insertAppointment(customerAppointment)){
                retApp = customerService.getAppInfo(customerAppointment);
            }
        }
        if (null != retApp) {
            map.put("type", 1);
        }
        return gson.toJson(map);
    }

    @RequestMapping("/customer/index.do")
    public String go2Index(){
        log.info("进入到店铺选择页");
        return "customer/index";
    }
}
