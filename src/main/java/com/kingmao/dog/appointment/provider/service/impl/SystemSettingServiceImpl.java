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
        int lefTime = 0;  //当日剩余时间
        int totalTime = 0; //当日工作时常
        int trainEffective = 0; //学徒能效时间
        int skillerEffective = 0; //熟手能效时间
        int earnTime = 0; //能效时间
        //修改基本设置
        if (systemSetting.getId() != null) {
            flag = systemSettingMapper.updateByPrimaryKeySelective(systemSetting) > 0;
        }
        //插入基本设置
        else {
            flag = systemSettingMapper.insertSelective(systemSetting) > 0;
        }
        //计算/修改店铺能效
        if (flag) {
            // 开工之前设置
            //if (systemSetting.getSubmitTime().before(systemSetting.getServiceStartTime())) {
                //当日剩余时间
                lefTime = DateUtil.getMin(systemSetting.getServiceStartTime(), systemSetting.getServiceEndTime());
                //当日工作时常
                totalTime = getTotalTime(systemSetting.getServiceStartTime(),systemSetting.getServiceEndTime());
                //学徒能效时间
                trainEffective = (int)(systemSetting.getTraineeNum() * systemSetting.getTraineeFactor() * lefTime);
                //熟手能效时间
                skillerEffective = (int) (systemSetting.getSkillerNum() * systemSetting.getSkillerFactor() * lefTime);
                //能效时间
                earnTime = trainEffective + skillerEffective;
                log.info("当日能效时间为：" + earnTime);
            //}
            /*else {
            //  开工之后设置
                SystemSetting lastSetting = systemSettingMapper.getSySettingByShopIdAndTime(systemSetting.getShopId(),systemSetting.getWorkTime());
                int lasTime = DateUtil.getMin(lastSetting.getServiceStartTime(), lastSetting.getSubmitTime());
                lefTime = DateUtil.getMin(lastSetting.getSubmitTime(), lastSetting.getServiceEndTime());
                //当日工作时常
                totalTime = getTotalTime(systemSetting.getServiceStartTime(),systemSetting.getServiceEndTime());
                //学徒能效时间
                trainEffective = (int)(systemSetting.getTraineeNum() * systemSetting.getTraineeFactor() * lefTime) +
                                    (int)(lastSetting.getTraineeNum() * lastSetting.getTraineeFactor() * lasTime);
                //熟手能效时间
                skillerEffective = (int)(systemSetting.getSkillerNum() * systemSetting.getSkillerFactor() * lefTime) +
                                    (int)(lastSetting.getSkillerNum() * lastSetting.getSkillerFactor() * lasTime);
                //能效时间
                earnTime = trainEffective + skillerEffective;
                log.info("当日能效时间为：" + earnTime);

            }*/
            ProviderCount providerCount = providerCountMapper.getPorivderCountInfoNoType(systemSetting.getShopId(),systemSetting.getWorkTime());
            if (null != providerCount) {
                //更新商家预约总表
                providerCount.setEarnTime(earnTime);
                providerCount.setTotalTime(totalTime);
                providerCount.setWorkTime(systemSetting.getWorkTime());
                providerCount.setShopId(systemSetting.getShopId());
                flag = providerCountMapper.updateByPrimaryKeySelective(providerCount) > 0;
            }else {
                //新建预约总表
                ProviderCount providerCount2 = new ProviderCount();
                providerCount2.setEarnTime(earnTime);
                providerCount2.setTotalTime(totalTime);
                providerCount2.setWorkTime(systemSetting.getWorkTime());
                providerCount2.setShopId(systemSetting.getShopId());
                flag = providerCountMapper.insertSelective(providerCount2) > 0;
            }
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

    /**
     * 计算目前可工作的时间
     * @param serviceStartTime   服务开始时间
     * @param serviceEndTime     服务结束时间
     * @param submitTime         提交设置时间
     * @return
     */
    public int getLefTime(Date serviceStartTime, Date serviceEndTime, Date submitTime) {
        int lefTime = 0;
        if (serviceStartTime.before(submitTime)) {
            lefTime = DateUtil.getMin(serviceEndTime, submitTime);
        }else {
            lefTime = DateUtil.getMin(serviceEndTime, serviceStartTime);
        }
        return lefTime;
    }

    /**
     * 计算当日工作时间
     * @param serviceStartTime
     * @param serviceEndTime
     * @return
     */
    public int getTotalTime(Date serviceStartTime, Date serviceEndTime) {
        return DateUtil.getMin(serviceStartTime, serviceEndTime);
    }
}
