package com.botlu.myapplication.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.botlu.myapplication.R;
import com.botlu.myapplication.controller.GlobalController;

public class MainActivity extends AppCompatActivity {
    GlobalController globalController;
    TextView games;
    TextView teams;
    TextView standings;
    TextView league;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalController = new GlobalController(this);
        setContentView(R.layout.activity_main);
        games = findViewById(R.id.games);
        teams = findViewById(R.id.teams);
        standings = findViewById(R.id.standing);
        league = findViewById(R.id.leagues);
        flag = 0;

        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalController.NextIntent(GamesActivty.class);
                finish();
            }
        });
        teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalController.NextIntent(TeamsActivity.class);
                finish();
            }
        });
        standings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalController.NextIntent(StandingsActivity.class);
                finish();
            }
        });
        league.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalController.NextIntent(LeaguesActivity.class);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(flag == 1){
            finish();
        }
        else{
            Toast.makeText(this, "Press Back to Exit", Toast.LENGTH_SHORT).show();
            flag++;
        }

    }
}