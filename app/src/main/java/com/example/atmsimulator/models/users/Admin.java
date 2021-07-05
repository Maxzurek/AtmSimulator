package com.example.atmsimulator.models.users;

public class Admin extends User
{
    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public Admin()
    {
        super();
    }

    public Admin(String lastName, String firstName, String userName, String accountNIP)
    {
        super(lastName, firstName, userName, accountNIP);
    }

    public Admin(Client other)
    {
        super(other);
    }

    /************************************************************************/
    /* Overridden Methods                                                   */
    /************************************************************************/
    @Override
    public String toString()
    {
        return "Admin" + super.toString();
    }
}
