package com.example.atmsimulator.models.account;

import android.content.Context;

import com.example.atmsimulator.R;

import java.io.Serializable;

public class CheckAccount extends Account implements Serializable
{
    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public CheckAccount()
        {
            super();
        }

    public CheckAccount(String accountNumber, String accountAmount)
    {
        super(accountNumber, accountAmount);
    }

    public CheckAccount(Account other)
    {
        super(other);
    }

    /************************************************************************/
    /* Overridden Methods                                                   */
    /************************************************************************/
    @Override
    public String toString()
        {
            return "CheckAccount" + super.toString();
        }
}



