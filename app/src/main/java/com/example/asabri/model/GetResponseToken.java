package com.example.asabri.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetResponseToken {

    @SerializedName("code")
    private int code;


    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("success")
    @Expose
    private AccessToken data;

    public Integer getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
}
