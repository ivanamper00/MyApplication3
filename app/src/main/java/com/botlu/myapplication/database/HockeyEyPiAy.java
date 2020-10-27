package com.botlu.myapplication.database;

import com.botlu.myapplication.model.RequestResponseCountryModel;
import com.botlu.myapplication.model.RequestResponseGamesModel;
import com.botlu.myapplication.model.RequestResponseLeagueModel;
import com.botlu.myapplication.model.RequestResponseStandingModel;
import com.botlu.myapplication.model.RequestResponseTeamDetailsModel;
import com.botlu.myapplication.model.RequestResponseTeamModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface HockeyEyPiAy {

    String BASE_URL = "https://api-hockey.p.rapidapi.com/";

    @Headers({"x-rapidapi-host: api-hockey.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("countries/")
    Call<RequestResponseCountryModel> getCountry();

    @Headers({"x-rapidapi-host: api-hockey.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("leagues/")
    Call<RequestResponseLeagueModel> getLeagues();

    @Headers({"x-rapidapi-host: api-hockey.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("standings/")
    Call<RequestResponseStandingModel> getStandings(@Query("league") String league, @Query("season")String season);

    @Headers({"x-rapidapi-host: api-hockey.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("games/")
    Call<RequestResponseGamesModel> getGames(@Query("league") String league, @Query("season")String season);

    @Headers({"x-rapidapi-host: api-hockey.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("games/")
    Call<RequestResponseGamesModel> getCurrentGames(@Query("date") String date);

    @Headers({"x-rapidapi-host: api-hockey.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("teams/")
    Call<RequestResponseTeamModel> getTeams(@Query("league") String league, @Query("season")String season);

    @Headers({"x-rapidapi-host: api-hockey.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("teams/")
    Call<RequestResponseTeamDetailsModel> getTeam(@Query("id") String id);
}
