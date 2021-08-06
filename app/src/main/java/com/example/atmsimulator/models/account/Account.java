package com.example.atmsimulator.models.account;

import android.content.Context;

import com.example.atmsimulator.R;
import com.example.atmsimulator.adapters.IListLayoutAdapter;


import java.io.Serializable;
import java.util.Objects;

public abstract class Account implements Comparable<Account>, Serializable, IListLayoutAdapter
    {
        /************************************************************************/
        /* Class attributes                                                     */
        /************************************************************************/
        private String accountNumber;
        private String accountAmount;


        /************************************************************************/
        /* Constructor(s)                                                       */
        /************************************************************************/
    public Account()
        {
            accountNumber = "";
            accountAmount = "";

        }

    public Account(String accountNumber, String accountAmount)
        {
            this.setAccountNumber(accountNumber);
            this.setAccountAmount(accountAmount);

        }

    public Account(Account other)
        {
            this.setAccountNumber(other.getAccountNumber());
            this.setAccountAmount(other.getAccountAmount());

        }

        /************************************************************************/
        /* Getters/Setters                                                      */
        /************************************************************************/
        public String getAccountNumber()
        {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber)
        {
            this.accountNumber = accountNumber;
        }

        public String getAccountAmount()
        {
            return accountAmount;
        }

        public void setAccountAmount(String accountAmount)
        {
            this.accountAmount = accountAmount;
        }

        /************************************************************************/
        /* Interface Implementation                                             */
        /************************************************************************/
        //Comparable
        @Override
        public int getIconResID(){return R.drawable.list_view_account_icon;};

        @Override
        public String getItem1(Context context){return context.getString(R.string.list_layout_item1_admin_prefix)+" "+getAccountNumber();}

        @Override
        public String getItem2(Context context){return context.getString(R.string.list_layout_item2_admin_prefix)+" "+getAccountAmount();}
        @Override

        //Comparable
        public int compareTo(Account other)
        {
            String thisAccountNumber = accountNumber;
            String otherAccountNumber = other.accountNumber;

            return thisAccountNumber.compareToIgnoreCase(otherAccountNumber);
        }

        /************************************************************************/
        /* Overridden Methods                                                   */
        /************************************************************************/
        @Override
        public String toString()
        {
            return "{" +
                    "accountNumber='" + getAccountNumber() + '\'' +
                    ", accountAmount='" + getAccountAmount() +
                    '}';
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }

            Account account = (Account) o;

            return getAccountNumber() == account.getAccountNumber() &&
                    Objects.equals(getAccountAmount(), account.getAccountAmount());
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(getAccountNumber(), getAccountAmount());
        }


        /************************************************************************/
        /* Public Methods                                                       */
        /************************************************************************/

        public float withdraw(float Amount){

            float newAccountAmount;
            newAccountAmount = Float.parseFloat(accountAmount) - Amount;

            return newAccountAmount;

        }

        public float deposit(float Amount){

            float newAccountAmount;
            newAccountAmount = Float.parseFloat(accountAmount) + Amount;

            return newAccountAmount;

        }

    }


