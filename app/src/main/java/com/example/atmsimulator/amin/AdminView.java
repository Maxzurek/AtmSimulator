package com.example.atmsimulator.amin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.atmsimulator.R;
import com.example.atmsimulator.EViewKey;
import com.example.atmsimulator.models.AtmData;
import com.example.atmsimulator.models.account.Account;
import com.example.atmsimulator.models.account.SavingAccount;

import java.util.ArrayList;

public class AdminView extends AppCompatActivity
{
    /************************************************************************/
    /* Overridden Methods                                                   */
    /************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    @Override
    public void onBackPressed()
    {
        displayLogoutWarning();
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
        ArrayList<Account> accounts = getAtmData().getAccounts();

        for(Account account : accounts)
        {
            if(account instanceof SavingAccount)
            {
               ((SavingAccount) account).interestPayment();
            }
        }

        Toast.makeText(this, R.string.admin_activity_interestComplete, Toast.LENGTH_LONG).show();
    }
    
    public void onClickCheckAccountList(View view)
    {
        Intent adminListIntent = new Intent(this, AdminListView.class);

        adminListIntent.putExtra(EViewKey.ADMIN_LIST_TITLE.label, getString(R.string.list_layout_title_account_check));
        adminListIntent.putExtra(EViewKey.ADMIN_LIST_DATA.label, getAtmData().getCheckAccounts());
        startActivity(adminListIntent);
    }

    public void onCLickSavingAccountList(View view)
    {
        Intent adminListIntent = new Intent(this, AdminListView.class);

        adminListIntent.putExtra(EViewKey.ADMIN_LIST_TITLE.label, getString(R.string.list_layout_title_account_saving));
        adminListIntent.putExtra(EViewKey.ADMIN_LIST_DATA.label, getAtmData().getSavingAccounts());
        startActivity(adminListIntent);
    }

    public void onClickClientList(View view)
    {
        Intent adminListIntent = new Intent(this, AdminListView.class);

        adminListIntent.putExtra(EViewKey.ADMIN_LIST_TITLE.label, getString(R.string.list_layout_title_client));
        adminListIntent.putExtra(EViewKey.ADMIN_LIST_DATA.label, getAtmData().getClients());
        startActivity(adminListIntent);
    }

    /************************************************************************/
    /* Private class methods                                                */
    /************************************************************************/
    private AtmData getAtmData()
    {
        return (AtmData) getIntent().getSerializableExtra(EViewKey.ATM_DATA.label);
    }

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
                        returnIntent.putExtra(EViewKey.ATM_DATA.label, getAtmData());
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