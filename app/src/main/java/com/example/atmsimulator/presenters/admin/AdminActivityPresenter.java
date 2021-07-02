package com.example.atmsimulator.presenters.admin;

import com.example.atmsimulator.models.AtmData;
import com.example.atmsimulator.views.admin.IAdminView;

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
    /* Public Methods                                                       */
    /************************************************************************/
    //TODO

    /************************************************************************/
    /* Private Methods                                                      */
    /************************************************************************/
    //TODO
}
