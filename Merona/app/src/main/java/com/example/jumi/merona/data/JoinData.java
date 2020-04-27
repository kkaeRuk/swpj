package com.example.jumi.merona.data;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("name")
    private String name;

    @SerializedName("sid")
    private String sid;

    @SerializedName("pwd")
    private String pwd;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("major")
    private String major;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    public JoinData(String userName, String userId, String userPwd, String nickname, String major, String phoneNumber) {
        this.name = userName;
        this.sid = userId;
        this.pwd = userPwd;
        this.nickname = nickname;
        this.major = major;
        this.phoneNumber = phoneNumber;
    }
}
