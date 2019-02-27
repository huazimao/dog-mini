package com.kingmao.dog.appointment.provider.mapper;

import com.kingmao.dog.appointment.provider.model.SystemSetting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface SystemSettingMapper {

    int insertSelective(SystemSetting record);

    SystemSetting getSySettingByShopIdAndTime(@Param("shopId") String shopId, @Param("workTime")Date workTine);

    int updateByPrimaryKeySelective(SystemSetting record);

}