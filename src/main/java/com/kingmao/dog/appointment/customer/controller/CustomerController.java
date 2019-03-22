package com.kingmao.dog.appointment.customer.controller;

import com.google.gson.Gson;
import com.kingmao.dog.appointment.customer.model.CustomerAppointment;
import com.kingmao.dog.appointment.customer.service.CustomerService;
import com.kingmao.dog.appointment.provider.model.SystemSetting;
import com.kingmao.dog.appointment.provider.service.SystemSettingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Paceage:com.kingmao.dog.appointment.customer.controller
 * Description:
 * Date:2019/2/21
 * Author: KingMao
 **/
@Controller
@RequestMapping("/customer/")
public class CustomerController {
    private static Logger log = Logger.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SystemSettingService systemSettingService;
    @Value("${mini.template_id}")
    private String templateId;

    /**
     * 进入预约页面，展示详情
     * 客户是否预约了，预计开始服务时间
     * 总开关，是否约满
     *
     * 第一位客户测试已通
     * @param customerAppointment
     * @return
     */
    @ResponseBody
    @RequestMapping("appointmentPage.do")
    public String appointmentPage(CustomerAppointment customerAppointment) {
        log.info("进入到客户预约界面，收到参数为：" + "shopId=" + customerAppointment.getShopId() + "workTime=" + customerAppointment.getWorkTime());
        String shopId = customerAppointment.getShopId();
        Date workTime = customerAppointment.getWorkTime();
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        customerAppointment.setOpenid("opend");

        //查询当日该店总开关
        SystemSetting systemSetting = systemSettingService.getSySettingByShopIdAndTime(shopId, workTime);
        int sysStatue = systemSetting.getSwitchStatue();
        map.put("sysStatue",sysStatue);
        if (sysStatue == 1) {
            //查询客户是否预约和预约详情
            customerAppointment = customerService.getAppInfo(customerAppointment);
            if (null != customerAppointment) {
                map.put("isApp",1); //isApp 是否预约过
                map.put("CustomerAppointment",customerAppointment);
            } else {
                map.put("isApp",0);
            }
        }

        return gson.toJson(map);
    }

    /**
     * 客户预约洗护服务
     * @param customerAppointment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "appointment.do" ,produces = {"application/json;charset=UTF-8"})
    public String getAppointment(@RequestBody CustomerAppointment customerAppointment){
        log.info("客户预约接收到的参数为：" + customerAppointment.toString());
        customerAppointment.setOpenid("aaaaaaa");
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        if (customerService.insertAppointment(customerAppointment)){
            map.put("type", 1);
        }

        return gson.toJson(map);
    }

    /**
     * 点击 预约时发出请求
     * 查询客户最近一次预约记录，并填充到预约信息中
     * @param shopId
     * @param openid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "showAppointHistory.do")
    public String showAppointHistory(String shopId, String openid){
        Gson gson = new Gson();
        Map map = new HashMap();
        List<CustomerAppointment> history = customerService.getLastAppointHistory(shopId,openid);
        if (history.size() > 0) {
            map.put("historyState", 1);
            map.put("pets", history.get(0).getPetLists());
        } else {
            map.put("historyState", 0);
        }
        return gson.toJson(map);
    }

    /**
     * 客户取消预约
     * @param customerAppointment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "cancelApponitment.do")
    public String cancelOrDoneApponitment(@RequestBody CustomerAppointment customerAppointment){
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        boolean flag = customerService.cancelOrDoneApponitment(customerAppointment);
        if (flag) {
            map.put("type", 1);
        } else {
            map.put("type", 0);
        }

        return gson.toJson(map);
    }

}
