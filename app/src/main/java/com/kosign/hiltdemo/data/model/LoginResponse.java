package com.kosign.hiltdemo.data.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
