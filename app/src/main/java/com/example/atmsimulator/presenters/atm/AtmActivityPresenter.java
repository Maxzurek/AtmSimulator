package com.example.atmsimulator.presenters.atm;

import com.example.atmsimulator.models.AtmData;
import com.example.atmsimulator.views.atm.IAtmView;

public class AtmActivityPresenter
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private IAtmView view;
    private AtmData atmData;
    //TODO
    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public AtmActivityPresenter(IAtmView view)
    {
        this.view = view;
        atmData = (AtmData) view.getAtmData();
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
