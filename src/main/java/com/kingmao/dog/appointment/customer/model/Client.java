package com.kingmao.dog.appointment.customer.model;

public class Client {
    private Integer id;

    private String openid;

    private String nickName;

    private String wxImg;

    private String phone;

    private String comments;

    private String realyName;

    private String address;

    private Integer enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRealyName() {
        return realyName;
    }

    public void setRealyName(String realyName) {
        this.realyName = realyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", wxImg='" + wxImg + '\'' +
                ", phone='" + phone + '\'' +
                ", comments='" + comments + '\'' +
                ", realyName='" + realyName + '\'' +
                ", address='" + address + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}