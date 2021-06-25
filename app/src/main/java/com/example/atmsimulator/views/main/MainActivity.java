package com.example.atmsimulator.views.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.atmsimulator.R;
import com.example.atmsimulator.views.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_activity_title);

        //Simulate 3 seconds loading
        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                startLoginActivity();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 3000);
    }

    private void startLoginActivity()
    {
        Intent loginScreen = new Intent(this, LoginActivity.class);
        startActivity(loginScreen);
    }
}