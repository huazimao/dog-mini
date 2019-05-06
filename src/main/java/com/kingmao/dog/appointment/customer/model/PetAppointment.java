package com.kingmao.dog.appointment.customer.model;

import java.io.Serializable;

public class PetAppointment implements Serializable{

    private Integer id;

    private Integer appointmentId;

    private String openid;

    private String kindPet;

    private String size;

    private String kindService;

    private String comment;

    private String namePet;

    private String photoPet;

    private Integer enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getKindPet() {
        return kindPet;
    }

    public void setKindPet(String kindPet) {
        this.kindPet = kindPet;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getKindService() {
        return kindService;
    }

    public void setKindService(String kindService) {
        this.kindService = kindService;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNamePet() {
        return namePet;
    }

    public void setNamePet(String namePet) {
        this.namePet = namePet;
    }

    public String getPhotoPet() {
        return photoPet;
    }

    public void setPhotoPet(String photoPet) {
        this.photoPet = photoPet;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}