package com.kingmao.dog.appointment.customer.mapper;

import com.kingmao.dog.appointment.customer.model.PetAppointment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetAppointmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PetAppointment record);

    int insertSelective(PetAppointment record);

    PetAppointment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PetAppointment record);

    int updateByPrimaryKey(PetAppointment record);

    int insertPetAppList(List<PetAppointment> petAppointments);

    int deleteByAppointmentId(Integer appointmentId);
}