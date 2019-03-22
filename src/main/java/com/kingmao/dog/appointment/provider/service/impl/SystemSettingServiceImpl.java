package com.kingmao.dog.appointment.provider.service.impl;

import com.kingmao.dog.appointment.provider.mapper.ProviderCountMapper;
import com.kingmao.dog.appointment.provider.mapper.SystemSettingMapper;
import com.kingmao.dog.appointment.provider.model.ProviderCount;
import com.kingmao.dog.appointment.provider.model.SystemSetting;
import com.kingmao.dog.appointment.provider.service.SystemSettingService;
import com.kingmao.dog.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Paceage:com.kingmao.dog.appointment.provider.service.impl
 * Description:
 * Date:2019/2/21
 * Author: KingMao
 **/
@Service
public class SystemSettingServiceImpl implements SystemSettingService {
    @Autowired
    private SystemSettingMapper systemSettingMapper;
    @Autowired
    private ProviderCountMapper providerCountMapper;
    private static Logger log = Logger.getLogger(SystemSettingServiceImpl.class);

    /**
     * 生成默认设置
     * @param systemSetting
     * @return
     */
    @Override
    public boolean insertSysSetting(SystemSetting systemSetting) {
        return systemSettingMapper.insertSelective(systemSetting) > 0;
    }

    /**
     * 更新默认设置
     * @param systemSetting
     * @return
     */
    @Override
    public boolean saveOrUptSetting(SystemSetting systemSetting) {
        boolean flag;
        //修改基本设置
        if (systemSetting.getId() != null) {
            flag = systemSettingMapper.updateByPrimaryKeySelective(systemSetting) > 0;
        }
        //插入基本设置
        else {
            flag = systemSettingMapper.insertSelective(systemSetting) > 0;
        }

        return flag;
    }

    /**
     * 根据店铺ID和生效时间查找默认设置
     * @param shopId
     * @return
     */
    @Override
    public SystemSetting getSySettingByShopIdAndTime(String shopId,Date workTime) {
        return systemSettingMapper.getSySettingByShopIdAndTime(shopId,workTime);
    }
}
