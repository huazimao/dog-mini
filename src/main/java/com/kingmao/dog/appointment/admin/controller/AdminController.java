package com.kingmao.dog.appointment.admin.controller;

import com.google.gson.Gson;
import com.kingmao.dog.appointment.admin.model.SysUser;
import com.kingmao.dog.appointment.admin.service.AdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Paceage:com.kingmao.dog.appointment.admin
 * Description:
 * Date:2019/3/14
 * Author: KingMao
 **/
@Controller
@RequestMapping("/admin/")
public class AdminController {
    private static Logger log = Logger.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "login.do", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, String username, String password) {
        log.info("进入到登陆后台");
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();

        SysUser sysUser = adminService.getUserByPWD(username, password);
        if (null == sysUser) {
            map.put("type", 0);
            map.put("msg", "账号或密码错误");
            return gson.toJson(map);
        }
        if (sysUser.getLevel() == 1) {
            request.getSession().setAttribute("sysUser", sysUser);
            map.put("type", 1);
            map.put("msg", "普通员工登陆成功");
        } else if (sysUser.getLevel() == 2) {
            request.getSession().setAttribute("sysUser", sysUser);
            map.put("type", 2);
            map.put("msg", "管理员登陆成功");
        }
        return gson.toJson(map);
    }

    /**
     * 普通员工登陆中间页
     * @param request
     * @return
     */
    @RequestMapping("normal.do")
    public String normal(HttpServletRequest request){
        log.info("进入到normal后台");
        return "/provider/info";
    }

    /**
     * 普通员工登陆中间页
     * 管理员登陆后跳转选择店铺页面
     * @param request
     * @return
     */
    @RequestMapping("power.do")
    public String power(HttpServletRequest request){
        log.info("进入到normal后台");
        return "/provider/index";
    }
}
