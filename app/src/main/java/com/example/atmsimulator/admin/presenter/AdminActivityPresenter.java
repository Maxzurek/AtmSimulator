package com.example.atmsimulator.admin.presenter;

import com.example.atmsimulator.models.AtmData;
import com.example.atmsimulator.admin.view.IAdminView;

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

    /************************************************************************/
    /* Private Methods                                                      */
    /************************************************************************/
    //TODO
}
