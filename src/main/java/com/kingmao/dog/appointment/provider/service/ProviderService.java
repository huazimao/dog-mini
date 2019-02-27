package com.kingmao.dog.appointment.provider.service;

import com.kingmao.dog.appointment.provider.model.ProviderCount;

import java.util.Date;

/**
 * Paceage:com.kingmao.dog.appointment.provider.service
 * Description:
 * Date:2019/2/25
 * Author: 伊秦轩
 **/
public interface ProviderService {
    int insertProviderCount(ProviderCount providerCount);

    int updateProviderCount(ProviderCount providerCount);

    ProviderCount getPorivderCountInfo(String shopId, Date workTime,String dtype);
}
