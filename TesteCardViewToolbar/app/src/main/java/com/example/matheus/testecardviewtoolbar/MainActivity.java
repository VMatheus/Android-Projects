package com.example.matheus.testecardviewtoolbar;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar1,mToolbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(mToolbar2);
        getSupportActionBar().setTitle("Title");
// Extended Toolbar (holds drawer icon)
        mToolbar1.setNavigationIcon(R.drawable.ic_action_menu);
        mToolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Handle Navigation Drawer menu click here
            }
        });
    }
}
