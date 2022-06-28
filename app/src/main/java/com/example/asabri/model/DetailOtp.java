package com.example.asabri.model;

import com.google.gson.annotations.SerializedName;

public class DetailOtp {
    @SerializedName("mobile_number")
    private String mobile_number;
    @SerializedName("otp")
    private String otp;

    @SerializedName("status")
    private int status;

    @SerializedName("code")
    private int code;


    @SerializedName("msg")
    private boolean msg;

    public DetailOtp(String mobile_number, String otp) {
        this.mobile_number = mobile_number;
        this.otp = otp;
           }

    public DetailOtp(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
