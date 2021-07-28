package com.example.atmsimulator.views.atm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atmsimulator.R;
import com.example.atmsimulator.presenters.atm.AtmActivityPresenter;
import com.example.atmsimulator.views.EViewKey;

import java.io.Serializable;

public class AtmActivity extends AppCompatActivity implements IAtmView
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private final String KEY_INPUT = "key_input";
    private final String KEY_RADIO_GROUP_TRANSACTION = "key_radio_group_transaction";
    private final String KEY_RADIO_GROUP_ACCOUNT = "key_radio_group_account";
    private final String KEY_CHECK_AMOUNT = "key_check_amount";
    private final String KEY_SAVING_AMOUNT = "key_saving_amount";
    private final String KEY_ACCOUNT_SUMMARY_VISIBILITY = "key_account_summary_visibility";

    private AtmActivityPresenter presenter;
    private boolean accountSummaryVisibility = false;

    /************************************************************************/
    /* Overridden Methods                                                   */
    /************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm);

        presenter = new AtmActivityPresenter(this);
        setAccountSummaryVisibility(accountSummaryVisibility);
        setTransactionSummaryVisibility(false);
    }

    @Override
    protected void onSaveInstanceState(@NonNull @org.jetbrains.annotations.NotNull Bundle outState)
    {
        super.onSaveInstanceState(outState);

        if(outState != null)
        {
            outState.putAll(getSaveInstanceBundle());
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null)
        {
            EditText editTextInput = findViewById(R.id.editTextInput);
            RadioGroup radioGroupTransaction = findViewById(R.id.radioGroupTransaction);
            RadioGroup radioGroupAccount = findViewById(R.id.radioGroupAccount);
            TextView textViewCheckAmount = findViewById(R.id.textViewCheckAmount);
            TextView textViewSavingAmount = findViewById(R.id.textViewSavingAmount);

            editTextInput.setText(savedInstanceState.getString(KEY_INPUT));
            radioGroupTransaction.check(savedInstanceState.getInt(KEY_RADIO_GROUP_TRANSACTION));
            radioGroupAccount.check(savedInstanceState.getInt(KEY_RADIO_GROUP_ACCOUNT));
            textViewCheckAmount.setText(savedInstanceState.getString(KEY_CHECK_AMOUNT));
            textViewSavingAmount.setText(savedInstanceState.getString(KEY_SAVING_AMOUNT));

            setAccountSummaryVisibility(savedInstanceState.getBoolean(KEY_ACCOUNT_SUMMARY_VISIBILITY));
        }
    }

    @Override
    public void onBackPressed()
    {
       displayLogoutWarning();
    }

    /************************************************************************/
    /* Interface Implementation                                             */
    /************************************************************************/
    public Serializable getUserAccounts()
    {
        return getIntent().getSerializableExtra(EViewKey.USER_ACCOUNTS.label);
    }

    /************************************************************************/
    /* Events Handling                                                      */
    /************************************************************************/


    /************************************************************************/
    /* Private class methods                                                */
    /************************************************************************/
    private Bundle getSaveInstanceBundle()
    {
        Bundle bundle = new Bundle();

        EditText editTextInput = findViewById(R.id.editTextInput);
        RadioGroup radioGroupTransaction = findViewById(R.id.radioGroupTransaction);
        int transactionCheckedRadioButtonID = radioGroupTransaction.getCheckedRadioButtonId();
        RadioGroup radioGroupAccount = findViewById(R.id.radioGroupAccount);
        int accountCheckedRadioButtonID = radioGroupAccount.getCheckedRadioButtonId();
        TextView textViewCheckAmount = findViewById(R.id.textViewCheckAmount);
        TextView textViewSavingAmount = findViewById(R.id.textViewSavingAmount);

        bundle.putString(KEY_INPUT, editTextInput.getText().toString());
        if(transactionCheckedRadioButtonID != -1)
        {
            bundle.putInt(KEY_RADIO_GROUP_TRANSACTION, transactionCheckedRadioButtonID);
        }
        if(accountCheckedRadioButtonID != -1)
        {
            bundle.putInt(KEY_RADIO_GROUP_ACCOUNT, accountCheckedRadioButtonID);
        }
        bundle.putString(KEY_CHECK_AMOUNT, textViewCheckAmount.getText().toString());
        bundle.putString(KEY_SAVING_AMOUNT, textViewSavingAmount.getText().toString());
        bundle.putBoolean(KEY_ACCOUNT_SUMMARY_VISIBILITY, accountSummaryVisibility);

        return bundle;
    }

    private void setAccountSummaryVisibility(boolean isVisible)
    {
        int visibility = isVisible ? View.VISIBLE : View.INVISIBLE;
        TextView textViewCheck = findViewById(R.id.textViewCheck);
        TextView textViewSaving = findViewById(R.id.textViewSaving);
        TextView textViewCheckAmount = findViewById(R.id.textViewCheckAmount);
        TextView textViewSavingAmount = findViewById(R.id.textViewSavingAmount);

        textViewCheck.setVisibility(visibility);
        textViewSaving.setVisibility(visibility);
        textViewCheckAmount.setVisibility(visibility);
        textViewSavingAmount.setVisibility(visibility);

        accountSummaryVisibility = isVisible;
    }

    private void setTransactionSummaryVisibility(boolean isVisible)
    {
        int visibility = isVisible ? View.VISIBLE : View.INVISIBLE;
        TextView textViewTransactionSummary = findViewById(R.id.textViewTransactionSummary);

        textViewTransactionSummary.setVisibility(visibility);
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
                        Intent returnIntent = new Intent();
                        //TODO get accounts data from presenter and add them to the intent
                        //returnIntent.putExtra(USER_ACCOUNTS_KEY, /*accounts*/)
                        setResult(Activity.RESULT_OK, returnIntent);
                        dialog.cancel();
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