package com.kingmao.dog.appointment.provider.controller;

import com.kingmao.dog.appointment.customer.model.CustomerAppointment;
import com.kingmao.dog.appointment.customer.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

/**
 * Paceage:com.kingmao.dog.appointment.provider.controller
 * Description:
 * 商家服务后台，监测预约状态，预约详细列表
 * Date:2019/2/21
 * Author: KingMao
 **/
@Controller
public class ProviderController {
    private static Logger log = Logger.getLogger(ProviderController.class);

    @Autowired
    private CustomerService customerService;

    public String showAppointmentByTimeAndShop(String shopId, Date workTime){
        List<CustomerAppointment> customerAppointmentList = customerService.showAppointmentByTimeAndShop(shopId, workTime);
        return null;
    }





}
