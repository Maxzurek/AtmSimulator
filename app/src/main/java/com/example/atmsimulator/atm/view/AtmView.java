package com.example.atmsimulator.atm.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.atmsimulator.R;
import com.example.atmsimulator.atm.presenter.AtmActivityPresenter;
import com.example.atmsimulator.views.EViewKey;

import java.io.Serializable;

public class AtmView extends AppCompatActivity implements IAtmView
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button buttonDot;
    Button buttonClear;
    EditText editTextInput;


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

        button0 = (Button) findViewById (R. id.button0);
        button1 = (Button) findViewById (R. id.button1);
        button2 = (Button) findViewById (R. id.button2);
        button3 = (Button) findViewById (R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonDot = (Button) findViewById(R.id.buttonDot);
        buttonClear = (Button) findViewById(R.id.buttonClear);

        editTextInput = (EditText) findViewById(R.id.editTextInput);

        presenter = new AtmActivityPresenter(this);
        setAccountSummaryVisibility(accountSummaryVisibility);
        setTransactionSummaryVisibility(false);

        button0.setOnClickListener (view -> chiffreClick("0"));

        button1.setOnClickListener (view -> chiffreClick("1"));

        button2.setOnClickListener (view -> chiffreClick("2"));

        button3.setOnClickListener (view -> chiffreClick("3"));

        button4.setOnClickListener (view -> chiffreClick("4"));

        button5.setOnClickListener (view -> chiffreClick("5"));

        button6.setOnClickListener (view -> chiffreClick("6"));

        button7.setOnClickListener (view -> chiffreClick("7"));

        button8.setOnClickListener (view -> chiffreClick("8"));

        button9.setOnClickListener (view -> chiffreClick("9"));

        buttonDot.setOnClickListener (view -> chiffreClick("."));
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
                        returnIntent.putExtra(EViewKey.ATM_DATA.label, presenter.getUserAccounts());
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
    public void chiffreClick (String nombre) {

        if(!editTextInput. getText (). equals ("0"))

            nombre = editTextInput.getText() + nombre;

        editTextInput. setText (nombre);
    }

    public void onClickClear(View view) {

        editTextInput.setText("");
    }

    /*public void onClickSubmit(View view) {
        int radioButtonSelectedAccount;
        int radioButtonSelectedTransaction;

        RadioGroup radioGroupAccount = findViewById(R.id.radioGroupAccount);
        RadioGroup radioGroupTransaction = findViewById((R.id.radioGroupTransaction));

        radioButtonSelectedTransaction = radioGroupTransaction.getCheckedRadioButtonId();
        radioButtonSelectedAccount = radioGroupAccount.getCheckedRadioButtonId();

        if(radioButtonSelectedAccount == R.id.radioButtonCheck)
        {
            if(radioButtonSelectedTransaction == R.id.radioButtonDeposit)
            {

            }
            if(radioButtonSelectedTransaction == R.id.radioButtonWidthdraw) {

            }
        }
        else{


        }






    }*/
}