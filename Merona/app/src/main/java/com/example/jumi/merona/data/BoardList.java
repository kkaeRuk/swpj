package com.example.jumi.merona.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BoardList {

    @SerializedName("data")
    public List<Datum> data = new ArrayList();


    public class Datum {
        @SerializedName("bNum")
        public int bNum;
        @SerializedName("bTitle")
        public String bTitle;
        @SerializedName("spotStart")
        public String spotStart;
        @SerializedName("spotEnd")
        public String spotEnd;
        @SerializedName("sid")
        public String sid;
        @SerializedName("bDate")
        public String bDate;
        @SerializedName("bPrice")
        public int bPrice;

    }

}
