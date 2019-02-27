package com.kingmao.dog.appointment.provider.model;

import java.util.Date;

public class ProviderAppointment {
    private Integer id;

    private String shopId;

    private Date serviceStartTime;

    private Date serviceEndTime;

    private Integer timeout;

    private Integer limite;

    private Integer traineeNum;

    private Integer skillerNum;

    private Double factor;

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

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }
}