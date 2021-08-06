package com.example.atmsimulator.main.presenter;

import com.example.atmsimulator.models.AtmData;
import com.example.atmsimulator.main.view.IMainView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivityPresenter
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private IMainView view;
    private AtmData atmData;

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
                view.rotateImageViewLoading(IMAGE_VIEW_ROTATION);
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
                view.startLoginActivity(atmData);
            }
        };
        timerWait.schedule(taskWait, LOADING_WAIT_TIME);
    }
}
