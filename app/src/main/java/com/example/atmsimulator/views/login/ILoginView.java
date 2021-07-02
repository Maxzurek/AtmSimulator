package com.example.atmsimulator.views.login;

import com.example.atmsimulator.presenters.login.LoginActivityPresenter;

import java.io.Serializable;

public interface ILoginView
{
    Serializable getAtmData();

    void startAtmActivity(Serializable atmData);

    void startAdminActivity(Serializable atmData);

    void displayEmptyUsernameError();

    void displayEmptyNIPError();

    void displayInvalidLoginError(final String REMAINING_ATTEMPT);

    void displayLoginAttemptsError(final String LOGIN_LOCK_WAIT_TIME_REMAINING);
}
