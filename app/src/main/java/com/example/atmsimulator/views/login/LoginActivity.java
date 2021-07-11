package com.example.atmsimulator.views.login;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.atmsimulator.R;
import com.example.atmsimulator.presenters.login.LoginActivityPresenter;
import com.example.atmsimulator.views.EViewKey;
import com.example.atmsimulator.views.admin.AdminActivity;
import com.example.atmsimulator.views.atm.AtmActivity;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity implements ILoginView
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private static final String USERNAME_KEY = "username";
    private static final String NIP_KEY = "nip";

    private ActivityResultLauncher<Intent> atmActivityLauncher;
    private ActivityResultLauncher<Intent> adminActivityLauncher;
    private LoginActivityPresenter presenter;
    private boolean textViewErrorVisibility = false;

    /************************************************************************/
    /* Overridden Methods                                                   */
    /************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginActivityPresenter(this);

        initAtmActivityLauncher();
        initAdminActivityLauncher();
        setTextViewErrorVisibility(textViewErrorVisibility);
    }

    @Override
    protected void onSaveInstanceState(@NonNull @NotNull Bundle outState)
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

        EditText editTextUsername = findViewById(R.id.editTextUsername);
        EditText editTextNIP = findViewById(R.id.editTextNIP);

        if(savedInstanceState != null)
        {
            editTextUsername.setText(savedInstanceState.getString(USERNAME_KEY));
            editTextNIP.setText(savedInstanceState.getString(NIP_KEY));
        }
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
    public void startAtmActivity(Serializable userAccounts)
    {
        Intent atmIntent = new Intent(this, AtmActivity.class);

        atmIntent.putExtra(EViewKey.USER_ACCOUNTS.label, userAccounts);
        atmActivityLauncher.launch(atmIntent);
    }

    @Override
    public void startAdminActivity(Serializable atmData)
    {
        Intent adminIntent = new Intent(this, AdminActivity.class);

        adminIntent.putExtra(EViewKey.ATM_DATA.label, atmData);
        adminActivityLauncher.launch(adminIntent);
    }

    @Override
    public void displayErrorMessage(String errorMessage)
    {
        TextView textViewError = findViewById(R.id.textViewError);
        setTextViewErrorVisibility(true);

        textViewError.setText(errorMessage);
    }

    @Override
    public void hideErrorMessage()
    {
        setTextViewErrorVisibility(false);
    }

    /************************************************************************/
    /* Events Handling                                                      */
    /************************************************************************/
    public void onClickButtonSignIn(View view)
    {
        EditText editTextUserName = findViewById(R.id.editTextUsername);
        EditText editTextNIP = findViewById(R.id.editTextNIP);
        String userName = editTextUserName.getText().toString();
        String NIP = editTextNIP.getText().toString();

        presenter.attemptLogin(this, userName, NIP);
    }

    /************************************************************************/
    /* Private class methods                                                */
    /************************************************************************/
    private void initAtmActivityLauncher()
    {
        atmActivityLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    (ActivityResult result) ->
                    {
                        if (result.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent data = result.getData();
                            presenter.updateUserAccounts(data.getSerializableExtra(EViewKey.USER_ACCOUNTS.label));
                        }
                    });
    }

    private void initAdminActivityLauncher()
    {
        adminActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                (ActivityResult result) ->
                {
                    if (result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent data = result.getData();
                        presenter.updateAtmData(data.getSerializableExtra(EViewKey.ATM_DATA.label));
                    }
                });
    }

    private Bundle getSaveInstanceBundle()
    {
        Bundle bundle = new Bundle();
        EditText editTextUsername = findViewById(R.id.editTextUsername);
        EditText editTextNIP = findViewById(R.id.editTextNIP);

        bundle.putString(USERNAME_KEY, editTextUsername.getText().toString());
        bundle.putString(NIP_KEY, editTextNIP.getText().toString());

        return bundle;
    }

    private void setTextViewErrorVisibility(boolean isVisible)
    {
        int visibility = isVisible ? View.VISIBLE : View.INVISIBLE;
        TextView textViewError= (TextView)findViewById(R.id.textViewError);

        textViewError.setVisibility(visibility);
        textViewErrorVisibility = isVisible;
    }
}