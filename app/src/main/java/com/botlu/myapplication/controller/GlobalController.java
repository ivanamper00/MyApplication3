package com.botlu.myapplication.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.botlu.myapplication.controller.activity.MainActivity;
import com.botlu.myapplication.database.HockeyEyPiAy;
import com.botlu.myapplication.model.CountryModel;
import com.botlu.myapplication.model.GamesModel;
import com.botlu.myapplication.model.LeagueModel;
import com.botlu.myapplication.model.RequestResponseCountryModel;
import com.botlu.myapplication.model.RequestResponseGamesModel;
import com.botlu.myapplication.model.RequestResponseLeagueModel;
import com.botlu.myapplication.model.RequestResponseStandingModel;
import com.botlu.myapplication.model.RequestResponseTeamModel;
import com.botlu.myapplication.model.StandingModel;
import com.botlu.myapplication.model.TeamsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class GlobalController {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String GAMES_CURRENT = "games_current";
    public static final String GAMES_SEASON_LEAGUE = "games";
    public static final String TEAMS_SEASON_LEAGUE = "teams";
    public static final String STANDINGS = "standings";
    public static final String COUNTRY = "country";
    public static final String LEAGUE = "league";
    public static final String CURRENT_LEAGUE = "current_league";
    public static final String CURRENT_SEASON = "current_season";

    public static final String GAMES_CURRENT_ERR = "games_all_err";
    public static final String GAMES_SEASON_LEAGUE_ERR = "games_err";
    public static final String TEAMS_SEASON_LEAGUE_ERR = "teams_err";
    public static final String STANDINGS_ERR = "standings_err";
    public static final String COUNTRY_ERR = "country_err";
    public static final String LEAGUE_ERR = "league_err";

    ProgressDialog pdLoading;
    Context context;
    Intent intent;
    Activity activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    CountDownTimer countDownTimer;
    int flag;


    @SuppressLint("CommitPrefEdits")
    public GlobalController(Context context){
        this.pdLoading = new ProgressDialog(context);
        this.context = context;
        this.activity = (Activity) context;
        this.intent = activity.getIntent();
        this.sharedPreferences = this.context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
        this.flag = 0;
    }

    public String getErrors(String key) {
        return sharedPreferences.getString(key,"");
    }

    public void setErrors(String key, String value) {
        editor.putString(key ,value);
        editor.commit();
    }

    public String getDefaultLeague(){
        return sharedPreferences.getString(CURRENT_LEAGUE,"37");
    }
    public void setDefaultLeague(String league){
        editor.putString(CURRENT_LEAGUE ,league);
        editor.commit();
    }

    public String getDefaultSeason(){
        return sharedPreferences.getString(CURRENT_SEASON,"2020");
    }
    public void setDefaultSeason(String series){
        editor.putString(CURRENT_SEASON ,series);
        editor.commit();
    }

    public void clearContents(){
        this.editor.clear();
        this.editor.commit();
    }

    //Next Intent w/ Data Function
    public void NextIntent(Class toClass, String data) {
        Intent intent = new Intent(context, toClass);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }
    //Next Intent w/o Data Function
    public void NextIntent(Class toClass) {
        NextIntent(toClass, "");
    }

    //Retrofit Builder Function
    public HockeyEyPiAy getRetrofitBuilder() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HockeyEyPiAy.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HockeyEyPiAy api = retrofit.create(HockeyEyPiAy.class);
        return api;
    }

    // Loading Function
    public void startLoading(String text){
        pdLoading.setMessage("\t" + text);
        pdLoading.setCancelable(false);
        pdLoading.show();
    }
    public void startLoading(){
        startLoading("Please Wait...");
    }
    public void stopLoading(){
        pdLoading.dismiss();
    }


    public <T> void saveData(String calling, List<T> object){
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(calling ,json);
        editor.commit();
    }
    public <T> void saveData(String calling, T object){
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(calling ,json);
        editor.commit();
    }

    public List<CountryModel> retrieveCountry(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(COUNTRY,"");
        Type type = new TypeToken<List<CountryModel>>(){}.getType();
        List<CountryModel> objects = gson.fromJson(json, type);
        return objects;
    }
    public List<LeagueModel> retrieveLeagues(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(LEAGUE,"");
        Type type = new TypeToken<List<LeagueModel>>(){}.getType();
        List<LeagueModel> objects = gson.fromJson(json, type);
        return objects;
    }
    public List<List<StandingModel>> retrieveStandings(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(STANDINGS,"");
        Type type = new TypeToken<List<List<StandingModel>>>(){}.getType();
        List<List<StandingModel>> objects = gson.fromJson(json, type);
        return objects;
    }
    public List<GamesModel> retrieveGames(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(GAMES_SEASON_LEAGUE,"");
        Type type = new TypeToken<List<GamesModel>>(){}.getType();
        List<GamesModel> objects = gson.fromJson(json, type);
        return objects;
    }
    public List<GamesModel> retrieveCurrentGames(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(GAMES_CURRENT,"");
        Type type = new TypeToken<List<GamesModel>>(){}.getType();
        List<GamesModel> objects = gson.fromJson(json, type);
        return objects;
    }
    public List<TeamsModel> retrieveTeams(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(TEAMS_SEASON_LEAGUE,"");
        Type type = new TypeToken<List<TeamsModel>>(){}.getType();
        List<TeamsModel> objects = gson.fromJson(json, type);
        return objects;
    }
//
    public void saveCountry(){

        Call<RequestResponseCountryModel> call = getRetrofitBuilder().getCountry();

        call.enqueue(new Callback<RequestResponseCountryModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<RequestResponseCountryModel> call, retrofit2.Response<RequestResponseCountryModel> response) {
                RequestResponseCountryModel responseCountry = response.body();
                List<CountryModel> countryModels = responseCountry.getResponse();
                saveData(COUNTRY, countryModels);
//                setSuccess(PLAYERS,"Players..");
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Country Saved!");
            }
            @Override
            public void onFailure(Call<RequestResponseCountryModel> call, Throwable t) {
//                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                setErrors(COUNTRY_ERR,t.getMessage());
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + t.getMessage());
            }
        });
    }

    public void saveLeagues(){

        Call<RequestResponseLeagueModel> call = getRetrofitBuilder().getLeagues();

        call.enqueue(new Callback<RequestResponseLeagueModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<RequestResponseLeagueModel> call, retrofit2.Response<RequestResponseLeagueModel> response) {
                RequestResponseLeagueModel leagueResponse = response.body();
                List<LeagueModel> leagueModels = leagueResponse.getResponse();
                saveData(LEAGUE, leagueModels);
//                setSuccess(PLAYERS,"Players..");
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Leagues Saved!");
            }
            @Override
            public void onFailure(Call<RequestResponseLeagueModel> call, Throwable t) {
//                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                setErrors(LEAGUE_ERR,t.getMessage());
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + t.getMessage());
            }
        });
    }

    public void saveStandings(String league,String season){

        Call<RequestResponseStandingModel> call = getRetrofitBuilder().getStandings(league,season);

        call.enqueue(new Callback<RequestResponseStandingModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<RequestResponseStandingModel> call, retrofit2.Response<RequestResponseStandingModel> response) {
                RequestResponseStandingModel standingResponse = response.body();
                List<List<StandingModel>> standingModels = standingResponse.getResponse();
                saveData(STANDINGS, standingModels);
//                setSuccess(PLAYERS,"Players..");
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Standings Saved!");
            }
            @Override
            public void onFailure(Call<RequestResponseStandingModel> call, Throwable t) {
//                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                setErrors(STANDINGS_ERR,t.getMessage());
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + t.getMessage());
            }
        });
    }

    public void saveGames(String league,String season){

        Call<RequestResponseGamesModel> call = getRetrofitBuilder().getGames(league,season);

        call.enqueue(new Callback<RequestResponseGamesModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<RequestResponseGamesModel> call, retrofit2.Response<RequestResponseGamesModel> response) {
                RequestResponseGamesModel gamesResponse = response.body();
                List<GamesModel> gamesModels = gamesResponse.getResponse();
                saveData(GAMES_SEASON_LEAGUE, gamesModels);
//                setSuccess(PLAYERS,"Players..");
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Games Saved!");
            }
            @Override
            public void onFailure(Call<RequestResponseGamesModel> call, Throwable t) {
//                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                setErrors(GAMES_SEASON_LEAGUE_ERR,t.getMessage());
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + t.getMessage());
            }
        });
    }

    public void saveCurrentGames(String date){

        Call<RequestResponseGamesModel> call = getRetrofitBuilder().getCurrentGames(date);

        call.enqueue(new Callback<RequestResponseGamesModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<RequestResponseGamesModel> call, retrofit2.Response<RequestResponseGamesModel> response) {
                RequestResponseGamesModel gamesResponse = response.body();
                List<GamesModel> gamesModels = gamesResponse.getResponse();
                saveData(GAMES_CURRENT, gamesModels);
//                setSuccess(PLAYERS,"Players..");
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Current Games Saved!");
            }
            @Override
            public void onFailure(Call<RequestResponseGamesModel> call, Throwable t) {
//                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                setErrors(GAMES_CURRENT_ERR,t.getMessage());
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + t.getMessage());
            }
        });
    }

    public void saveTeams(String league,String season){

        Call<RequestResponseTeamModel> call = getRetrofitBuilder().getTeams(league,season);

        call.enqueue(new Callback<RequestResponseTeamModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<RequestResponseTeamModel> call, retrofit2.Response<RequestResponseTeamModel> response) {
                RequestResponseTeamModel teamResponse = response.body();
                List<TeamsModel> teamsModels = teamResponse.getResponse();
                saveData(TEAMS_SEASON_LEAGUE, teamsModels);
//                setSuccess(PLAYERS,"Players..");
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Teams Saved!");
            }
            @Override
            public void onFailure(Call<RequestResponseTeamModel> call, Throwable t) {
//                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_SHORT).show();
                setErrors(TEAMS_SEASON_LEAGUE_ERR,t.getMessage());
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + t.getMessage());
            }
        });
    }

    public void saveStandings(){
        saveStandings(getDefaultLeague(),getDefaultSeason());
    }
    public void saveGames(){
        saveGames(getDefaultLeague(),getDefaultSeason());
    }
    public void saveTeams(){
        saveTeams(getDefaultLeague(),getDefaultSeason());
    }
    public void saveCurrentGames(){
        saveCurrentGames(getCurrentDate());
    }
    public void loop(){
        checkData();
    }

    public void checkData(){
        countDownTimer = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(retrieveLeagues() == null ||
                        retrieveCountry() == null ||
                        retrieveStandings() == null ||
                        retrieveGames() == null ||
                        retrieveTeams() == null){
                    errorChecker();
                    loop();
                }else{
                    NextIntent(MainActivity.class);
                    stopLoading();
                }

            }
        }.start();
    }
    public void loopSplash(){
        checkDataSplash();
    }
    public void checkDataSplash(){
        countDownTimer = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(retrieveLeagues() == null ||
                        retrieveCountry() == null ||
                        retrieveStandings() == null ||
                        retrieveGames() == null ||
                        retrieveTeams() == null ||
                        retrieveCurrentGames() == null){
                    errorChecker();
                    loopSplash();
                }else{
                    NextIntent(MainActivity.class);
                    activity.finish();
                }

            }
        }.start();
    }

    public void errorChecker(){
        if(getErrors(TEAMS_SEASON_LEAGUE_ERR).contains("timeout")){
            saveTeams();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Requesting for Teams");
            setErrors(TEAMS_SEASON_LEAGUE_ERR,"");
        }else if(!getErrors(TEAMS_SEASON_LEAGUE_ERR).isEmpty()){
            errorHolder();
        }
        if(getErrors(GAMES_SEASON_LEAGUE_ERR).contains("timeout")){
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Requesting for Games");
            saveGames();
            setErrors(GAMES_SEASON_LEAGUE_ERR,"");
        }else if(!getErrors(GAMES_SEASON_LEAGUE_ERR).isEmpty()){
            errorHolder();
        }
        if(getErrors(STANDINGS_ERR).contains("timeout")){
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Requesting for Standins");
            saveStandings();
            setErrors(STANDINGS_ERR,"");
        }else if(!getErrors(STANDINGS_ERR).isEmpty()){
            errorHolder();
        }
        if(getErrors(LEAGUE_ERR).contains("timeout")){
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Requesting for Leagues");
            saveLeagues();
            setErrors(LEAGUE_ERR,"");
        }else if(!getErrors(LEAGUE_ERR).isEmpty()){
            errorHolder();
        }
        if(getErrors(COUNTRY_ERR).contains("timeout")){
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Requesting for Country");
            saveCountry();
            setErrors(COUNTRY_ERR,"");
        }else if(!getErrors(COUNTRY_ERR).isEmpty()){
            errorHolder();
        }
        if(getErrors(GAMES_CURRENT_ERR).contains("timeout")){
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa Requesting for Country");
            saveCurrentGames();
            setErrors(GAMES_CURRENT_ERR,"");
        }else if(!getErrors(GAMES_CURRENT_ERR).isEmpty()){
            errorHolder();
        }
    }

    public void errorHolder(){
        if(flag == 0){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this.context);
            alertDialog.setCancelable(false);
            alertDialog.setMessage("Failed To Connect, Try To Restart the Application!");
            alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            AlertDialog alert = alertDialog.create();
            alert.show();
            flag++;
        }
    }

    public void saveAllQueries(){
        saveLeagues();
        saveCountry();
        saveStandings();
        saveGames();
        saveTeams();
        saveCurrentGames();
    }

    public String getCurrentDate(){
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        return timeStamp;
    }
}
