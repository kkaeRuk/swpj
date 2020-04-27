package com.example.jumi.merona.data;

import com.google.gson.annotations.SerializedName;

public class BoardResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
