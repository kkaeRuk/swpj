package com.example.jumi.merona.data;

import com.example.jumi.merona.UserData;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("studentId")
    private String sid;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("major")
    private String major;

    @SerializedName("phonenum")
    private String phonenum;

    @SerializedName("cash")
    private int cash;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public UserData getUserInfo() {
        UserData data = new UserData(sid, nickname, major, phonenum, cash);
        return data;
    }

}
