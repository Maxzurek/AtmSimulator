package com.example.atmsimulator.views.login;

import com.example.atmsimulator.presenters.login.LoginActivityPresenter;

import java.io.Serializable;

public interface ILoginView
{
    Serializable getAtmData();

    void startAtmActivity(Serializable atmData);

    void startAdminActivity(Serializable atmData);

    void displayErrorMessage(String errorMessage);

    void hideErrorMessage();
}
