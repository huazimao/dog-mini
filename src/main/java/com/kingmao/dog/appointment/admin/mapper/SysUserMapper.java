package com.kingmao.dog.appointment.admin.mapper;

import com.kingmao.dog.appointment.admin.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper {

    SysUser getUserByPWD(@Param("username") String usernmae, @Param("password") String password);
}