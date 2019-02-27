package com.kingmao.dog.appointment.provider.model;

import java.util.Date;

public class ProviderCount {
    private Integer id;

    private String shopId;

    private Integer totalTime;

    private Integer leftTime;

    private Integer earnTime;

    private Integer consumeTiime;

    private Date workTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(Integer leftTime) {
        this.leftTime = leftTime;
    }

    public Integer getEarnTime() {
        return earnTime;
    }

    public void setEarnTime(Integer earnTime) {
        this.earnTime = earnTime;
    }

    public Integer getConsumeTiime() {
        return consumeTiime;
    }

    public void setConsumeTiime(Integer consumeTiime) {
        this.consumeTiime = consumeTiime;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }
}