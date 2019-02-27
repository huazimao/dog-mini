package com.kingmao.dog.appointment.customer.service.impl;

import com.kingmao.dog.appointment.customer.mapper.CustomerAppointmentMapper;
import com.kingmao.dog.appointment.customer.mapper.PetAppointmentMapper;
import com.kingmao.dog.appointment.customer.model.CustomerAppointment;
import com.kingmao.dog.appointment.customer.model.PetAppointment;
import com.kingmao.dog.appointment.customer.service.CustomerService;
import com.kingmao.dog.appointment.provider.mapper.ProviderCountMapper;
import com.kingmao.dog.appointment.provider.model.ProviderCount;
import com.kingmao.dog.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Paceage:com.kingmao.dog.appointment.customer.service.impl
 * Description:
 * Date:2019/2/21
 * Author: 伊秦轩
 **/
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerAppointmentMapper customerAppointmentMapper;
    @Autowired
    private PetAppointmentMapper petAppointmentMapper;
    @Autowired
    private ProviderCountMapper providerCountMapper;
    private static Logger log = Logger.getLogger(CustomerServiceImpl.class);

    @Override
    public List<CustomerAppointment> getAll() {
        return customerAppointmentMapper.getAll();
    }

    /**
     * 记录和修改走的是同一套
     * 先处理客户预约，再处理宠物列表
     * 最后处理总预约消耗时间
     * @param customerAppointment
     * @return
     */
    @Override
    public boolean insertAppointment(CustomerAppointment customerAppointment) {
        List<PetAppointment> petAppointmentList = customerAppointment.getPetLists();
        Integer consumeTime = getConsumeTime(petAppointmentList);
        log.info(customerAppointment.getClientName() + "用户洗护预计耗时：" + consumeTime);
        customerAppointment.setConsumeTime(consumeTime);
        Date appointmenTime = DateUtil.getYMD(new Date());
        customerAppointment.setOppointmentTime(appointmenTime);
        customerAppointment.setCountFinishedTime(DateUtil.getPlus(appointmenTime,consumeTime));
        boolean flag;
        if (customerAppointment.getAppointmentId() == null) {
            //插入客户预约表
            flag = customerAppointmentMapper.insertSelective(customerAppointment) > 0;
        }else {
            //先删除该客户之前的宠物预约记录
            int appointmentId = customerAppointment.getAppointmentId();
            flag = petAppointmentMapper.deleteByPrimaryKey(appointmentId) > 0;
        }
        //更新宠物表和客户预约表中的消耗时间
        if (flag) {
            flag = petAppointmentMapper.insertPetAppList(petAppointmentList)>0 && customerAppointmentMapper.updateByPrimaryKeySelective(customerAppointment) >0;
        }
        //更新商家预约总表
        if (flag) {
            ProviderCount providerCount = providerCountMapper.getPorivderCountInfo(customerAppointment.getShopId(),customerAppointment.getWorkTime());
            //更新
            if (providerCount.getId() != null) {
                providerCountMapper.updateByShopIdAndTime(customerAppointment.getShopId(), customerAppointment.getWorkTime());
            //插入
            }else {
                providerCountMapper.insertByShopIdAdnTime(customerAppointment.getShopId(), customerAppointment.getWorkTime());
            }
        }

        return flag;
    }

    @Override
    public CustomerAppointment getAppInfo(CustomerAppointment customerAppointment) {
        return customerAppointmentMapper.getAppInfo(customerAppointment);
    }

    /**
     * 计算该客户预约的宠物洗护需要的耗时
     * @param petAppointmentList
     * @return
     */
    public Integer getConsumeTime(List<PetAppointment> petAppointmentList) {
        Integer consumeTime = 0;
        for (int x = 0; x < petAppointmentList.size(); x++) {
            PetAppointment pet = petAppointmentList.get(x);
            if (pet.getKindPet().equals("cat")) {
                consumeTime += 30;
            } else if (pet.getKindPet().equals("dog")) {
                if (pet.getKindService().equals("wash")) {
                    if (pet.getSize().equals("mini")) {
                        consumeTime += 30;
                    } else if (pet.getSize().equals("normal")) {
                        consumeTime += 40;
                    } else if (pet.getSize().equals("large")) {
                        consumeTime += 50;
                    }
                } else if (pet.getKindService().equals("modeling")) {
                    consumeTime += 120;
                }
            }
        }

        return consumeTime;
    }
}
