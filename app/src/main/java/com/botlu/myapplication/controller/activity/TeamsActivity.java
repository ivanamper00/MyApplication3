package com.botlu.myapplication.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.botlu.myapplication.R;
import com.botlu.myapplication.adapter.TeamsAdapter;
import com.botlu.myapplication.controller.GlobalController;
import com.botlu.myapplication.model.TeamsModel;

import java.util.List;

public class TeamsActivity extends AppCompatActivity {
    GlobalController globalController;
    RecyclerView recyclerView;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        globalController = new GlobalController(this);
        recyclerView = findViewById(R.id.teams_recycler);
        cardView = findViewById(R.id.card_no_result);
        GridLayoutManager grid = new GridLayoutManager(this,2, GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(grid);

        List<TeamsModel> teamsModels = globalController.retrieveTeams();

        if(teamsModels.size() != 0){
            cardView.setVisibility(View.INVISIBLE);
            TeamsAdapter adapter = new TeamsAdapter(this, teamsModels);
            recyclerView.setAdapter(adapter);
        }else {
            cardView.setVisibility(View.VISIBLE);
        }

    }
    @Override
    public void onBackPressed() {
        globalController.NextIntent(MainActivity.class);
        finish();
    }
}