package com.example.matheus.mesada;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

public class Splash_Screen extends AppCompatActivity {
    private static int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent it = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(it);
     finish();
        }
    },SPLASH_DISPLAY_LENGTH);

    }

}
