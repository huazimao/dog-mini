package com.kingmao.dog.appointment.customer.mapper;

import com.kingmao.dog.appointment.customer.model.Client;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClientMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Client record);

    int insertSelective(Client record);

    Client selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);

    Client getClientByOpenid(String openid);
}