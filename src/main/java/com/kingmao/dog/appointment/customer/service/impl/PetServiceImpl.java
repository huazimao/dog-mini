package com.kingmao.dog.appointment.customer.service.impl;

import com.kingmao.dog.appointment.customer.mapper.PetAppointmentMapper;
import com.kingmao.dog.appointment.customer.model.PetAppointment;
import com.kingmao.dog.appointment.customer.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Paceage:com.kingmao.dog.appointment.customer.service.impl
 * Description:
 * Date:2019/2/25
 * Author: KingMao
 **/
@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private PetAppointmentMapper petAppointmentMapper;
    @Override
    public int insertPetAppointment(PetAppointment petAppointment) {
        return petAppointmentMapper.insertSelective(petAppointment);
    }

    @Override
    public int insertPetAppList(List<PetAppointment> petAppointments) {
        return 0;
    }
}
