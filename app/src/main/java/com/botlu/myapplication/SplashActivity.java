package com.botlu.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.botlu.myapplication.controller.GlobalController;
import com.botlu.myapplication.controller.activity.MainActivity;

public class SplashActivity extends AppCompatActivity {
    GlobalController globalController;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        globalController = new GlobalController(this);
        globalController.clearContents();
        globalController.saveAllQueries();
        globalController.loopSplash();
        System.out.println("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa " + globalController.getCurrentDate());


//        countDownTimer = new CountDownTimer(3000,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                globalController.NextIntent(MainActivity.class);
//                finish();
//            }
//        }.start();
    }
}