package com.botlu.myapplication.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.botlu.myapplication.R;
import com.botlu.myapplication.adapter.StandingsAdapter;
import com.botlu.myapplication.controller.GlobalController;
import com.botlu.myapplication.model.StandingModel;

import java.util.List;

public class StandingsActivity extends AppCompatActivity {
    GlobalController globalController;
    RecyclerView recyclerView;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);
        globalController = new GlobalController(this);
        cardView = findViewById(R.id.card_no_result);
        recyclerView = findViewById(R.id.standings_recycler);
        GridLayoutManager grid = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(grid);

        List<List<StandingModel>> standingModels = globalController.retrieveStandings();
        if(standingModels.size() != 0){
            cardView.setVisibility(View.INVISIBLE);
            List<StandingModel>standingModel = standingModels.get(0);
            StandingsAdapter adapter = new StandingsAdapter(this,standingModel);
            recyclerView.setAdapter(adapter);
        }else{
            cardView.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onBackPressed() {
        globalController.NextIntent(MainActivity.class);
        finish();
    }
}