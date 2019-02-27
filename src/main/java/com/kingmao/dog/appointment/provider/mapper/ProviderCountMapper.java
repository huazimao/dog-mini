package com.kingmao.dog.appointment.provider.mapper;

import com.kingmao.dog.appointment.provider.model.ProviderCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
@Mapper
public interface ProviderCountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProviderCount record);

    int insertSelective(ProviderCount record);

    ProviderCount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProviderCount record);

    int updateByShopIdAndTime(@Param("shopId") String shopId, @Param("workTime")Date workTine);

    int updateByPrimaryKey(ProviderCount record);

    ProviderCount getPorivderCountInfo(@Param("shopId") String shopId, @Param("workTime")Date workTine);

    int insertByShopIdAdnTime(@Param("shopId") String shopId, @Param("workTime")Date workTine);
}