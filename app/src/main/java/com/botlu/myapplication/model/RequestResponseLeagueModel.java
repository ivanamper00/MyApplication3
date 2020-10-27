package com.botlu.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestResponseLeagueModel {

    @SerializedName("results")
    private Integer results;
    @SerializedName("response")
    private List<LeagueModel> response = null;

    public RequestResponseLeagueModel(Integer results, List<LeagueModel> response) {
        this.results = results;
        this.response = response;
    }

    public Integer getResults() {
        return results;
    }

    public List<LeagueModel> getResponse() {
        return response;
    }

}
