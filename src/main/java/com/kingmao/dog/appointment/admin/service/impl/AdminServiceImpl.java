package com.kingmao.dog.appointment.admin.service.impl;

import com.kingmao.dog.appointment.admin.mapper.SysUserMapper;
import com.kingmao.dog.appointment.admin.model.SysUser;
import com.kingmao.dog.appointment.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Paceage:com.kingmao.dog.appointment.admin.service.impl
 * Description:
 * Date:2019/3/14
 * Author: KingMao
 **/
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserByPWD(String usernmae, String password) {
        return sysUserMapper.getUserByPWD(usernmae,password);
    }
}
