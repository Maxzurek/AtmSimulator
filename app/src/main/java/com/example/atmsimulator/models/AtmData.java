package com.example.atmsimulator.models;

import com.example.atmsimulator.models.account.Account;
import com.example.atmsimulator.models.account.CheckAccount;
import com.example.atmsimulator.models.account.SavingAccount;
import com.example.atmsimulator.models.users.Admin;
import com.example.atmsimulator.models.users.Client;
import com.example.atmsimulator.models.users.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

        initializeData(users, usersAccounts);
    }

    /************************************************************************/
    /* Public Class methods                                                 */
    /************************************************************************/
    public ArrayList<User> getUsers(){return users;}

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

    public boolean validateUser(final String userName, final String nip)
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

    public ArrayList<Client> getClients()
    {
        ArrayList<Client> clients = new ArrayList<>();

        for(User user : users)
        {
            if(user instanceof Client)
            {
                clients.add((Client)user);
            }
        }

        return clients;
    }

    public ArrayList<Admin> getAdmins()
    {
        ArrayList<Admin> admins = new ArrayList<>();

        for(User user : users)
        {
            if(user instanceof Admin)
            {
                admins.add((Admin)user);
            }
        }

        return admins;
    }

    public ArrayList<Account> getAccounts()
    {
        ArrayList<Account> accounts = new ArrayList<>();

        for(Map.Entry<String, ArrayList<Account>> entry : usersAccounts.entrySet())
        {
            accounts.addAll(entry.getValue());
        }

        return accounts;
    }

    public ArrayList<Account> getUserAccounts(final String userNip)
    {
        return usersAccounts.get(userNip);
    }

    public boolean updateUserAccounts(final ArrayList<Account> userAccounts)
    {
        if(userAccounts != null)
        {
            //TODO Get account nip and replace data in the usersAccounts hashmap with the specified key
            //userAccounts.get(0);

            return true;
        }

        return false;
    }

    /************************************************************************/
    /* Private Class methods                                                */
    /************************************************************************/
    private void initializeData(ArrayList<User> users,HashMap<String, ArrayList<Account>> usersAccounts)
    {
        users.add(new Admin("Doe", "John", "Admin", "D001"));

        users.add(new Client("Zurek", "Maxime", "MaximeZurek", "1234"));
        ArrayList<Account> zurekMaximeAccounts = new ArrayList<>();
        zurekMaximeAccounts.add(new CheckAccount());
        zurekMaximeAccounts.add(new SavingAccount());

        Collections.sort(users);
        //TODO Sort zurekMaximeAccounts, implement compare to in Account class
        usersAccounts.put("1234", zurekMaximeAccounts);
    }
}
