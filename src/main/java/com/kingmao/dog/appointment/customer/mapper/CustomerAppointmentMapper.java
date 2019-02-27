package com.kingmao.dog.appointment.customer.mapper;

import com.kingmao.dog.appointment.customer.model.CustomerAppointment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerAppointmentMapper {
    int deleteByPrimaryKey(Integer appointmentId);

    int insert(CustomerAppointment record);

    int insertSelective(CustomerAppointment record);

    CustomerAppointment selectByPrimaryKey(Integer appointmentId);

    int updateByPrimaryKeySelective(CustomerAppointment record);

    int updateByPrimaryKey(CustomerAppointment record);

    List<CustomerAppointment> getAll();

    // 根据客户openId，店铺ID，工作日查询详情
    CustomerAppointment getAppInfo(CustomerAppointment customerAppointment);

}