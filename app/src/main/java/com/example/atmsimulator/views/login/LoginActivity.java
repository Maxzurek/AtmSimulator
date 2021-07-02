package com.example.atmsimulator.views.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.atmsimulator.R;
import com.example.atmsimulator.presenters.login.LoginActivityPresenter;
import com.example.atmsimulator.views.atm.AtmActivity;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity implements ILoginView
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private static final String KEY_USERNAME = "key_username";
    private static final String KEY_NIP = "key_nip";

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
            editTextUsername.setText(savedInstanceState.getString(KEY_USERNAME));
            editTextNIP.setText(savedInstanceState.getString(KEY_NIP));
        }
    }

    /************************************************************************/
    /* Interface Implementation                                             */
    /************************************************************************/
    @Override
    public void startAtmActivity()
    {
        Intent atmScreen = new Intent(this, AtmActivity.class);

        startActivity(atmScreen);
    }

    @Override
    public void displayEmptyUsernameError()
    {
        TextView textViewError = findViewById(R.id.textViewError);
        String errorMessage = getResources().getString(R.string.login_activity_textview_emptyUsername_error);

        setTextViewErrorVisibility(true);
        textViewError.setText(errorMessage);
    }

    @Override
    public void displayEmptyNIPError()
    {
        TextView textViewError = findViewById(R.id.textViewError);
        String errorMessage = getResources().getString(R.string.login_activity_textview_emptyNIP_error);

        setTextViewErrorVisibility(true);
        textViewError.setText(errorMessage);
    }

    @Override
    public void displayInvalidLoginError(final String REMAINING_ATTEMPT)
    {
        TextView textViewError = findViewById(R.id.textViewError);
        String errorMessage = getResources().getString(R.string.login_activity_textview_invalidLogin_error)
                +"\n"+REMAINING_ATTEMPT
                +" "+getResources().getString(R.string.login_activity_textview_invalidLogin_errorEnding);

        setTextViewErrorVisibility(true);
        textViewError.setText(errorMessage);
    }

    @Override
    public void displayLoginAttemptsError(final String LOGIN_LOCK_WAIT_TIME_REMAINING)
    {
        TextView textViewError = findViewById(R.id.textViewError);
        String errorMessage = getResources().getString(R.string.login_activity_textview_loginAttempts_error)
                +" "+LOGIN_LOCK_WAIT_TIME_REMAINING
                +" "+getResources().getString(R.string.login_activity_textview_loginAttempts_errorEnding);

        setTextViewErrorVisibility(true);
        textViewError.setText(errorMessage);
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

        presenter.attemptLogin(userName, NIP);
    }

    /************************************************************************/
    /* Private class methods                                                */
    /************************************************************************/
    private Bundle getSaveInstanceBundle()
    {
        Bundle bundle = new Bundle();
        EditText editTextUsername = findViewById(R.id.editTextUsername);
        EditText editTextNIP = findViewById(R.id.editTextNIP);

        bundle.putString(KEY_USERNAME, editTextUsername.getText().toString());
        bundle.putString(KEY_NIP, editTextNIP.getText().toString());

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