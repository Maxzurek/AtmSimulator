package com.example.atmsimulator.presenters.main;

import com.example.atmsimulator.models.AtmData;
import com.example.atmsimulator.views.main.IMainView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivityPresenter
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private IMainView view;
    private AtmData atmData;
    private final int LOADING_WAIT_TIME = 3000;
    private final int ROTATION_DELAY = 90;

    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public MainActivityPresenter(IMainView view){this.view = view;}

    /************************************************************************/
    /* Public Methods                                                       */
    /************************************************************************/
    public void initializeApp()
    {
        loadData();
        simulateLoading();
    }

    /************************************************************************/
    /* Private Methods                                                      */
    /************************************************************************/
    private void loadData()
    {
        atmData = new AtmData();
    }

    private void simulateLoading()
    {
        final float IMAGE_VIEW_ROTATION = 10; //10 degrees

        //Spin loading icon in main view
        Timer timerRotate = new Timer();
        TimerTask taskRotate = new TimerTask()
        {
            @Override
            public void run()
            {
                view.rotateImageViewLoading(IMAGE_VIEW_ROTATION);
            }
        };
        timerRotate.schedule(taskRotate, 0, ROTATION_DELAY);


        //Simulate 3 seconds loading
        Timer timerWait = new Timer();
        TimerTask taskWait = new TimerTask()
        {
            @Override
            public void run()
            {
                timerRotate.cancel();
                view.startLoginActivity(atmData);
            }
        };
        timerWait.schedule(taskWait, LOADING_WAIT_TIME);
    }
}
