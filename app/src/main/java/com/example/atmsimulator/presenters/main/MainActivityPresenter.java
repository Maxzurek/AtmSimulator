package com.example.atmsimulator.presenters.main;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atmsimulator.views.login.LoginActivity;
import com.example.atmsimulator.views.main.IMainView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivityPresenter
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private IMainView view;
    final private int LOADING_WAIT_TIME = 3000;

    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public MainActivityPresenter(IMainView view){this.view = view;}

    /************************************************************************/
    /* Public Methods                                                       */
    /************************************************************************/
    public void initializeApp()
    {
        simulateLoading();
    }

    /************************************************************************/
    /* Private Methods                                                      */
    /************************************************************************/
    private void simulateLoading()
    {
        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                view.startLoginActivity();
            }
        };

        //Simulate 3 seconds loading
        Timer timer = new Timer();
        timer.schedule(task, LOADING_WAIT_TIME);
    }
}
