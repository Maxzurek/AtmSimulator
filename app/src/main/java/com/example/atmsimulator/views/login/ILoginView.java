package com.example.atmsimulator.views.login;

import com.example.atmsimulator.presenters.login.LoginActivityPresenter;

public interface ILoginView
{
    void startAtmActivity();

    void displayEmptyUsernameError();

    void displayEmptyNIPError();

    void displayInvalidLoginError(final String REMAINING_ATTEMPT);

    void displayLoginAttemptsError(final String LOGIN_LOCK_WAIT_TIME_REMAINING);
}
