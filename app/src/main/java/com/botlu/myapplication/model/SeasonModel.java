package com.botlu.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class SeasonModel {
    @SerializedName("season")
    private Integer season;
    @SerializedName("current")
    private Boolean current;
    @SerializedName("start")
    private String start;
    @SerializedName("end")
    private String end;

    public SeasonModel(Integer season, Boolean current, String start, String end) {
        this.season = season;
        this.current = current;
        this.start = start;
        this.end = end;
    }

    public Integer getSeason() {
        return season;
    }

    public Boolean getCurrent() {
        return current;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
