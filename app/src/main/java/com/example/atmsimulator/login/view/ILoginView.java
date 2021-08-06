package com.example.atmsimulator.login.view;

import java.io.Serializable;

public interface ILoginView
{
    Serializable getAtmData();

    void startAtmActivity(Serializable atmData);

    void startAdminActivity(Serializable atmData);

    void displayErrorMessage(String errorMessage);

    void hideErrorMessage();
}
