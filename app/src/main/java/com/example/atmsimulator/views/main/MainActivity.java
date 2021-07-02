package com.example.atmsimulator.views.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.atmsimulator.R;
import com.example.atmsimulator.presenters.main.MainActivityPresenter;
import com.example.atmsimulator.views.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements IMainView
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private MainActivityPresenter presenter;

    /************************************************************************/
    /* Overridden Methods                                                   */
    /************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_activity_title);

        presenter = new MainActivityPresenter(this);

        presenter.initializeApp();
    }

    /************************************************************************/
    /* Interface Implementation                                             */
    /************************************************************************/
    public void startLoginActivity()
    {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }
}