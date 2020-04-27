package com.example.jumi.merona;

import java.io.Serializable;

public class UserData implements Serializable {
    public String studentid;
    public String nickname;
    public String major;
    public String phonenumber;
    public int usercash;

    public UserData(String id, String nick, String major, String phone, int cash) {
        this.studentid = id;
        this.nickname = nick;
        this.major = major;
        this.phonenumber = phone;
        this.usercash = cash;
    }

}
