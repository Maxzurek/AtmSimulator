package com.example.atmsimulator.models.account;

import android.content.Context;

import com.example.atmsimulator.R;
import com.example.atmsimulator.adapters.IListLayoutAdapter;


import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Objects;

public abstract class Account implements Comparable<Account>, Serializable, IListLayoutAdapter
    {
        /************************************************************************/
        /* Class attributes                                                     */
        /************************************************************************/
        private int accountNumber;
        private double accountAmount;


        /************************************************************************/
        /* Constructor(s)                                                       */
        /************************************************************************/
        public Account()
        {
            accountNumber = 0;
            accountAmount = 0;

        }

        public Account(int accountNumber, double accountAmount)
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
        public int getAccountNumber()
        {
            return accountNumber;
        }

        public void setAccountNumber(int accountNumber)
        {
            this.accountNumber = accountNumber;
        }

        public double getAccountAmount()
        {
            return accountAmount;
        }

        public void setAccountAmount(double accountAmount)
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
        public String getItem1(Context context){
            return context.getString(R.string.list_layout_item1_account_prefix)+" "+getAccountNumber();}

        @Override
        public String getItem2(Context context)
        {
            DecimalFormat decimalFormat = new DecimalFormat("#.00$");

            String formattedAmount = decimalFormat.format(getAccountAmount());

            return context.getString(R.string.list_layout_item2_account_prefix)+" "+ formattedAmount;
        }
        @Override

        //Comparable
        public int compareTo(Account other)
        {
            if(accountAmount < other.getAccountAmount())
            {
                return -1;
            }
            if (accountAmount == other.getAccountAmount())
            {
                return 0;
            }
            return 1;
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


        public boolean withdraw(double amount)
        {
            if (amount > accountAmount)
            {
                return false;
            }
            if (amount > 1000)
            {
                return false;
            }
            if (amount % 10 != 0)
            {
                return false;
            }
            else
            {
                accountAmount = accountAmount - amount;

                return true;
            }
        }

        public boolean deposit(double Amount)
        {

            if(Amount < 0)
            {
                return false;
            }
            else
            {
                accountAmount = accountAmount + Amount;

                return true;
            }
        }


    }


