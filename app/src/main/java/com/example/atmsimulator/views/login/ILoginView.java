package com.example.atmsimulator.views.login;

import com.example.atmsimulator.presenters.login.LoginActivityPresenter;

public interface ILoginView
{
    void startAtmActivity();

    void displayEmptyUsernameError();

    void displayEmptyNIPError();

    void displayInvalidLoginError();

    void displayLoginAttemptsError();
}
