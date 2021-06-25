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
import com.example.atmsimulator.views.admin.IAdminView;
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
    /* Override Methods                                                     */
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
    /************************************************************************/
    /* Events Handling                                                      */
    /************************************************************************/
    public void onClickButtonSignIn(View view)
    {
        Intent atmScreen = new Intent(this, AtmActivity.class);

        startActivity(atmScreen);
    }
}