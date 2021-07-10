package com.example.atmsimulator.views.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

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
    private final String ATM_DATA_KEY = "atmData";

    private AdminActivityPresenter presenter;

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
        return getIntent().getSerializableExtra(ATM_DATA_KEY);
    }

    //TODO

    /************************************************************************/
    /* Events Handling                                                      */
    /************************************************************************/
    public void onClickPayInterest(View view)
    {
    }
    
    public void onClickCheckAccountList(View view)
    {
    }

    public void onCLickSavingAccountList(View view)
    {
    }

    public void onClickClientList(View view)
    {
    }

    /************************************************************************/
    /* Private class methods                                                */
    /************************************************************************/
    //TODO
}