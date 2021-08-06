package com.example.atmsimulator.login.presenter;

import android.content.Context;

import com.example.atmsimulator.R;
import com.example.atmsimulator.models.AtmData;
import com.example.atmsimulator.models.account.Account;
import com.example.atmsimulator.models.users.Admin;
import com.example.atmsimulator.models.users.User;
import com.example.atmsimulator.login.view.ILoginView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivityPresenter
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private final int LOGIN_LOCK_WAIT_TIME = 10000;
    private final int MAX_LOGIN_ATTEMPT = 3;

    private ILoginView view;
    private AtmData atmData;
    private int invalidLoginAttempt;
    private long loginLockStartTime;

    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public LoginActivityPresenter(ILoginView view)
    {
        this.view = view;
        atmData = (AtmData) view.getAtmData();
    }

    /************************************************************************/
    /* Public Methods                                                       */
    /************************************************************************/
    public void attemptLogin(Context context, String userName, String nip)
    {
        String errorMessage;

        if(isLoginLocked())
        {
            errorMessage = context.getString(R.string.login_activity_textview_loginAttempts_error)
                    +" "+getLoginLockTimeRemaining()
                    +" "+context.getString(R.string.login_activity_textview_loginAttempts_errorEnding);

            view.displayErrorMessage(errorMessage);

            return;
        }

        if(userName.isEmpty())
        {
            errorMessage = context.getString(R.string.login_activity_textview_emptyUsername_error);

            view.displayErrorMessage(errorMessage);

            return;
        }
        else if(nip.isEmpty())
        {
            errorMessage = context.getString(R.string.login_activity_textview_emptyNIP_error);

            view.displayErrorMessage(errorMessage);

            return;
        }

        if(atmData.validateUser(userName, nip))
        {
            User user = atmData.getUser(userName, nip);

            if(user != null)
            {
                view.hideErrorMessage();

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
                view.displayErrorMessage(context.getString(R.string.login_activity_textview_fetchingData_error));
            }
        }
        else
        {
            invalidLoginAttempt++;

            if(invalidLoginAttempt == MAX_LOGIN_ATTEMPT)
            {
                lockLogin(context);

                errorMessage = context.getString(R.string.login_activity_textview_loginAttempts_error)
                        +" "+getLoginLockTimeRemaining()
                        +" "+context.getString(R.string.login_activity_textview_loginAttempts_errorEnding);

            }
            else
            {
                errorMessage = context.getString(R.string.login_activity_textview_invalidLogin_error)
                        +"\n"+getRemainLoginAttempt()
                        +" "+context.getString(R.string.login_activity_textview_invalidLogin_errorEnding);

            }

            view.displayErrorMessage(errorMessage);
        }
    }

    public void updateUserAccounts(Serializable userAccounts)
    {
        ArrayList<Account> accounts = (ArrayList<Account>) userAccounts;

        if(accounts != null)
        {
            atmData.updateUserAccounts(accounts);
        }
    }

    public void updateAtmData(Serializable atmData)
    {
        this.atmData = (AtmData)atmData;
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

    private String getRemainLoginAttempt()
    {
        return String.valueOf(MAX_LOGIN_ATTEMPT - invalidLoginAttempt);
    }

    private void lockLogin(Context context)
    {
        loginLockStartTime = System.currentTimeMillis();

        Timer unlockTimer = new Timer();
        TimerTask unlockTask = new TimerTask()
        {
            @Override
            public void run()
            {
               invalidLoginAttempt = 0;
            }
        };
        unlockTimer.schedule(unlockTask, LOGIN_LOCK_WAIT_TIME);
    }
}
