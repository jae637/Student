package com.example.jhchoi.smokingspot;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class RetrofitRepo {
    @SerializedName("success")
    private String success;

    @SerializedName("location")
    public List<location> location = new ArrayList<>();

    @SerializedName("status")
    public List<String> status = new ArrayList<>();

    public class location {
        String id;
        String lat;
        String lng;
        String name;
    }
}
