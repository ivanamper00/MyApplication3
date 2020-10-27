package com.botlu.myapplication.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.botlu.myapplication.R;
import com.botlu.myapplication.adapter.LeaguesAdapter;
import com.botlu.myapplication.controller.GlobalController;
import com.botlu.myapplication.model.CountryModel;
import com.botlu.myapplication.model.LeagueModel;

import java.util.ArrayList;
import java.util.List;

public class LeaguesActivity extends AppCompatActivity {
    GlobalController globalController;
    RecyclerView recyclerView;
    RecyclerView recyclerViewSearch;
    List<LeagueModel> searchLeagues;
    List<LeagueModel> leagueModelList;
    SearchView searchView;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);
        globalController = new GlobalController(this);
        recyclerView = findViewById(R.id.leagues_recycler);
        searchView = findViewById(R.id.search);
        cardView = findViewById(R.id.card_no_result);
        recyclerViewSearch = findViewById(R.id.leagues_search_recycler);
        searchLeagues = new ArrayList<>();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerViewSearch.setLayoutManager(llm1);


        leagueModelList = globalController.retrieveLeagues();
        LeaguesAdapter adapter = new LeaguesAdapter(this, leagueModelList);
        recyclerView.setAdapter(adapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty()){
                    if(cardView.getVisibility() != cardView.GONE){
                        cardView.setVisibility(cardView.GONE);
                    }
                    if(recyclerView.getVisibility() != recyclerView.VISIBLE){
                        recyclerView.setVisibility(recyclerView.VISIBLE);
                    }
                    if(recyclerViewSearch.getVisibility() != recyclerViewSearch.GONE){
                        recyclerViewSearch.setVisibility(recyclerViewSearch.GONE);
                    }
                }else{
                    search(newText);

                }
                return false;
            }
        });

    }
    @Override
    public void onBackPressed() {
        globalController.NextIntent(MainActivity.class);
        finish();
    }

    public void search(String query){
        cleanSearch();
        for(int i = 0; i < leagueModelList.size(); i++){
            if(leagueModelList.get(i).getName().toLowerCase().contains(query.toLowerCase())){
                searchLeagues.add(new LeagueModel(leagueModelList.get(i).getId(),
                        leagueModelList.get(i).getName(),
                        leagueModelList.get(i).getType(),
                        leagueModelList.get(i).getLogo(),
                        leagueModelList.get(i).getCountry()));
            }

        }
        if(searchLeagues.size() != 0){
            recyclerView.setVisibility(View.GONE);
            cardView.setVisibility(View.GONE);
            if(recyclerViewSearch.getVisibility() != View.VISIBLE){
                recyclerViewSearch.setVisibility(View.VISIBLE);
            }
            LeaguesAdapter adapter = new LeaguesAdapter(LeaguesActivity.this, searchLeagues);
            recyclerViewSearch.setAdapter(adapter);
        }
        else{
            recyclerViewSearch.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            cardView.setVisibility(View.VISIBLE);
        }
    }

    public void cleanSearch(){
        searchLeagues.clear();
    }
}