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
    private HashMap<User, ArrayList<Account>> userAccounts;

    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public AtmData()
    {
        users = new ArrayList<>();
        userAccounts = new HashMap<>();

        initializeData(users, userAccounts);
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

        for(Map.Entry<User, ArrayList<Account>> entry : userAccounts.entrySet())
        {
            accounts.addAll(entry.getValue());
        }

        return accounts;
    }

    public ArrayList<CheckAccount> getCheckAccounts()
    {
        ArrayList<CheckAccount> checkAccounts = new ArrayList<>();

        for(Map.Entry<User, ArrayList<Account>> entry : userAccounts.entrySet())
        {
            for(Account account : entry.getValue())
            {
                if(account instanceof CheckAccount)
                {
                    checkAccounts.add((CheckAccount) account);
                }
            }
        }

        return checkAccounts;
    }

    public ArrayList<SavingAccount> getSavingAccounts()
    {
        ArrayList<SavingAccount> savingAccounts = new ArrayList<>();

        for(Map.Entry<User, ArrayList<Account>> entry : userAccounts.entrySet())
        {
            for(Account account : entry.getValue())
            {
                if(account instanceof SavingAccount)
                {
                    savingAccounts.add((SavingAccount) account);
                }
            }
        }

        return savingAccounts;
    }

    public ArrayList<Account> getUserAccounts(final User _user)
    {
        for(Map.Entry<User, ArrayList<Account>> entry : userAccounts.entrySet())
        {
            User user = entry.getKey();

            if(user.equals(_user))
            {
                return entry.getValue();
            }
        }

        return null;
    }

    public boolean updateUserAccounts(final User user, final ArrayList<Account> _userAccounts)
    {
        if(_userAccounts != null)
        {
            for(Map.Entry<User, ArrayList<Account>> entry : userAccounts.entrySet())
            {
                User keyUser = entry.getKey();

                if(keyUser.equals(user))
                {
                   entry.setValue(_userAccounts);
                }
            }

            return true;
        }

        return false;
    }

    /************************************************************************/
    /* Private Class methods                                                */
    /************************************************************************/
    private void initializeData(ArrayList<User> users, HashMap<User, ArrayList<Account>> userAccountsData)
    {
        Admin admin1 = new Admin("Doe", "John", "Admin", "D001");
        users.add(admin1);

        Client maximeZurek = new Client("Zurek", "Maxime", "MaximeZurek", "1234");
        users.add(maximeZurek);
        ArrayList<Account> maximeZurekAccounts = new ArrayList<>();
        maximeZurekAccounts.add(new CheckAccount(1001, 10500.95));
        maximeZurekAccounts.add(new SavingAccount(2001, 1000.50));
        userAccountsData.put(maximeZurek, maximeZurekAccounts);

        Client jeremyDubeau = new Client("Dubeau", "Jeremy", "JeremyDubeau", "1234");
        users.add(jeremyDubeau);
        ArrayList<Account> jeremyDubeauAccounts = new ArrayList<>();
        jeremyDubeauAccounts.add(new CheckAccount(1002, 20500.95));
        jeremyDubeauAccounts.add(new SavingAccount(2002, 2000.50));
        userAccountsData.put(jeremyDubeau, jeremyDubeauAccounts);

        Collections.sort(users);
    }
}
