package com.example.atmsimulator.presenters.admin;

import com.example.atmsimulator.models.AtmData;
import com.example.atmsimulator.views.admin.IAdminView;

import java.io.Serializable;

public class AdminActivityPresenter
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private IAdminView view;
    private AtmData atmData;

    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public AdminActivityPresenter(IAdminView view)
    {
        this.view = view;
        atmData = (AtmData)view.getAtmData();
    }

    /************************************************************************/
    /* Getters/Setters                                                      */
    /************************************************************************/
    public Serializable getAtmData(){return atmData;}

    /************************************************************************/
    /* Public Methods                                                       */
    /************************************************************************/
    public void handleOnClickPayInterest()
    {
    }

    public void handleOnClickCheckAccountList()
    {

    }

    public void handleOnClickSavingAccountList()
    {

    }

    public void handleOnClickClientList()
    {
        view.startAdminListActivity(atmData.getClients());
    }

    /************************************************************************/
    /* Private Methods                                                      */
    /************************************************************************/
    //TODO
}
