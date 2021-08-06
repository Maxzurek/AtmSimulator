package com.example.atmsimulator.main.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.atmsimulator.EViewKey;
import com.example.atmsimulator.R;
import com.example.atmsimulator.main.presenter.MainActivityPresenter;
import com.example.atmsimulator.login.view.LoginView;

import java.io.Serializable;

public class MainView extends AppCompatActivity implements IMainView
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
    @Override
    public void rotateImageViewLoading(float rotation)
    {
        ImageView imageViewLoading = findViewById(R.id.imageViewLoading);

        if(imageViewLoading != null)
        {
            float currentRotation = imageViewLoading.getRotation();
            float newRotation = currentRotation + rotation;

            imageViewLoading.setRotation(newRotation);
        }
    }

    @Override
    public void startLoginActivity(Serializable atmData)
    {
        Intent loginIntent = new Intent(this, LoginView.class);
        loginIntent.putExtra(EViewKey.ATM_DATA.label, atmData);
        startActivity(loginIntent);
    }
}