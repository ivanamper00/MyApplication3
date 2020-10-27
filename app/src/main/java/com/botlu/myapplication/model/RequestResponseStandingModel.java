package com.botlu.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestResponseStandingModel {
    @SerializedName("results")
    private Integer results;
    @SerializedName("response")
    private List<List<StandingModel>> response = null;

    public RequestResponseStandingModel(Integer results, List<List<StandingModel>> response) {
        this.results = results;
        this.response = response;
    }

    public Integer getResults() {
        return results;
    }

    public List<List<StandingModel>> getResponse() {
        return response;
    }
}
