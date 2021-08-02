package com.example.atmsimulator.models.account;

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
        @Override
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
    }


