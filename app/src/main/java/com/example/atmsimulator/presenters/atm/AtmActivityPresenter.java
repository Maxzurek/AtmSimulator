package com.example.atmsimulator.presenters.atm;

import com.example.atmsimulator.models.AtmData;
import com.example.atmsimulator.models.account.Account;
import com.example.atmsimulator.views.atm.IAtmView;

import java.util.ArrayList;

public class AtmActivityPresenter
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private IAtmView view;
    private ArrayList<Account> userAccounts;
    //TODO
    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public AtmActivityPresenter(IAtmView view)
    {
        this.view = view;
        userAccounts = (ArrayList<Account>) view.getUserAccounts();
    }

    /************************************************************************/
    /* Public Methods                                                       */
    /************************************************************************/
    //TODO

    /************************************************************************/
    /* Private Methods                                                      */
    /************************************************************************/
    //TODO
}
