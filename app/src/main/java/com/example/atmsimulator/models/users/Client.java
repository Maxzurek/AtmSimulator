package com.example.atmsimulator.models.users;

import java.util.Objects;

public class Client extends User
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
    /* Overridden Methods                                                   */
    /************************************************************************/
    @Override
    public String toString()
    {
        return "Client" + super.toString();
    }
}
