package com.botlu.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestResponseGamesModel {
    @SerializedName("results")
    private Integer results;
    @SerializedName("response")
    private List<GamesModel> response = null;

    public RequestResponseGamesModel(Integer results, List<GamesModel> response) {
        this.results = results;
        this.response = response;
    }

    public Integer getResults() {
        return results;
    }

    public List<GamesModel> getResponse() {
        return response;
    }
}
