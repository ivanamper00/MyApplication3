package com.botlu.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeagueModel {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;
    @SerializedName("logo")
    private String logo;
    @SerializedName("country")
    private CountryModel country;
    @SerializedName("seasons")
    private List<SeasonModel> seasons = null;

    public LeagueModel(Integer id, String name, String type, String logo, CountryModel country, List<SeasonModel> seasons) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.logo = logo;
        this.country = country;
        this.seasons = seasons;
    }

    public LeagueModel(Integer id, String name, String type, String logo, CountryModel country) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.logo = logo;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLogo() {
        return logo;
    }

    public CountryModel getCountry() {
        return country;
    }

    public List<SeasonModel> getSeasons() {
        return seasons;
    }
}
