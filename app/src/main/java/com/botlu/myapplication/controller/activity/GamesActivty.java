package com.botlu.myapplication.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.botlu.myapplication.R;
import com.botlu.myapplication.adapter.GamesAdapter;
import com.botlu.myapplication.adapter.ViewPageAdapter;
import com.botlu.myapplication.controller.GlobalController;
import com.botlu.myapplication.controller.activity.fragment.GamesAllFragment;
import com.botlu.myapplication.controller.activity.fragment.GamesTodayFragment;
import com.botlu.myapplication.model.GamesModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class GamesActivty extends AppCompatActivity {
    GlobalController globalController;
    ViewPager viewPager;
    TabLayout tabLayout;
    GamesAllFragment gamesAllFragment;
    GamesTodayFragment gamesTodayFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_activty);
        globalController = new GlobalController(this);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(GamesActivty.this.getSupportFragmentManager(),0);
        gamesTodayFragment = new GamesTodayFragment();
        viewPageAdapter.addFragment(gamesTodayFragment, "Today");
        gamesAllFragment = new GamesAllFragment();
        viewPageAdapter.addFragment(gamesAllFragment, "All");
        viewPager.setAdapter(viewPageAdapter);
    }
    @Override
    public void onBackPressed() {
            globalController.NextIntent(MainActivity.class);
        finish();
    }
}