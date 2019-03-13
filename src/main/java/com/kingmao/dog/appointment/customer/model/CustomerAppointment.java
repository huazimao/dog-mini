package com.kingmao.dog.appointment.customer.model;

import java.util.Date;
import java.util.List;

public class CustomerAppointment {
    private Integer appointmentId;

    private String shopId;

    private Date workTime;

    private Date serviceEndTime;

    private Date serviceStartTime;

    private String openid;

    private String phone;

    private String address;

    private Integer ispickup;

    private Date oppointmentTime;

    private Date countFinishedTime;

    private Integer appointmentState;

    private Integer consumeTime;

    private String dtype;

    private Date accFinishedTime;

    private Integer enabled;

    private String formId;

    private List<PetAppointment> petLists;

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
                ", openid='" + openid + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", ispickup=" + ispickup +
                ", oppointmentTime=" + oppointmentTime +
                ", countFinishedTime=" + countFinishedTime +
                ", appointmentState=" + appointmentState +
                ", consumeTime=" + consumeTime +
                ", dtype='" + dtype + '\'' +
                ", accFinishedTime=" + accFinishedTime +
                ", enabled=" + enabled +
                ", petLists=" + petLists +
                '}';
    }
}