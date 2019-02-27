package com.kingmao.dog.appointment.provider.service.impl;

import com.kingmao.dog.appointment.provider.mapper.ProviderCountMapper;
import com.kingmao.dog.appointment.provider.model.ProviderCount;
import com.kingmao.dog.appointment.provider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Paceage:com.kingmao.dog.appointment.provider.service.impl
 * Description:
 * Date:2019/2/25
 * Author: 伊秦轩
 **/
@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderCountMapper providerCountMapper;

    @Override
    public int insertProviderCount(ProviderCount providerCount) {
        return 0;
    }

    @Override
    public int updateProviderCount(ProviderCount providerCount) {
        return providerCountMapper.updateByPrimaryKeySelective(providerCount);
    }

    @Override
    public ProviderCount getPorivderCountInfo(String shopId, Date workTime) {
        return providerCountMapper.getPorivderCountInfo(shopId,workTime);
    }
}
