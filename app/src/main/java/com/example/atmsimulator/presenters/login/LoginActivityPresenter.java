package com.example.atmsimulator.presenters.login;

import com.example.atmsimulator.models.AtmData;
import com.example.atmsimulator.models.users.Admin;
import com.example.atmsimulator.models.users.Client;
import com.example.atmsimulator.models.users.User;
import com.example.atmsimulator.views.login.ILoginView;

import java.util.ArrayList;

public class LoginActivityPresenter
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private ILoginView view;
    private AtmData atmData;
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
        atmData = (AtmData) view.getAtmData();
    }

    /************************************************************************/
    /* Public Methods                                                       */
    /************************************************************************/
    public void attemptLogin(String userName, String nip)
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
        else if(nip.isEmpty())
        {
            view.displayEmptyNIPError();
            return;
        }

        if(atmData.validateUser(userName, nip))
        {
            User user = atmData.getUser(userName, nip);

            if(user != null)
            {
                if(user instanceof Admin)
                {
                    view.startAdminActivity(atmData);
                }
                else
                {
                    view.startAtmActivity(atmData.getUserAccounts(nip));
                }
            }
            else
            {
                //TODO interface method + res sting
                view.setTextViewErrorText("There was an error while fetching data");
            }
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
}
