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

    public SavingAccount(String accountNumber, String accountAmount)
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




    /************************************************************************/
    /* Public Methods                                                       */
    /************************************************************************/


    /*public void withdrawSavingAccount(float Amount){



    }
    public void depositSavingAccount(float Amount){



    }*/
}


