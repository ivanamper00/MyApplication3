package com.botlu.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestResponseTeamModel {
    @SerializedName("results")
    private Integer results;
    @SerializedName("response")
    private List<TeamsModel> response = null;

    public RequestResponseTeamModel(Integer results, List<TeamsModel> response) {
        this.results = results;
        this.response = response;
    }

    public Integer getResults() {
        return results;
    }

    public List<TeamsModel> getResponse() {
        return response;
    }
}
