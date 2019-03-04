package com.kingmao.dog.appointment.customer.service;

import com.kingmao.dog.appointment.customer.model.PetAppointment;

import java.util.List;

/**
 * Paceage:com.kingmao.dog.appointment.customer.service
 * Description:
 * Date:2019/2/25
 * Author: KingMao
 **/
public interface PetService {
    int insertPetAppointment(PetAppointment petAppointment);

    int insertPetAppList(List<PetAppointment> petAppointments);
}
