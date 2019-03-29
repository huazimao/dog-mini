package com.kingmao.dog.appointment.admin.controller;

import com.google.gson.Gson;
import com.kingmao.dog.appointment.admin.model.SysUser;
import com.kingmao.dog.appointment.admin.service.AdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
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

    /**
     * 登陆
     * @param request
     * @param response
     * @param username
     * @param password
     * @return
     */
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
            Cookie username_cookie = new Cookie("username", URLEncoder.encode(username));
            username_cookie.setMaxAge(7*24*60*60);
            Cookie password_cookie  = new Cookie("password", URLEncoder.encode(password));
            password_cookie.setMaxAge(7*24*60*60);
            response.addCookie(username_cookie);
            response.addCookie(password_cookie);

            map.put("type", 1);
            map.put("shopId", sysUser.getShopid());
            map.put("msg", "普通员工登陆成功");
        } else if (sysUser.getLevel() == 2) {
            request.getSession().setAttribute("sysUser", sysUser);
            request.getSession().setAttribute("level", sysUser.getLevel());
            Cookie username_cookie = new Cookie("username", URLEncoder.encode(username));
            username_cookie.setMaxAge(7*24*60*60);
            Cookie password_cookie  = new Cookie("password", URLEncoder.encode(password));
            password_cookie .setMaxAge(7*24*60*60);
            response.addCookie(username_cookie);
            response.addCookie(password_cookie);

            map.put("type", 2);
            map.put("msg", "管理员登陆成功");
        }
        return gson.toJson(map);
    }

    /**
     * 伪cookie7天免密登陆，进入登录页后自动登陆
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "loginCookie.do", method = RequestMethod.GET)
    public String loginCookie(HttpServletRequest request) {
        String username = null;
        String password = null;
        Cookie[] cookie = request.getCookies();
        if (cookie != null) {
            for (Cookie c : cookie) {
                log.info("name:" + c.getName() + "===" + "value:" + c.getValue());
                if (c.getName().equals("username")) {
                    username = c.getValue();
                }
                if (c.getName().equals("password")) {
                    password = c.getValue();
                }
            }
        }


        log.info("进入到cookie登陆后台,从cookie获取值：" + username + password);
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();

        SysUser sysUser = adminService.getUserByPWD(username, password);
        if (null == sysUser) {
            map.put("type", 0);
            map.put("msg", "无cookie或密码已更改");
            return gson.toJson(map);
        }
        if (sysUser.getLevel() == 1) {
            request.getSession().setAttribute("sysUser", sysUser);
            map.put("type", 1);
            map.put("shopId", sysUser.getShopid());
        } else if (sysUser.getLevel() == 2) {
            request.getSession().setAttribute("sysUser", sysUser);
            map.put("type", 2);
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
     * 管理员工登陆中间页
     * 管理员登陆后跳转选择店铺页面
     * @param request
     * @return
     */
    @RequestMapping("power.do")
    public String power(HttpServletRequest request){
        log.info("进入到powerl后台");
        return "/provider/index";
    }
}
