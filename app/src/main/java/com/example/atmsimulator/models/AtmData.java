package com.example.atmsimulator.models;

import com.example.atmsimulator.models.account.Account;
import com.example.atmsimulator.models.account.CheckAccount;
import com.example.atmsimulator.models.account.SavingAccount;
import com.example.atmsimulator.models.users.Admin;
import com.example.atmsimulator.models.users.Client;
import com.example.atmsimulator.models.users.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class AtmData implements Serializable
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private ArrayList<User> users;
    private HashMap<String, ArrayList<Account>> usersAccounts;

    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public AtmData()
    {
        users = new ArrayList<>();
        usersAccounts = new HashMap<>();

        users.add(new Admin("Doe", "John", "Admin", "D001"));

        users.add(new Client("Zurek", "Maxime", "MaximeZurek", "1234"));
        ArrayList<Account> zurekMaximeAccounts = new ArrayList<>();
        zurekMaximeAccounts.add(new CheckAccount());
        zurekMaximeAccounts.add(new SavingAccount());
        usersAccounts.put("1234", zurekMaximeAccounts);
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
        return usersAccounts.get(userNip);
    }
}
