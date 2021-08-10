package com.example.atmsimulator.atm.view;

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

import com.example.atmsimulator.models.account.SavingAccount;
import com.example.atmsimulator.models.users.User;
import com.example.atmsimulator.R;
import com.example.atmsimulator.atm.presenter.AtmActivityPresenter;
import com.example.atmsimulator.EViewKey;
import com.example.atmsimulator.models.account.Account;
import com.example.atmsimulator.models.account.CheckAccount;


import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class AtmView extends AppCompatActivity implements IAtmView {
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/

    
    ArrayList<Account> userAccounts;
    User user;


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm);

        userAccounts = (ArrayList<Account>) getIntent().getSerializableExtra(EViewKey.USER_ACCOUNTS.label);
        user = (User) getIntent().getSerializableExtra(EViewKey.USER.label);

        presenter = new AtmActivityPresenter(this);
        setAccountSummaryVisibility(accountSummaryVisibility);
        setTransactionSummaryVisibility(false);


    }

    @Override
    protected void onSaveInstanceState(@NonNull @org.jetbrains.annotations.NotNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (outState != null) {
            outState.putAll(getSaveInstanceBundle());
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
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
    public void onBackPressed() {
        displayLogoutWarning();
    }

    /************************************************************************/
    /* Interface Implementation                                             */

    /************************************************************************/
    public Serializable getUserAccounts() {
        return getIntent().getSerializableExtra(EViewKey.USER_ACCOUNTS.label);
    }

    /************************************************************************/
    /* Events Handling                                                      */
    /************************************************************************/


    /************************************************************************/
    /* Private class methods                                                */

    /************************************************************************/
    private Bundle getSaveInstanceBundle() {
        Bundle bundle = new Bundle();

        EditText editTextInput = findViewById(R.id.editTextInput);
        RadioGroup radioGroupTransaction = findViewById(R.id.radioGroupTransaction);
        int transactionCheckedRadioButtonID = radioGroupTransaction.getCheckedRadioButtonId();
        RadioGroup radioGroupAccount = findViewById(R.id.radioGroupAccount);
        int accountCheckedRadioButtonID = radioGroupAccount.getCheckedRadioButtonId();
        TextView textViewCheckAmount = findViewById(R.id.textViewCheckAmount);
        TextView textViewSavingAmount = findViewById(R.id.textViewSavingAmount);

        bundle.putString(KEY_INPUT, editTextInput.getText().toString());
        if (transactionCheckedRadioButtonID != -1) {
            bundle.putInt(KEY_RADIO_GROUP_TRANSACTION, transactionCheckedRadioButtonID);
        }
        if (accountCheckedRadioButtonID != -1) {
            bundle.putInt(KEY_RADIO_GROUP_ACCOUNT, accountCheckedRadioButtonID);
        }
        bundle.putString(KEY_CHECK_AMOUNT, textViewCheckAmount.getText().toString());
        bundle.putString(KEY_SAVING_AMOUNT, textViewSavingAmount.getText().toString());
        bundle.putBoolean(KEY_ACCOUNT_SUMMARY_VISIBILITY, accountSummaryVisibility);

        return bundle;
    }

    private void setAccountSummaryVisibility(boolean isVisible) {
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

    private void setTransactionSummaryVisibility(boolean isVisible) {
        int visibility = isVisible ? View.VISIBLE : View.INVISIBLE;
        TextView textViewTransactionSummary = findViewById(R.id.textViewTransactionSummary);

        textViewTransactionSummary.setVisibility(visibility);
    }

    private void displayLogoutWarning() {
        AlertDialog.Builder logoutWarningBuilder = new AlertDialog.Builder(this);

        logoutWarningBuilder.setMessage(getString(R.string.logout_warning_message));
        logoutWarningBuilder.setCancelable(true);
        logoutWarningBuilder.setPositiveButton(
                getString(R.string.logout_warning_button_yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(EViewKey.USER_ACCOUNTS.label, userAccounts);
                        returnIntent.putExtra(EViewKey.USER.label, user);
                        setResult(Activity.RESULT_OK, returnIntent);
                        dialog.cancel();
                        finish();
                    }
                });
        logoutWarningBuilder.setNegativeButton(
                getString(R.string.logout_warning_button_no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog logoutWarning = logoutWarningBuilder.create();

        logoutWarning.show();
    }


    public void onClickClear(View view)
    {
        EditText editTextInput = findViewById(R.id.editTextInput);

        editTextInput.setText("");

    }

    public void onClickSubmit(View view) {
        int radioButtonSelectedAccount;
        int radioButtonSelectedTransaction;
        double editTextAmount;

        setTransactionSummaryVisibility(true);

        TextView textViewTransactionSummary = findViewById(R.id.textViewTransactionSummary);
        EditText editTextAmountChange = findViewById(R.id.editTextInput);
        RadioGroup radioGroupAccount = findViewById(R.id.radioGroupAccount);
        RadioGroup radioGroupTransaction = findViewById((R.id.radioGroupTransaction));

        radioButtonSelectedTransaction = radioGroupTransaction.getCheckedRadioButtonId();
        radioButtonSelectedAccount = radioGroupAccount.getCheckedRadioButtonId();
        CheckAccount checkAccount = new CheckAccount();
        SavingAccount savingAccount = new SavingAccount();
        String StringTransactionSummary = editTextAmountChange.getText().toString();

        try
        {
            editTextAmount = Double.parseDouble(editTextAmountChange.getText().toString());
        }
        catch(NumberFormatException e)
        {
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_fieldEmpty));
            return;
        }

        if(StringTransactionSummary.length() > 0) {


            if (radioButtonSelectedAccount == R.id.radioButtonCheck) {


                for (Account account : userAccounts) {
                    if (account instanceof CheckAccount) {
                        checkAccount = (CheckAccount) account;

                    }
                    if (account instanceof SavingAccount) {
                        savingAccount = (SavingAccount) account;
                    }
                }

                if (radioButtonSelectedTransaction == R.id.radioButtonDeposit) {

                    if (checkAccount.deposit(editTextAmount) == true)
                    {
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_transaction_deposit) + " " + editTextAmountChange.getText().toString() + " " + getString(R.string.atm_activity_textView_transaction_checkAccount));
                    }
                    if (editTextAmount > 0)
                    {
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_errorAmountZero));
                    }


                    editTextAmountChange.setText("");

                }
                if (radioButtonSelectedTransaction == R.id.radioButtonWidthdraw) {
                    if(checkAccount.withdraw(editTextAmount) == true)
                    {
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_transaction_withdraw) + " " + editTextAmountChange.getText().toString() + " " + getString(R.string.atm_activity_textView_transaction_checkAccount));
                    }
                    if(editTextAmount % 10 != 0)
                    {
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_errorAmountDivisableBy10));
                    }
                    if(editTextAmount> 1000)
                    {
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_errorAmount1000));
                    }

                    editTextAmountChange.setText("");
                }
                if (radioButtonSelectedTransaction == R.id.radioButtonTransfer) {
                    if (editTextAmount > 100000) {
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_maximumTransfer));
                    } else if (editTextAmount > savingAccount.getAccountAmount()) {
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_notEnoughMoney));
                    } else {
                        checkAccount.setAccountAmount(checkAccount.getAccountAmount() + editTextAmount);
                        savingAccount.setAccountAmount(savingAccount.getAccountAmount() - editTextAmount);
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_Transfer) + " " + editTextAmountChange.getText().toString() + " " + getString(R.string.atm_activity_textView_Transfer_savingToCheck));
                        editTextAmountChange.setText("");
                    }


                }
            }
            if (radioButtonSelectedAccount == R.id.radioButtonSaving) {
                for (Account account : userAccounts) {
                    if (account instanceof CheckAccount) {
                        checkAccount = (CheckAccount) account;

                    }
                    if (account instanceof SavingAccount) {
                        savingAccount = (SavingAccount) account;
                    }
                }

                if (radioButtonSelectedTransaction == R.id.radioButtonDeposit) {
                    if(savingAccount.deposit(editTextAmount)== true)
                    {
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_transaction_withdraw) + " " + editTextAmountChange.getText().toString() + " " + getString(R.string.atm_activity_textView_transaction_checkAccount));
                    }
                    if (editTextAmount > 0)
                    {
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_errorAmountZero));
                    }

                    editTextAmountChange.setText("");
                }
                if (radioButtonSelectedTransaction == R.id.radioButtonWidthdraw) {
                    if(savingAccount.withdraw(editTextAmount) == true)
                    {
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_transaction_withdraw) + " " + editTextAmountChange.getText().toString() + " " + getString(R.string.atm_activity_textView_transaction_savingAccount));
                    }
                    if(editTextAmount % 10 != 0)
                    {
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_errorAmountDivisableBy10));
                    }
                    if(editTextAmount> 1000)
                    {
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_errorAmount1000));
                    }

                    editTextAmountChange.setText("");
                }
                if (radioButtonSelectedTransaction == R.id.radioButtonTransfer) {
                    if (editTextAmount > 100000) {
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_maximumTransfer));
                    }
                    if (editTextAmount > checkAccount.getAccountAmount()) {
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_notEnoughMoney));
                    } else {
                        savingAccount.setAccountAmount(savingAccount.getAccountAmount() + editTextAmount);
                        checkAccount.setAccountAmount(checkAccount.getAccountAmount() - editTextAmount);
                        textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_Transfer) + " " + editTextAmountChange.getText().toString() + " " + getString(R.string.atm_activity_textView_Transfer_checkToSaving));
                        editTextAmountChange.setText("");
                    }

                }
            }
            updateAccountSummary();
        }

    }



    public void onClickZero(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);
        String textInputString = textInput.getText().toString();

        if(textInputString.length() <= 0)
        {
            textInput.setText(textInput.getText() + "");
        }
        else
        {
            textInput.setText(textInput.getText() + "0");
        }
        textInput.setSelection(textInput.getText().length());
    }

    public void onClickOne(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        textInput.setText(textInput.getText() + "1");
        textInput.setSelection(textInput.getText().length());
    }
    public void onClickTwo(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        textInput.setText(textInput.getText() + "2");
        textInput.setSelection(textInput.getText().length());
    }

    public void onClickThree(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        textInput.setText(textInput.getText() + "3");
        textInput.setSelection(textInput.getText().length());
    }

    public void onClickFour(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        textInput.setText(textInput.getText() + "4");
        textInput.setSelection(textInput.getText().length());
    }

    public void onClickFive(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        textInput.setText(textInput.getText() + "5");
        textInput.setSelection(textInput.getText().length());
    }

    public void onClickSix(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        textInput.setText(textInput.getText() + "6");
        textInput.setSelection(textInput.getText().length());
    }

    public void onClickSeven(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        textInput.setText(textInput.getText() + "7");
        textInput.setSelection(textInput.getText().length());
    }

    public void onClickEight(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        textInput.setText(textInput.getText() + "8");
        textInput.setSelection(textInput.getText().length());
    }

    public void onClickNine(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        textInput.setText(textInput.getText() + "9");
        textInput.setSelection(textInput.getText().length());
    }

    public void onClickDot(View view)
    {


        EditText textInput = findViewById(R.id.editTextInput);
        String textImputString =  textInput.getText().toString();
        textImputString.contains(".");

        if(!textImputString.contains("."))
        {
            textInput.setText(textInput.getText() + ".");

        }
        else
        {

            textInput.setText(textInput.getText() + "");
        }
        textInput.setSelection(textInput.getText().length());

    }

    public void OnClickLogout(View view)
    {
         displayLogoutWarning();

    }

    public void onClickSummary(View view)
    {

        if (accountSummaryVisibility == true)
        {
            setAccountSummaryVisibility(false);
            accountSummaryVisibility = false;

        }
        else
        {
            setAccountSummaryVisibility(true);
            accountSummaryVisibility = true;

        }
        updateAccountSummary();


    }
    private void updateAccountSummary()
    {
        TextView balanceCheck = findViewById(R.id.textViewCheckAmount);
        TextView balanceSaving = findViewById(R.id.textViewSavingAmount);
        CheckAccount checkAccount = new CheckAccount();
        SavingAccount savingAccount = new SavingAccount();
        DecimalFormat decimalFormat = new DecimalFormat("#.##$");


        for (Account account : userAccounts)
        {
            if (account instanceof CheckAccount)
            {
                checkAccount = (CheckAccount) account;

            }
            if(account instanceof SavingAccount)
            {
                savingAccount = (SavingAccount) account;
            }
        }
        balanceCheck.setText(decimalFormat.format(checkAccount.getAccountAmount()));
        balanceSaving.setText(decimalFormat.format(savingAccount.getAccountAmount()));

    }

}
















