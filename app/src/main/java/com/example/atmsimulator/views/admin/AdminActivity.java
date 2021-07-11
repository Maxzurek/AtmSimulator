package com.example.atmsimulator.views.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.atmsimulator.AdminListActivity;
import com.example.atmsimulator.R;
import com.example.atmsimulator.presenters.admin.AdminActivityPresenter;

import java.io.Serializable;

public class AdminActivity extends AppCompatActivity implements IAdminView
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private final String ATM_DATA_KEY = "atmData";
    private final String LIST_TITLE_KEY = "listTitleKey";
    private final String LIST_DATA_KEY = "listDataKey";

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

    /************************************************************************/
    /* Interface Implementation                                             */
    /************************************************************************/
    @Override
    public Serializable getAtmData()
    {
        return getIntent().getSerializableExtra(ATM_DATA_KEY);
    }

    @Override
    public void startAdminListActivity(Serializable listData)
    {
        Intent adminListIntent = new Intent(this, AdminListActivity.class);

        adminListIntent.putExtra(LIST_TITLE_KEY, getString(R.string.list_layout_title_client));
        adminListIntent.putExtra(LIST_DATA_KEY, listData);
        startActivity(adminListIntent);
    }


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
        presenter.handleOnClickClientList();
    }

    /************************************************************************/
    /* Private class methods                                                */
    /************************************************************************/
    //TODO
}