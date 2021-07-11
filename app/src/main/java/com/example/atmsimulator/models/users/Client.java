package com.example.atmsimulator.models.users;

import android.content.Context;

import com.example.atmsimulator.R;

import java.io.Serializable;

public class Client extends User implements Serializable
{
    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public Client()
    {
        super();
    }

    public Client(String lastName, String firstName, String userName, String accountNIP)
    {
        super(lastName, firstName, userName, accountNIP);
    }

    public Client(Client other)
    {
        super(other);
    }

    /************************************************************************/
    /* Interface Implementation                                             */
    /************************************************************************/
    @Override
    public int getIconResID(){return R.drawable.list_view_user_icon;};

    @Override
    public String getItem1(Context context){return context.getString(R.string.list_layout_item1_client_prefix)+" "+getLastName();}

    @Override
    public String getItem2(Context context){return context.getString(R.string.list_layout_item2_client_prefix)+" "+getFirstName();}

    /************************************************************************/
    /* Overridden Methods                                                   */
    /************************************************************************/
    @Override
    public String toString()
    {
        return "Client" + super.toString();
    }
}
