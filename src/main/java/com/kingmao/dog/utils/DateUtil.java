package com.kingmao.dog.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Paceage:com.kingmao.dog.utils
 * Description:
 * Date:2019/2/21
 * Author: 伊秦轩
 **/
@Component
public class DateUtil {

    /**
     * 求两个date类型之间的分钟差值
     * @param starTime
     * @param endTime
     * @return
     */
    public static int getMin(Date starTime, Date endTime) {
        return (int)(endTime.getTime() - starTime.getTime())/(1000*60);
    }

    /**
     * 返回时间，不包含是秒 2019-02-26 10:36
     * @param date
     * @return
     */
    public static Date getYMD(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String str = sdf.format(new Date());
        Date date1 = null;
        try {
            date1 =  sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    /**
     * 时间+分钟，返回结果不包含秒
     * @param date
     * @param mins
     * @return
     */
    public static Date getPlus(Date date,Integer mins){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date1 = null;
        try {
            date1 = sdf.parse(sdf.format((date.getTime() + mins * 60 * 1000)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date1;
    }

    /**
     * 计算可服务时间
     * @param serviceStartTime
     * @param serviceEndTime
     * @param earnTime
     * @param consumeTime
     * @return
     */
    public static Date countRat(Date serviceStartTime,Date serviceEndTime,Integer earnTime, Integer consumeTime){
        double b = getMin(serviceStartTime,serviceEndTime) * (new BigDecimal((float)consumeTime/earnTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        return getPlus(serviceStartTime, (int) b);
    }

    public static void main(String[] args) {
        System.out.println(countRat(test1(),test2(),300,183));
    }

    public static Date test1(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String a = "2019-02-26 09:30:00";
        Date date1 = null;
        try {
            date1 =  sdf.parse(a);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("开始时间：" + date1);
        return date1;
    }

    public static Date test2(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String a = "2019-02-26 20:00:00";
        Date date1 = null;
        try {
            date1 =  sdf.parse(a);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("结束时间：" + date1);
        return date1;
    }


}
