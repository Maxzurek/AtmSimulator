package com.example.atmsimulator.atm;

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

import com.example.atmsimulator.models.account.SavingAccount;
import com.example.atmsimulator.models.users.User;
import com.example.atmsimulator.R;
import com.example.atmsimulator.EViewKey;
import com.example.atmsimulator.models.account.Account;
import com.example.atmsimulator.models.account.CheckAccount;


import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class AtmView extends AppCompatActivity
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

    private boolean accountSummaryVisibility = false;
    private ArrayList<Account> userAccounts;
    private User user;

    /************************************************************************/
    /* Overridden Methods                                                   */
    /************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm);

        userAccounts = (ArrayList<Account>) getIntent().getSerializableExtra(EViewKey.USER_ACCOUNTS.label);
        user = (User) getIntent().getSerializableExtra(EViewKey.USER.label);

        setAccountSummaryVisibility(accountSummaryVisibility);
        setTransactionSummaryVisibility(false);
    }

    @Override
    protected void onSaveInstanceState(@NonNull @org.jetbrains.annotations.NotNull Bundle outState)
    {
        super.onSaveInstanceState(outState);

        if (outState != null)
        {
            outState.putAll(getSaveInstanceBundle());
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null)
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
    public void onClickClear(View view)
    {
        EditText editTextInput = findViewById(R.id.editTextInput);

        editTextInput.setText("");
    }

    public void onClickSubmit(View view)
    {
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
        String StringTransactionSummary = editTextAmountChange.getText().toString();

        try
        {
            editTextAmount = Double.parseDouble(editTextAmountChange.getText().toString());
        }
        catch(NumberFormatException e)
        {
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_errorAmountZero));
            return;
        }

        if(StringTransactionSummary.length() > 0)
        {
            if (radioButtonSelectedAccount == R.id.radioButtonCheck)
            {
                getCheckAccount();

                if (radioButtonSelectedTransaction == R.id.radioButtonDeposit)
                {
                    checkDeposit(editTextAmount);
                }
                if (radioButtonSelectedTransaction == R.id.radioButtonWidthdraw)
                {

                    checkWithdraw(editTextAmount);
                }
                if (radioButtonSelectedTransaction == R.id.radioButtonTransfer)
                {
                    savingToCheckTransfer(editTextAmount);
                }
            }
            if (radioButtonSelectedAccount == R.id.radioButtonSaving)
            {
                getSavingAccount();

                if (radioButtonSelectedTransaction == R.id.radioButtonDeposit)
                {
                    savingDeposit(editTextAmount);
                }
                if (radioButtonSelectedTransaction == R.id.radioButtonWidthdraw)
                {
                    savingWithdraw(editTextAmount);
                }
                if (radioButtonSelectedTransaction == R.id.radioButtonTransfer)
                {
                    checkToSavingTransfer(editTextAmount);
                }
            }
            updateAccountSummary();
        }
    }

    public void onClickZero(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);
        String textInputString = textInput.getText().toString();

        if(textInputString.length() == 0)
        {
            return;
        }
        else
        {
            concatToTextInput("0");
        }
    }

    public void onClickOne(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        concatToTextInput("1");
    }
    public void onClickTwo(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        concatToTextInput("2");
    }

    public void onClickThree(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        concatToTextInput("3");
    }

    public void onClickFour(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        concatToTextInput("4");
    }

    public void onClickFive(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        concatToTextInput("5");
    }

    public void onClickSix(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        concatToTextInput("6");
    }

    public void onClickSeven(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        concatToTextInput("7");
    }

    public void onClickEight(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        concatToTextInput("8");
    }

    public void onClickNine(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        concatToTextInput("9");
    }

    public void onClickDot(View view)
    {
        EditText textInput = findViewById(R.id.editTextInput);
        String textInputString =  textInput.getText().toString();

        if(textInputString.contains("."))
        {
            return;
        }
        else
        {
            concatToTextInput(".");
        }
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

        if (transactionCheckedRadioButtonID != -1)
        {
            bundle.putInt(KEY_RADIO_GROUP_TRANSACTION, transactionCheckedRadioButtonID);
        }
        if (accountCheckedRadioButtonID != -1)
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

    private void checkDeposit(double amount)
    {
        EditText editTextAmountChange = findViewById(R.id.editTextInput);
        TextView textViewTransactionSummary = findViewById(R.id.textViewTransactionSummary);

        if (amount < 0)
        {
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_errorAmountZero));
        }
        else
        {
            getCheckAccount().deposit(amount);
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_transaction_deposit_prefix) + " " + editTextAmountChange.getText().toString() + "$ " + getString(R.string.atm_activity_textView_transaction_deposit_checkAccount_suffix));
        }

        editTextAmountChange.setText("");
    }

    private void savingDeposit(double amount)
    {
        EditText editTextAmountChange = findViewById(R.id.editTextInput);
        TextView textViewTransactionSummary = findViewById(R.id.textViewTransactionSummary);

        if (amount < 0)
        {
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_errorAmountZero));
        }
        else
        {
            getSavingAccount().deposit(amount);
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_transaction_deposit_prefix) + " " + editTextAmountChange.getText().toString() + "$ " + getString(R.string.atm_activity_textView_transaction_deposit_checkAccount_suffix));
        }

        editTextAmountChange.setText("");
    }

    private void savingWithdraw(double amount)
    {
        EditText editTextAmountChange = findViewById(R.id.editTextInput);
        TextView textViewTransactionSummary = findViewById(R.id.textViewTransactionSummary);

        if(getSavingAccount().withdraw(amount))
        {
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_transaction_withdraw) + " " + editTextAmountChange.getText().toString() + "$ " + getString(R.string.atm_activity_textView_transaction_withdraw_savingAccount_suffix));
        }
        if(amount % 10 != 0)
        {
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_error_amount_modulo10));
        }
        if(amount> 1000)
        {
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_errorAmount1000));
        }
        if(amount > getSavingAccount().getAccountAmount())
        {

        }

        editTextAmountChange.setText("");
    }

    private void checkWithdraw(double amount)
    {
        EditText editTextAmountChange = findViewById(R.id.editTextInput);
        TextView textViewTransactionSummary = findViewById(R.id.textViewTransactionSummary);

        if(getCheckAccount().withdraw(amount))
        {
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_transaction_withdraw) + " " + editTextAmountChange.getText().toString() + "$ " + getString(R.string.atm_activity_textView_transaction_withdraw_checkAccount_suffix));
        }
        if(amount % 10 != 0)
        {
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_error_amount_modulo10));
        }
        if(amount> 1000)
        {
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_errorAmount1000));
        }
        if(amount > getCheckAccount().getAccountAmount())
        {

        }

        editTextAmountChange.setText("");
    }

    private void checkToSavingTransfer(double amount)
    {
        EditText editTextAmountChange = findViewById(R.id.editTextInput);
        TextView textViewTransactionSummary = findViewById(R.id.textViewTransactionSummary);

        if (amount > 100000)
        {
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_maximumTransfer));
        }
        else if (amount > getCheckAccount().getAccountAmount())
        {
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_insufficient_funds));

        }
        else
        {
            getSavingAccount().setAccountAmount(getSavingAccount().getAccountAmount() + amount);
            getCheckAccount().setAccountAmount(getCheckAccount().getAccountAmount() - amount);
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_Transfer) + " " + editTextAmountChange.getText().toString() + "$ " + getString(R.string.atm_activity_textView_Transfer_checkToSaving));
        }
        editTextAmountChange.setText("");
    }

    private void savingToCheckTransfer(double amount)
    {
        EditText editTextAmountChange = findViewById(R.id.editTextInput);
        TextView textViewTransactionSummary = findViewById(R.id.textViewTransactionSummary);

        if (amount > 100000)
        {
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_maximumTransfer));
        }
        else if (amount > getSavingAccount().getAccountAmount())
        {
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_insufficient_funds));
        }
        else
        {
            getCheckAccount().setAccountAmount(getCheckAccount().getAccountAmount() + amount);
            getSavingAccount().setAccountAmount(getSavingAccount().getAccountAmount() - amount);
            textViewTransactionSummary.setText(getString(R.string.atm_activity_textView_Transfer) + " " + editTextAmountChange.getText().toString() + "$ " + getString(R.string.atm_activity_textView_Transfer_savingToCheck));
        }
        editTextAmountChange.setText("");
    }

    private CheckAccount getCheckAccount()
    {
        CheckAccount checkAccount = null;

        for (Account account : userAccounts)
        {
            if (account instanceof CheckAccount)
            {
                checkAccount = (CheckAccount) account;
            }
        }
        return checkAccount;
    }

    private SavingAccount getSavingAccount()
    {
        SavingAccount savingAccount = null;

        for (Account account : userAccounts)
        {
            if (account instanceof SavingAccount)
            {
                savingAccount = (SavingAccount) account;
            }
        }
        return savingAccount;
    }

    private void concatToTextInput(String text)
    {
        EditText textInput = findViewById(R.id.editTextInput);

        textInput.setText(textInput.getText() + text);
        textInput.setSelection(textInput.getText().length());
    }
}