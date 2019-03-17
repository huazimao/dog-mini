package com.kingmao.dog.appointment.admin.service;

import com.kingmao.dog.appointment.admin.model.SysUser;

/**
 * Paceage:com.kingmao.dog.appointment.admin.service
 * Description:
 * Date:2019/3/14
 * Author: KingMao
 **/
public interface AdminService {
    SysUser getUserByPWD(String usernmae,String password);

}
