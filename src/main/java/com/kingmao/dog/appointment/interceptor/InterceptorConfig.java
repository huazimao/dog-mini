package com.kingmao.dog.appointment.interceptor;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@SpringBootConfiguration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Resource
    private SysUserInterceptor sysUserInterceptor;

    /**
     * addPathPatterns() 拦截的路径
     * excludePathPatterns() 排除拦截的路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sysUserInterceptor).addPathPatterns("/admin/normal.do","/admin/power.do","/provider/*");
                //.excludePathPatterns("/login.html", "/login","admin","index.html");
        super.addInterceptors(registry);
    }
}
