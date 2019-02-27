package com.kingmao.dog.appointment.provider.model;

import java.util.Date;

public class ProviderCount {
    private Integer id;

    private String shopId;

    private Integer totalTime;

    private Integer earnTime;

    private Integer consumeTime;

    private Date workTime;

    private Short modelStatue;

    private String dtype;

    private Short enabled;

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

    public Integer getEarnTime() {
        return earnTime;
    }

    public void setEarnTime(Integer earnTime) {
        this.earnTime = earnTime;
    }

    public Integer getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Integer consumeTime) {
        this.consumeTime = consumeTime;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public Short getModelStatue() {
        return modelStatue;
    }

    public void setModelStatue(Short modelStatue) {
        this.modelStatue = modelStatue;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public Short getEnabled() {
        return enabled;
    }

    public void setEnabled(Short enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "ProviderCount{" +
                "id=" + id +
                ", shopId='" + shopId + '\'' +
                ", totalTime=" + totalTime +
                ", earnTime=" + earnTime +
                ", consumeTime=" + consumeTime +
                ", workTime=" + workTime +
                ", modelStatue=" + modelStatue +
                ", dtype='" + dtype + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}