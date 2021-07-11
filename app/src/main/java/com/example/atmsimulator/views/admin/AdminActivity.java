package com.example.atmsimulator.views.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.atmsimulator.AdminListActivity;
import com.example.atmsimulator.R;
import com.example.atmsimulator.presenters.admin.AdminActivityPresenter;
import com.example.atmsimulator.views.EViewKey;

import java.io.Serializable;

public class AdminActivity extends AppCompatActivity implements IAdminView
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
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
    public void onBackPressed()
    {
        displayLogoutWarning();
    }

    /************************************************************************/
    /* Interface Implementation                                             */
    /************************************************************************/
    @Override
    public Serializable getAtmData()
    {
        return getIntent().getSerializableExtra(EViewKey.ATM_DATA.label);
    }

    @Override
    public void startAdminListActivity(Serializable listData)
    {
        Intent adminListIntent = new Intent(this, AdminListActivity.class);

        adminListIntent.putExtra(EViewKey.ADMIN_LIST_TITLE.label, getString(R.string.list_layout_title_client));
        adminListIntent.putExtra(EViewKey.ADMIN_LIST_DATA.label, listData);
        startActivity(adminListIntent);
    }


    /************************************************************************/
    /* Events Handling                                                      */
    /************************************************************************/
    public void onClickButtonLogout(View view)
    {
        displayLogoutWarning();
    }

    public void onClickPayInterest(View view)
    {
        presenter.handleOnClickPayInterest();
    }
    
    public void onClickCheckAccountList(View view)
    {
        presenter.handleOnClickCheckAccountList();
    }

    public void onCLickSavingAccountList(View view)
    {
        presenter.handleOnClickSavingAccountList();
    }

    public void onClickClientList(View view)
    {
        presenter.handleOnClickClientList();
    }

    /************************************************************************/
    /* Private class methods                                                */
    /************************************************************************/
    private void displayLogoutWarning()
    {
        AlertDialog.Builder logoutWarningBuilder = new AlertDialog.Builder(this);

        logoutWarningBuilder.setMessage("Do you want to log out.");
        logoutWarningBuilder.setCancelable(true);
        logoutWarningBuilder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(EViewKey.ATM_DATA.label, presenter.getAtmData());
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }
                });
        logoutWarningBuilder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });

        AlertDialog logoutWarning = logoutWarningBuilder.create();

        logoutWarning.show();
    }
}