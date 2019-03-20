package com.kingmao.dog.appointment.customer.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingmao.dog.appointment.cacha.SysCacha;
import com.kingmao.dog.appointment.customer.model.CustomerAppointment;
import com.kingmao.dog.appointment.customer.service.CustomerService;
import com.kingmao.dog.appointment.provider.model.ProviderCount;
import com.kingmao.dog.appointment.provider.model.SystemSetting;
import com.kingmao.dog.appointment.provider.service.ProviderService;
import com.kingmao.dog.appointment.provider.service.SystemSettingService;
import com.kingmao.dog.appointment.wechat.CoreApi;
import com.kingmao.dog.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    private ProviderService providerService;
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
     * @param request
     * @param customerAppointment
     * @return
     */
    @RequestMapping("appointmentPage.do")
    public String appointmentPage(HttpServletRequest request, CustomerAppointment customerAppointment) {
        log.info("进入到客户预约界面，收到参数为：" + "shopId=" + customerAppointment.getShopId() + "workTime=" + customerAppointment.getWorkTime());
        String shopId = customerAppointment.getShopId();
        Date workTime = customerAppointment.getWorkTime();
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        customerAppointment.setOpenid("opend");

        //查询客户是否预约和预约详情
        customerAppointment = customerService.getAppInfo(customerAppointment);
        if (null != customerAppointment) {
            request.setAttribute("isApp",1); //isApp 是否预约过
            request.setAttribute("CustomerAppointment",customerAppointment);
        } else {
            request.setAttribute("isApp",0);
        }

        //查询当日该店总开关
        SystemSetting systemSetting = systemSettingService.getSySettingByShopIdAndTime(shopId, workTime);
        int sysStatue = systemSetting.getSwitchStatue();
        request.setAttribute("sysStatue",sysStatue);

        //查询某日某店上午是否已约满，及可预约时间
        ProviderCount providerCountAm = providerService.getPorivderCountInfo(shopId, workTime,"am");
        Integer appStatueAm = 0; //某日某店上午时段是否可以预约 1 可以，0不可以

        if (null == providerCountAm) {
            appStatueAm = 1;
            request.setAttribute("appTimeAm", new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create().toJson(systemSetting.getServiceStartTime()));
        } else {
            Integer earnTimeAm = providerCountAm.getEarnTime();
            Integer consumeTimeAm = providerCountAm.getConsumeTime();
            if (consumeTimeAm < earnTimeAm) {
                appStatueAm = 1;
                Date appTimeAm = DateUtil.countRat(systemSetting.getServiceStartTime(),systemSetting.getServiceEndTime(),earnTimeAm, consumeTimeAm,"am");
                Gson gson1 = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
                request.setAttribute("appTimeAm",gson1.toJson(appTimeAm));
            }
        }
        request.setAttribute("appStatueAm",appStatueAm);

        //查询某日某店下午是否已约满，及可预约时间
        ProviderCount providerCountPm = providerService.getPorivderCountInfo(shopId, workTime,"pm");
        Integer appStatuePm = 0; //某日某店上午时段是否可以预约 1 可以，0不可以
        if (null == providerCountPm) {
            appStatuePm = 1;
            request.setAttribute("appTimePm", new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create().toJson(systemSetting.getServiceStartTime()));
        } else {
            Integer earnTimePm = providerCountPm.getEarnTime();
            Integer consumeTimePm = providerCountPm.getConsumeTime();
            if (consumeTimePm< earnTimePm) {
                appStatuePm = 1;
                Date appTimePm = DateUtil.countRat(systemSetting.getServiceStartTime(),systemSetting.getServiceEndTime(),earnTimePm, consumeTimePm,"pm");
                Gson gson2= new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
                request.setAttribute("appTimePm",gson2.toJson(appTimePm));
            }
        }
        request.setAttribute("appStatuePm",appStatuePm);

        return "customer/appointment";
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

        CustomerAppointment retApp = new CustomerAppointment();
        if (customerService.insertAppointment(customerAppointment)){
            map.put("type", 1);
            //retApp = customerService.getAppInfo(customerAppointment);
        }

        //预约前进行二次校验
        //不进行校验，乐于接受预约超的情况
        /*
        ProviderCount providerCount = providerService.getPorivderCountInfo(customerAppointment.getShopId(), customerAppointment.getWorkTime(),customerAppointment.getDtype());
        Integer earnTime = providerCount.getEarnTime();
        Integer consumeTime = providerCount.getConsumeTime();
        if (consumeTime < earnTime) {
        }*/

        return gson.toJson(map);
    }

    @RequestMapping("/customer/index.do")
    public String go2Index(){
        log.info("进入到店铺选择页");
        return "customer/index";
    }

    /**
     * 客户取消预约
     * @param customerAppointment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "cancelOrDoneApponitment.do")
    public String cancelOrDoneApponitment(@RequestBody CustomerAppointment customerAppointment){
        Gson gson = new Gson();
        String ret = "fail";
        Map<String, Object> map = new HashMap<String, Object>();
        boolean flag = customerService.cancelOrDoneApponitment(customerAppointment);
        if (flag) {
            map.put("type", 1);
        } else {
            map.put("type", 0);
        }

        return gson.toJson(map);
    }

    /**
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
}
