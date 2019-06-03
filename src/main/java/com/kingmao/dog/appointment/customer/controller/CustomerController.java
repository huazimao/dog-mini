package com.kingmao.dog.appointment.customer.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kingmao.dog.appointment.customer.model.CustomerAppointment;
import com.kingmao.dog.appointment.customer.model.PetAppointment;
import com.kingmao.dog.appointment.customer.service.CustomerService;
import com.kingmao.dog.appointment.provider.model.SystemSetting;
import com.kingmao.dog.appointment.provider.service.SystemSettingService;
import com.kingmao.dog.utils.DateUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.*;

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
        log.info("进入到客户预约界面，收到参数为：" + "shopId=" + customerAppointment.getShopId() + "workTime=" + customerAppointment.getWorkTime() + "openid=" + customerAppointment.getOpenid());
        String shopId = customerAppointment.getShopId();
        Date workTime = customerAppointment.getWorkTime();
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();

        //查询当日该店总开关
        SystemSetting systemSetting = systemSettingService.getSySettingByShopIdAndTime(shopId, workTime);
        int sysStatue = systemSetting.getSwitchStatue();
        String board = systemSetting.getBoard();

        if (sysStatue == 1) {
            //只对今天的预约时间做判断
            if (systemSetting.getWorkTime().toString().equals(DateUtil.getYMD2(new Date()).toString())){
                if(DateUtil.getYMD2HMS(new Date()).before(DateUtil.getYMD2HMS(systemSetting.getServiceStartTime()))){
                    sysStatue = 0;
                    board = "今日预约系统暂未开放，请稍后再试。";
                }
                if (DateUtil.getYMD2HMS(new Date()).after(DateUtil.getYMD2HMS(systemSetting.getServiceEndTime()))){
                    sysStatue = 0;
                    board = "今日预约系统已关闭，请选择其他时间。";
                }
            }
            //查询客户是否预约和预约详情
            customerAppointment = customerService.getAppInfo2(customerAppointment);
            if (null != customerAppointment) {
                map.put("isApp",1); //isApp 是否预约过
                //转换时间
                customerAppointment.setOppointmentTimeStr(DateUtil.date2StrCN(customerAppointment.getOppointmentTime()));
                map.put("CustomerAppointment",customerAppointment);
            } else {
                map.put("isApp",0);
            }
        }
        map.put("sysStatue",sysStatue);
        map.put("board",board);

        return gson.toJson(map);
    }

    /**
     * 客户预约/修改洗护服务
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "appointment.do" ,produces = {"application/json;charset=UTF-8"})
    public String getAppointment(HttpServletRequest request){
        Date workTime = DateUtil.str3Date(request.getParameter("workTime"));
        CustomerAppointment customerAppointment = new CustomerAppointment();
        customerAppointment.setShopId(request.getParameter("shopId"));
        customerAppointment.setOpenid(request.getParameter("openid"));
        customerAppointment.setPhone(request.getParameter("mobile"));
        customerAppointment.setDtype(request.getParameter("dtype"));
        customerAppointment.setWorkTime(workTime);
        String appIdStr = request.getParameter("appointmentId");
        if (appIdStr != null && !appIdStr.equals(" ") && !appIdStr.equals("") && !appIdStr.equals("null")){
            customerAppointment.setAppointmentId(Integer.parseInt(appIdStr));
        }
        //customerAppointment.setWorkTime(DateUtil.str2Date(request.getParameter("workTime")));
        String paramList = request.getParameter("petLists");
        List<PetAppointment> petList = new Gson().fromJson(paramList,new TypeToken<List<PetAppointment>>() {}.getType());
        /*for (int x = 0;x<petList.size();x++) {
            if (petList.get(x).getSize().trim().isEmpty()) {
                log.info("=======" + petList.get(x).getSize());
                petList.remove(x);
            }
        }*/

        Iterator<PetAppointment> it = petList.iterator();
        while(it.hasNext()){
            PetAppointment x = it.next();
            if(x.getKindPet().isEmpty()){
                it.remove();
            }
        }

        customerAppointment.setPetLists(petList);
        log.info("最终参数：" + customerAppointment.toString());
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        if (customerService.insertAppointment(customerAppointment)){
            map.put("type", 1);
        }else {
            map.put("type", 1);
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
            map.put("phone", history.get(0).getPhone());
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
    public String cancelOrDoneApponitment(CustomerAppointment customerAppointment){
        customerAppointment.setAppointmentState(4);
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
