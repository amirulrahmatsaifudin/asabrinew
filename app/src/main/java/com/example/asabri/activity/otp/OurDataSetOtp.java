package com.example.asabri.activity.otp;

public class OurDataSetOtp {
    String mobile_number;
    String otp;


    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public OurDataSetOtp(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public OurDataSetOtp(){

    }

}
