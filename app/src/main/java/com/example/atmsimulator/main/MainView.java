package com.example.atmsimulator.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.atmsimulator.EViewKey;
import com.example.atmsimulator.R;
import com.example.atmsimulator.login.view.LoginView;
import com.example.atmsimulator.models.AtmData;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class MainView extends AppCompatActivity
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private AtmData atmData;

    /************************************************************************/
    /* Overridden Methods                                                   */
    /************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.main_activity_title);
        initializeApp();
    }

    /************************************************************************/
    /* Private Methods                                                      */
    /************************************************************************/
    private void initializeApp()
    {
        loadData();
        simulateLoading();
    }

    private void loadData()
    {
        atmData = new AtmData();
    }

    private void simulateLoading()
    {
        final int LOADING_WAIT_TIME = 3000;
        final int ROTATION_DELAY = 0;
        final int ROTATION_PERIOD = 90;
        final float IMAGE_VIEW_ROTATION = 10; //10 degrees

        //Spin loading icon in main view
        Timer timerRotate = new Timer();
        TimerTask taskRotate = new TimerTask()
        {
            @Override
            public void run()
            {
                rotateImageViewLoading(IMAGE_VIEW_ROTATION);
            }
        };
        timerRotate.schedule(taskRotate, ROTATION_DELAY, ROTATION_PERIOD);


        //Simulate 3 seconds loading
        Timer timerWait = new Timer();
        TimerTask taskWait = new TimerTask()
        {
            @Override
            public void run()
            {
                timerRotate.cancel();
                startLoginActivity(atmData);
            }
        };
        timerWait.schedule(taskWait, LOADING_WAIT_TIME);
    }

    private void rotateImageViewLoading(float rotation)
    {
        ImageView imageViewLoading = findViewById(R.id.imageViewLoading);

        if(imageViewLoading != null)
        {
            float currentRotation = imageViewLoading.getRotation();
            float newRotation = currentRotation + rotation;

            imageViewLoading.setRotation(newRotation);
        }
    }

    private void startLoginActivity(Serializable atmData)
    {
        Intent loginIntent = new Intent(this, LoginView.class);
        loginIntent.putExtra(EViewKey.ATM_DATA.label, atmData);
        startActivity(loginIntent);
    }
}