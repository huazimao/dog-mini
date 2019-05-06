package com.kingmao.dog.appointment.customer.service.impl;

import com.kingmao.dog.appointment.customer.mapper.ClientMapper;
import com.kingmao.dog.appointment.customer.mapper.CustomerAppointmentMapper;
import com.kingmao.dog.appointment.customer.mapper.PetAppointmentMapper;
import com.kingmao.dog.appointment.customer.model.Client;
import com.kingmao.dog.appointment.customer.model.CustomerAppointment;
import com.kingmao.dog.appointment.customer.model.PetAppointment;
import com.kingmao.dog.appointment.customer.service.CustomerService;
import com.kingmao.dog.appointment.provider.mapper.ProviderCountMapper;
import com.kingmao.dog.appointment.provider.model.ProviderCount;
import com.kingmao.dog.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Paceage:com.kingmao.dog.appointment.customer.service.impl
 * Description:
 * Date:2019/2/21
 * Author: KingMao
 **/
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerAppointmentMapper customerAppointmentMapper;
    @Autowired
    private PetAppointmentMapper petAppointmentMapper;
    @Autowired
    private ProviderCountMapper providerCountMapper;
    @Autowired
    private ClientMapper clientMapper;

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
        Date appointmenTime = DateUtil.getYMD(new Date());
        customerAppointment.setOppointmentTime(appointmenTime);
        boolean flag = false;
        if (customerAppointment.getAppointmentId() == null) {
            log.info("今日首次预约，生成记录");
            //插入客户预约表
            customerAppointment.setAppointmentState(1);
            Integer ret = customerAppointmentMapper.insertSelective(customerAppointment) ;
            if (ret > 0) {
                Integer appId = customerAppointment.getAppointmentId();
                for (int x = 0;x<petAppointmentList.size();x++) {
                    petAppointmentList.get(x).setAppointmentId(appId);
                }
                flag = petAppointmentMapper.insertPetAppList(petAppointmentList) > 0;
            }
        }else {
            log.info("今日有预约，修改记录");
            //先删除该客户之前的宠物预约记录
            Integer appointmentId = customerAppointment.getAppointmentId();
            Integer ret  = petAppointmentMapper.deleteByPrimaryKey(appointmentId);
            if (ret > 0) {
                for (int x = 0;x<petAppointmentList.size();x++) {
                    petAppointmentList.get(x).setAppointmentId(customerAppointment.getAppointmentId());
                }
                flag = petAppointmentMapper.insertPetAppList(petAppointmentList)>0 && customerAppointmentMapper.updateByPrimaryKeySelective(customerAppointment) >0;
            }
        }
        return flag;
    }

    /**
     * 查询客户是否预约和预约详情
     * @param customerAppointment
     * @return
     */
    @Override
    public CustomerAppointment getAppInfo(CustomerAppointment customerAppointment) {
        return customerAppointmentMapper.getAppInfo(customerAppointment);
    }


    /**
     * 查询客户是否预约和预约详情
     * @param customerAppointment
     * @return
     */
    @Override
    public CustomerAppointment getAppInfo2(CustomerAppointment customerAppointment) {
        return customerAppointmentMapper.getAppInfo2(customerAppointment);
    }


    @Override
    public List<CustomerAppointment> showAppointmentByTimeAndShop(String shopId, Date workTime) {
        return customerAppointmentMapper.showAppointmentByTimeAndShop(shopId, workTime);
    }

    /**
     * 修改预约（修改，取消，完成预约都是这个接口）
     * @param customerAppointment
     * @return
     */
    @Override
    public boolean cancelOrDoneApponitment(CustomerAppointment customerAppointment) {
        return customerAppointmentMapper.updateByPrimaryKeySelective(customerAppointment) > 0;
    }

    /**
     * 查询最后一次预约记录
     * @param openid
     * @return
     */
    @Override
    public List<CustomerAppointment> getLastAppointHistory(String shopId,String openid) {
        return customerAppointmentMapper.getLastAppointHistory(shopId ,openid);
    }

    /**
     * 插入客户信息
     * @param client
     * @return
     */
    @Override
    public boolean insertClient(Client client) {
        boolean flag = false;
        if (clientMapper.getClientByOpenid(client.getOpenid()) == null) {
            flag = clientMapper.insertSelective(client) >0;
        }else {
            flag = clientMapper.updateByPrimaryKeySelective(client)>0;
        }
        return flag;
    }

    /**
     * 客户修改预约信息
     * @param customerAppointment
     * @return
     */
    @Override
    public boolean updateAppointment(CustomerAppointment customerAppointment) {
        return customerAppointmentMapper.updateByPrimaryKeySelective(customerAppointment)>0;
    }
}
