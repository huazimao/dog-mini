package com.kingmao.dog.appointment.provider.service;

import com.kingmao.dog.appointment.provider.model.SystemSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Paceage:com.kingmao.dog.appointment.provider.service
 * Description:
 * Date:2019/2/21
 * Author: KingMao
 **/
public interface SystemSettingService {
    boolean insertSysSetting(SystemSetting systemSetting);

    boolean saveOrUptSetting(SystemSetting systemSetting);

    SystemSetting getSySettingByShopIdAndTime(@Param("shopId") String shopId, @Param("workTime") Date workTime);
}
