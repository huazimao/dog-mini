package com.kingmao.dog.appointment.provider.model;

import java.util.Date;

public class SystemSetting {
    private Integer id;

    private String shopId;

    private Date serviceStartTime;

    private Date serviceEndTime;

    private Date submitTime;

    private Integer timeout;

    private Integer limite;

    private Integer traineeNum;

    private Integer skillerNum;

    private Double traineeFactor;

    private Double skillerFactor;

    private Date workTime;

    private Integer switchStatue;

    public Integer getSwitchStatue() {
        return switchStatue;
    }

    public void setSwitchStatue(Integer switchStatue) {
        this.switchStatue = switchStatue;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

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

    public Date getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(Date serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public Date getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(Date serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getLimite() {
        return limite;
    }

    public void setLimite(Integer limite) {
        this.limite = limite;
    }

    public Integer getTraineeNum() {
        return traineeNum;
    }

    public void setTraineeNum(Integer traineeNum) {
        this.traineeNum = traineeNum;
    }

    public Integer getSkillerNum() {
        return skillerNum;
    }

    public void setSkillerNum(Integer skillerNum) {
        this.skillerNum = skillerNum;
    }

    public Double getTraineeFactor() {
        return traineeFactor;
    }

    public void setTraineeFactor(Double traineeFactor) {
        this.traineeFactor = traineeFactor;
    }

    public Double getSkillerFactor() {
        return skillerFactor;
    }

    public void setSkillerFactor(Double skillerFactor) {
        this.skillerFactor = skillerFactor;
    }

    @Override
    public String toString() {
        return "SystemSetting{" +
                "id=" + id +
                ", shopId='" + shopId + '\'' +
                ", serviceStartTime=" + serviceStartTime +
                ", serviceEndTime=" + serviceEndTime +
                ", submitTime=" + submitTime +
                ", timeout=" + timeout +
                ", limite=" + limite +
                ", traineeNum=" + traineeNum +
                ", skillerNum=" + skillerNum +
                ", traineeFactor=" + traineeFactor +
                ", skillerFactor=" + skillerFactor +
                '}';
    }
}