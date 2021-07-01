package com.example.atmsimulator.presenters.login;

import com.example.atmsimulator.models.Client;
import com.example.atmsimulator.views.login.ILoginView;

import java.util.ArrayList;

public class LoginActivityPresenter
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private ILoginView view;
    private ArrayList<Client> clients;

    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public LoginActivityPresenter(ILoginView view)
    {
        this.view = view;

        clients = new ArrayList<Client>();
        clients.add(new Client());
    }

    /************************************************************************/
    /* Public Methods                                                       */
    /************************************************************************/
    public boolean validateUser(String userName, int nip)
    {
        boolean isValidUser = false;

        return isValidUser;
    }

    /************************************************************************/
    /* Private Methods                                                      */
    /************************************************************************/
    private boolean findUserName(String userName)
    {
        //TODO iterate through the arraylist of clients to find a username, return true if user is found
       return true;
    }
}
