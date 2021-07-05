package com.example.atmsimulator.models;

import com.example.atmsimulator.models.account.Account;
import com.example.atmsimulator.models.users.Admin;
import com.example.atmsimulator.models.users.Client;
import com.example.atmsimulator.models.users.User;

import java.io.Serializable;
import java.util.ArrayList;

public class AtmData implements Serializable
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public AtmData()
    {
        users = new ArrayList<User>();
        users.add(new Client("Zurek", "Maxime", "MaximeZurek", "1234"));
        users.add(new Admin("Doe", "John", "Admin", "D001"));
    }

    /************************************************************************/
    /* Public Class methods                                                 */
    /************************************************************************/
    public boolean validateUser(String userName, String nip)
    {
        for(User user : users)
        {
            if(user.getUserName().equals(userName) && user.getAccountNIP().equals(nip))
            {
                return true;
            }
        }

        return false;
    }

    public User getUser(String userName, String nip)
    {
        for(User user : users)
        {
            if(user.getUserName().equals(userName) && user.getAccountNIP().equals(nip))
            {
                return user;
            }
        }

        return null;
    }

    public ArrayList<Account> getUserAccounts(String userNip)
    {
        ArrayList<Account> userAccounts = new ArrayList<Account>();

        for(Account account : accounts)
        {
            //TODO if account match userNip, add account to the list;
        }

        if(userAccounts.size() == 0)
        {
            return null;
        }

        return userAccounts;
    }
}
