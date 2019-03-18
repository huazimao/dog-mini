package com.kingmao.dog.appointment.interceptor;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Paceage:com.kingmao.dog.appointment.interceptor
 * Description:
 * Date:2019/3/15
 * Author: KingMao
 **/
@Component
public  class SysUserInterceptor implements HandlerInterceptor {
    private static Logger log = Logger.getLogger(SysUserInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        log.info("-------------进入拦截器-----------");
        if (request.getSession().getAttribute("sysUser") == null) {
            log.info("-------------无权限，返回登陆页-----------");
            response.sendRedirect(request.getContextPath() + "/index.html");
            return false;
        } else {
            log.info("-------------有权限，执行下一步-----------");
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
