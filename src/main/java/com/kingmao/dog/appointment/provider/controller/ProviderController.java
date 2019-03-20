package com.kingmao.dog.appointment.provider.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingmao.dog.appointment.customer.model.CustomerAppointment;
import com.kingmao.dog.appointment.customer.service.CustomerService;
import com.kingmao.dog.appointment.wechat.CoreApi;
import com.kingmao.dog.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Paceage:com.kingmao.dog.appointment.provider.controller
 * Description:
 * 商家服务后台，监测预约状态，预约详细列表
 * Date:2019/2/21
 * Author: KingMao
 **/
@Controller
@RequestMapping("/provider/")
public class ProviderController {
    private static Logger log = Logger.getLogger(ProviderController.class);
    @Value("${mini.template_id}")
    private String templateId;

    @Autowired
    private CustomerService customerService;

    /**
     * 商家预约后台，展示预约详情以及完成状态
     * @param shopId
     * @param workTime
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "showAppointmentByTimeAndShop.do", method = {RequestMethod.GET,RequestMethod.POST})
    public String showAppointmentByTimeAndShop(HttpServletRequest request,String shopId, Date workTime){
        log.info("进入到商家后台");
        Gson gson = new Gson();
        Map map = new HashMap();
        if (null == workTime) {
            workTime = new Date();
        }
        List<CustomerAppointment> customerAppointmentList = customerService.showAppointmentByTimeAndShop(shopId, workTime);
        map.put("list", customerAppointmentList);
        return gson.toJson(map);
    }


    /**
     * 商家完成服务
     * @param customerAppointment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "cancelOrDoneApponitment.do",produces = {"application/json;charset=UTF-8"})
    public String cancelOrDoneApponitment(@RequestBody CustomerAppointment customerAppointment){
        Gson gson = new Gson();
        String ret = "fail";
        Map<String, Object> map = new HashMap<String, Object>();
        boolean flag = customerService.cancelOrDoneApponitment(customerAppointment);

        //完成洗护，发送模板消息
        if (flag) {
            ret = CoreApi.sendTemplateMessage(dealMsg(customerAppointment));
            if (ret.equals("success")) {
                map.put("type", 1);
            } else {
                map.put("type", 2);
                map.put("msg", "发送通知失败，请人工联系客户。");
            }
        } else {
            map.put("type", 0);
            map.put("msg", "更改洗护状态失败，请人工联系客户。");
        }

        return gson.toJson(map);
    }

    public String dealMsg(CustomerAppointment customerAppointment){
        String shopId = customerAppointment.getShopId();
        String accTime = DateUtil.date2Str(DateUtil.getYMD(new Date()));
        String shopName = "";
        switch (shopId) {
            case "ls":
                shopName = "龙山店";
                break;
            case "lj":
                shopName = "龙江店";
                break;
        }

        String msg = "{\n" +
                "\t\"touser\": "+customerAppointment.getOpenid()+",\n" +
                "\t\"template_id\": "+templateId+",\n" +
                "\t\"form_id\": "+customerAppointment.getFormId()+",\n" +
                "\t\"data\": {\n" +
                "\t\t\"keyword1\": {\n" +
                "\t\t\t\"value\": \"洗护服务\",\n" +
                "\t\t\t\"color\": \"#4a4a4a\"\n" +
                "\t\t},\n" +
                "\t\t\"keyword2\": {\n" +
                "\t\t\t\"value\": "+shopName+",\n" +
                "\t\t\t\"color\": \"#9b9b9b\"\n" +
                "\t\t},\n" +
                "\t\t\"keyword3\": {\n" +
                "\t\t\t\"value\": "+new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create().toJson(new Date())+",\n" +
                "\t\t\t\"color\": \"#9b9b9b\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";

        return msg;
    }





}
