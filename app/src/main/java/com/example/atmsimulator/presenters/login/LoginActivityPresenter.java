package com.example.atmsimulator.presenters.login;

import com.example.atmsimulator.views.login.ILoginView;

public class LoginActivityPresenter
{
    private ILoginView view;

    public LoginActivityPresenter(ILoginView view)
    {
        this.view = view;
    }
}
