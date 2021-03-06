package com.example.atmsimulator.admin.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.atmsimulator.AdminListView;
import com.example.atmsimulator.R;
import com.example.atmsimulator.admin.presenter.AdminActivityPresenter;
import com.example.atmsimulator.views.EViewKey;

import java.io.Serializable;

public class AdminView extends AppCompatActivity implements IAdminView
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



    /************************************************************************/
    /* Events Handling                                                      */
    /************************************************************************/
    public void onClickButtonLogout(View view)
    {
        displayLogoutWarning();
    }

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
        Intent adminListIntent = new Intent(this, AdminListView.class);

        adminListIntent.putExtra(EViewKey.ADMIN_LIST_TITLE.label, getString(R.string.list_layout_title_client));
        adminListIntent.putExtra(EViewKey.ADMIN_LIST_DATA.label, getAtmData());
        startActivity(adminListIntent);
    }

    /************************************************************************/
    /* Private class methods                                                */
    /************************************************************************/
    private void displayLogoutWarning()
    {
        AlertDialog.Builder logoutWarningBuilder = new AlertDialog.Builder(this);

        logoutWarningBuilder.setMessage(getString(R.string.logout_warning_message));
        logoutWarningBuilder.setCancelable(true);
        logoutWarningBuilder.setPositiveButton(
                getString(R.string.logout_warning_button_yes),
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
                getString(R.string.logout_warning_button_no),
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