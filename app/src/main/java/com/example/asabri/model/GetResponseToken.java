package com.example.asabri.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetResponseToken {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private AccessToken data;
    @SerializedName("detail")
    @Expose
    private DetailOtp detail;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AccessToken getData() {
        return data;
    }

    public void setData(AccessToken data) {
        this.data = data;
    }

    public void setDetail(DetailOtp detail) {
        this.detail = detail;
    }

    public DetailOtp getDetail() {
        return detail;
    }
}
