package com.botlu.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class TeamsModel {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("logo")
    private String logo;
    @SerializedName("country")
    private CountryModel country;

    public TeamsModel(Integer id, String name, String logo, CountryModel country) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.country = country;
    }

    public TeamsModel(Integer id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = logo;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogo() {
        return logo;
    }

    public CountryModel getCountry() {
        return country;
    }
}
