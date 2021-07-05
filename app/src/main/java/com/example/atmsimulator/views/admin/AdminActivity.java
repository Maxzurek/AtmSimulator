package com.example.atmsimulator.views.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.atmsimulator.R;
import com.example.atmsimulator.models.AtmData;
import com.example.atmsimulator.presenters.admin.AdminActivityPresenter;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class AdminActivity extends AppCompatActivity implements IAdminView
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private AdminActivityPresenter presenter;
    private AtmData atmData;
    //TODO

    /************************************************************************/
    /* Overridden Methods                                                   */
    /************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        presenter = new AdminActivityPresenter(this);
    }

    @Override
    protected void onSaveInstanceState(@NonNull @NotNull Bundle outState)
    {
        super.onSaveInstanceState(outState);

        //TODO
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        //TODO
    }

    /************************************************************************/
    /* Interface Implementation                                             */
    /************************************************************************/
    @Override
    public Serializable getAtmData()
    {
        return null;
    }
    //TODO

    /************************************************************************/
    /* Events Handling                                                      */
    /************************************************************************/
    //TODO

    /************************************************************************/
    /* Private class methods                                                */
    /************************************************************************/
    //TODO
}