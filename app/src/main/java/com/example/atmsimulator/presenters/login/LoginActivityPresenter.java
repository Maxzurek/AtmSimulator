package com.example.atmsimulator.presenters.login;

import com.example.atmsimulator.models.Client;
import com.example.atmsimulator.views.login.ILoginView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivityPresenter
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private ILoginView view;

    private int invalidLoginAttempt;
    private final int LOGIN_LOCK_WAIT_TIME = 10000;
    private long loginLockStartTime;

    private ArrayList<Client> clients;

    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public LoginActivityPresenter(ILoginView view)
    {
        this.view = view;
        invalidLoginAttempt = 3;
        clients = new ArrayList<Client>();
        clients.add(new Client("Zurek", "Maxime", "MaximeZurek", "1234"));
    }

    /************************************************************************/
    /* Public Methods                                                       */
    /************************************************************************/
    public void attemptLogin(String userName, String NIP)
    {
        long currentSystemTime = System.currentTimeMillis();

        if( isLoginLocked())
        {
            view.displayLoginAttemptsError(getLoginLockTimeRemaining());
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

        if(validateUser(userName, NIP))
        {
            view.startAtmActivity();
        }
        else
        {
            invalidLoginAttempt--;

            if(invalidLoginAttempt == 0)
            {
                lockLogin();
                view.displayLoginAttemptsError(getLoginLockTimeRemaining());
            }
            else
            {
                view.displayInvalidLoginError(String.valueOf(invalidLoginAttempt));
            }
        }
    }

    /************************************************************************/
    /* Private Methods                                                      */
    /************************************************************************/
    private boolean validateUser(String userName, String NIP)
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

    private boolean isLoginLocked()
    {
        if( (System.currentTimeMillis() - loginLockStartTime) < LOGIN_LOCK_WAIT_TIME )
        {
            return true;
        }

        return false;
    }

    private String getLoginLockTimeRemaining()
    {
        //We want the remaining lock time in seconds
        long remainingTime = ( LOGIN_LOCK_WAIT_TIME - (System.currentTimeMillis() - loginLockStartTime) ) /1000;

        return String.valueOf(remainingTime);
    }

    private void lockLogin()
    {
        loginLockStartTime = System.currentTimeMillis();
    }

    private void unlockLogin()
    {
        invalidLoginAttempt = 0;
    }
}
