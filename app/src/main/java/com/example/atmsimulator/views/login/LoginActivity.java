package com.example.atmsimulator.views.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.atmsimulator.R;
import com.example.atmsimulator.views.atm.AtmActivity;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity
{
    private static final String KEY_USERNAME = "key_username";
    private static final String KEY_NIP = "key_nip";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

    public void onClickButtonSignIn(View view)
    {
        Intent atmScreen = new Intent(this, AtmActivity.class);

        startActivity(atmScreen);
    }

    private Bundle getSaveInstanceBundle()
    {
        Bundle bundle = new Bundle();
        EditText editTextUsername = findViewById(R.id.editTextUsername);
        EditText editTextNIP = findViewById(R.id.editTextNIP);

        bundle.putString(KEY_USERNAME, editTextUsername.getText().toString());
        bundle.putString(KEY_NIP, editTextNIP.getText().toString());

        return bundle;
    }
}