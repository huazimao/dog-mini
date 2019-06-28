package com.kingmao.dog.appointment.customer.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CustomerAppointment implements Serializable{

    private Integer appointmentId;

    private String shopId;

    private Date workTime;

    private Date serviceEndTime;

    private Date serviceStartTime;

    private String nickName;

    private String wxImg;

    private String openid;

    private String phone;

    private String address;

    private Integer ispickup;

    private Date oppointmentTime;

    private String oppointmentTimeStr;

    private Date countFinishedTime;

    private Integer appointmentState;

    private Integer consumeTime;

    private String dtype;

    private Date accFinishedTime;

    private Integer enabled;

    private String formId;

    private String appStr;

    private String accStr;

    private Integer quantum;

    public Integer getQuantum() {
        return quantum;
    }

    public void setQuantum(Integer quantum) {
        this.quantum = quantum;
    }

    public String getOppointmentTimeStr() {
        return oppointmentTimeStr;
    }

    public void setOppointmentTimeStr(String oppointmentTimeStr) {
        this.oppointmentTimeStr = oppointmentTimeStr;
    }

    public String getAppStr() {
        return appStr;
    }

    public void setAppStr(String appStr) {
        this.appStr = appStr;
    }

    public String getAccStr() {
        return accStr;
    }

    public void setAccStr(String accStr) {
        this.accStr = accStr;
    }

    private List<PetAppointment> petLists;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWxImg() {
        return wxImg;
    }

    public void setWxImg(String wxImg) {
        this.wxImg = wxImg;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public List<PetAppointment> getPetLists() {
        return petLists;
    }

    public void setPetLists(List<PetAppointment> petLists) {
        this.petLists = petLists;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public Date getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(Date serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    public Date getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(Date serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIspickup() {
        return ispickup;
    }

    public void setIspickup(Integer ispickup) {
        this.ispickup = ispickup;
    }

    public Date getOppointmentTime() {
        return oppointmentTime;
    }

    public void setOppointmentTime(Date oppointmentTime) {
        this.oppointmentTime = oppointmentTime;
    }

    public Date getCountFinishedTime() {
        return countFinishedTime;
    }

    public void setCountFinishedTime(Date countFinishedTime) {
        this.countFinishedTime = countFinishedTime;
    }

    public Integer getAppointmentState() {
        return appointmentState;
    }

    public void setAppointmentState(Integer appointmentState) {
        this.appointmentState = appointmentState;
    }

    public Integer getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Integer consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public Date getAccFinishedTime() {
        return accFinishedTime;
    }

    public void setAccFinishedTime(Date accFinishedTime) {
        this.accFinishedTime = accFinishedTime;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "CustomerAppointment{" +
                "appointmentId=" + appointmentId +
                ", shopId='" + shopId + '\'' +
                ", workTime=" + workTime +
                ", serviceEndTime=" + serviceEndTime +
                ", serviceStartTime=" + serviceStartTime +
                ", nickName='" + nickName + '\'' +
                ", wxImg='" + wxImg + '\'' +
                ", openid='" + openid + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", ispickup=" + ispickup +
                ", oppointmentTime=" + oppointmentTime +
                ", oppointmentTimeStr='" + oppointmentTimeStr + '\'' +
                ", countFinishedTime=" + countFinishedTime +
                ", appointmentState=" + appointmentState +
                ", consumeTime=" + consumeTime +
                ", dtype='" + dtype + '\'' +
                ", accFinishedTime=" + accFinishedTime +
                ", enabled=" + enabled +
                ", formId='" + formId + '\'' +
                ", appStr='" + appStr + '\'' +
                ", accStr='" + accStr + '\'' +
                ", petLists=" + petLists +
                '}';
    }
}