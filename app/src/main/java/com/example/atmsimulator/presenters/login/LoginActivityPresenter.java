package com.example.atmsimulator.presenters.login;

import com.example.atmsimulator.R;
import com.example.atmsimulator.models.Client;
import com.example.atmsimulator.views.login.ILoginView;

import java.util.ArrayList;

public class LoginActivityPresenter
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private ILoginView view;
    private int invalidLoginAttempt;
    private ArrayList<Client> clients;

    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public LoginActivityPresenter(ILoginView view)
    {
        this.view = view;
        invalidLoginAttempt = 0;
        clients = new ArrayList<Client>();
        clients.add(new Client("Zurek", "Maxime", "MaxZurek", "1234"));
    }

    /************************************************************************/
    /* Public Methods                                                       */
    /************************************************************************/
    public void loginAttempt(String userName, String NIP)
    {
        if(invalidLoginAttempt == 3)
        {
            view.displayLoginAttemptsError();
            return;
        }

        if(userName.isEmpty())
        {
            view.displayEmptyUsernameError();
            return;
        }
        else if(NIP.isEmpty())
        {
            view.displayEmptyNIPError();
            return;
        }

        if(isUserValid(userName, NIP))
        {
            view.startAtmActivity();
        }
        else
        {
            invalidLoginAttempt++;
            view.displayInvalidLoginError();
        }
    }

    /************************************************************************/
    /* Private Methods                                                      */
    /************************************************************************/
    private boolean isUserValid(String userName, String NIP)
    {
        for(Client client : clients)
        {
            if(client.getUserName().equals(userName) && client.getAccountNIP().equals(NIP))
            {
                return true;
            }
        }

        return false;
    }
}
