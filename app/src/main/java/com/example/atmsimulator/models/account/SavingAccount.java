package com.example.atmsimulator.models.account;

import android.content.Context;

import com.example.atmsimulator.R;

import java.io.Serializable;

public class SavingAccount extends Account implements Serializable
{
    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public SavingAccount()
    {
        super();
    }

    public SavingAccount(int accountNumber, double accountAmount)
    {
        super(accountNumber, accountAmount);
    }

    public SavingAccount(Account other)
    {
        super(other);
    }

    /************************************************************************/
    /* Overridden Methods                                                   */
    /************************************************************************/
    @Override
    public String toString()
    {
        return "SavingAccount" + super.toString();
    }


}


