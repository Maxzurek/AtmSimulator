package com.example.atmsimulator.views.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.atmsimulator.R;

public class AdminActivity extends AppCompatActivity implements IAdminView
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }
}