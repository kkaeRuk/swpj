package com.example.jumi.merona.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("sid")
    String sid;

    @SerializedName("pwd")
    String pwd;

    public LoginData(String studentId, String userPwd) {
        this.sid = studentId;
        this.pwd = userPwd;
    }
}
