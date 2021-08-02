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
        /* Interface Implementation                                             */
        /************************************************************************/
        @Override
        public int getIconResID(){return R.drawable.list_view_user_icon;};

        @Override
        public String getItem1(Context context){return context.getString(R.string.list_layout_item1_admin_prefix)+" "+getAccountNumber();}

        @Override
        public String getItem2(Context context){return context.getString(R.string.list_layout_item2_admin_prefix)+" "+getAccountAmount();}

        /************************************************************************/
        /* Overridden Methods                                                   */
        /************************************************************************/
        @Override
        public String toString()
        {
            return "CheckAccount" + super.toString();
        }
}



