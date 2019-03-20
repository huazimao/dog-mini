package com.kingmao.dog.appointment.customer.service;

import com.kingmao.dog.appointment.customer.model.CustomerAppointment;

import java.util.Date;
import java.util.List;

/**
 * Paceage:com.kingmao.dog.appointment.customer.service
 * Description:
 * Date:2019/2/21
 * Author: KingMao
 **/
public interface CustomerService {
    List<CustomerAppointment> getAll();

    //记录预约
    boolean insertAppointment(CustomerAppointment customerAppointment);

    //查询该客户在某天某店的预约详情
    CustomerAppointment getAppInfo(CustomerAppointment customerAppointment);

    //查询某天某店服务详情
    List<CustomerAppointment> showAppointmentByTimeAndShop(String shopId, Date workTime);

    boolean cancelOrDoneApponitment(CustomerAppointment customerAppointment);

    //查询最后一次预约记录
    List<CustomerAppointment> getLastAppointHistory(String shopId,String openid);
}
