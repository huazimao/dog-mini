package com.kingmao.dog.appointment.provider.mapper;

import com.kingmao.dog.appointment.provider.model.ProviderAppointment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProviderAppointmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProviderAppointment record);

    int insertSelective(ProviderAppointment record);

    ProviderAppointment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProviderAppointment record);

    int updateByPrimaryKey(ProviderAppointment record);
}