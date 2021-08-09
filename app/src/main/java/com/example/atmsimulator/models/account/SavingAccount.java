package com.example.atmsimulator.models.account;

import android.content.Context;

import com.example.atmsimulator.R;

import java.io.Serializable;

public class SavingAccount extends Account implements Serializable
{
    private final double INTEREST_RATE = 1.25;
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

    public void interestPayment()
    {
        setAccountAmount(getAccountAmount() * INTEREST_RATE);

    }


}


