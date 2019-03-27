package com.kingmao.dog.appointment.provider.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingmao.dog.appointment.provider.model.SystemSetting;
import com.kingmao.dog.appointment.provider.service.SystemSettingService;
import com.kingmao.dog.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Paceage:com.kingmao.dog.appointment.provider.controller
 * Description:
 * Date:2019/2/21
 * Author: KingMao
 **/
@RequestMapping("/provider/")
@Controller
public class SystemController {
    @Autowired
    private SystemSettingService systemSettingService;

    private static Logger log = Logger.getLogger(ProviderController.class);

    /**
     * 基本设置
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveOrUptSetting.do")
    //public String calculateServiceTime(Date workTime, Integer traineeNum, Double traineeFactor,Integer skillerNum,Double skillerFactor, Date serviceStartTime,Date serviceEndTime) {
//    public String calculateServiceTime(Date workTime, Integer traineeNum, Double traineeFactor,Integer skillerNum,Double skillerFactor,Date serviceStartTime) {
    public String calculateServiceTime(HttpServletRequest request) {
        String work_time = request.getParameter("workTime");
        String service_start_time = request.getParameter("serviceStartTime");
        String service_end_time = request.getParameter("serviceEndTime");
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer switchStatue = Integer.parseInt(request.getParameter("switchStatue"));
        String board = request.getParameter("board");

        SystemSetting systemSetting = new SystemSetting();
        systemSetting.setId(id);
        systemSetting.setWorkTime(DateUtil.str2Date(work_time));
        systemSetting.setServiceStartTime(DateUtil.str2Date(service_start_time));
        systemSetting.setServiceEndTime(DateUtil.str2Date(service_end_time));
        systemSetting.setSubmitTime(new Date());
        systemSetting.setBoard(board);
        systemSetting.setSwitchStatue(switchStatue);

        return systemSettingService.saveOrUptSetting(systemSetting) ? "1" : "0";

    }

    /**
     * 根据店铺ID和生效时间查找默认设置
     * @return
     */
    @ResponseBody
    @RequestMapping("getSySettingByShopIdAndTime.do")
    public String getSySettingByShopIdAndTime(HttpServletRequest request){
        String work_time = request.getParameter("workTime");
        Date workTime = DateUtil.getYMD2(DateUtil.str2Date(work_time));
        String shopId = request.getParameter("shopId");
        Gson gson = new Gson();
        Map map = new HashMap();
        SystemSetting systemSetting = systemSettingService.getSySettingByShopIdAndTime(shopId,workTime);
        if (systemSetting != null) {
            systemSetting.setEndStr(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create().toJson(systemSetting.getServiceEndTime()));
            systemSetting.setStartStr(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create().toJson(systemSetting.getServiceStartTime()));
        }
        map.put("systemSetting", systemSetting);
        return gson.toJson(map);
    }

    /**
     * 跳转店铺设置中间页
     * @return
     */
    @RequestMapping("go2defaultSettingPage.do")
    public String getDog(HttpServletRequest request){
        request.setAttribute("shopId",request.getParameter("shopId"));
        return "provider/sysetting";
    }
}
