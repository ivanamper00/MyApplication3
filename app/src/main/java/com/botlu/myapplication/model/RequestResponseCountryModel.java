package com.botlu.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestResponseCountryModel {
    @SerializedName("results")
    private Integer results;
    @SerializedName("response")
    private List<CountryModel> response = null;

    public RequestResponseCountryModel(Integer results, List<CountryModel> response) {
        this.results = results;
        this.response = response;
    }

    public Integer getResults() {
        return results;
    }

    public List<CountryModel> getResponse() {
        return response;
    }
}
