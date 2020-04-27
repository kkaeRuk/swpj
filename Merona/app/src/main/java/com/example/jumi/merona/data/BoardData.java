package com.example.jumi.merona.data;

import com.google.gson.annotations.SerializedName;

public class BoardData {
    @SerializedName("bNum")
    private int bNum;

    @SerializedName("bTitle")
    private String bTitle;

    @SerializedName("spotStart")
    private String spotStart;

    @SerializedName("spotEnd")
    private String spotEnd;

    @SerializedName("sid")
    private String sid;

    @SerializedName("bDate")
    private String bDate;

    @SerializedName("bPrice")
    private int bPrice;

    public BoardData(int bNum, String bTitle, String spotStart, String spotEnd, String sid, String bDate, int bPrice) {
        this.bNum = bNum;
        this.bTitle = bTitle;
        this.spotStart = spotStart;
        this.spotEnd = spotEnd;
        this.sid = sid;
        this.bDate = bDate;
        this.bPrice = bPrice;
    }
}
