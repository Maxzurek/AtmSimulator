package com.example.atmsimulator.models.users;

import com.example.atmsimulator.R;
import com.example.atmsimulator.adapters.IListLayoutAdapter;

import java.io.Serializable;
import java.util.Objects;

public abstract class User implements Comparable<User>, Serializable, IListLayoutAdapter
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private String lastName;
    private String firstName;
    private String userName;
    private String accountNIP;

    /************************************************************************/
    /* Constructor(s)                                                       */
    /************************************************************************/
    public User()
    {
        lastName = "";
        firstName = "";
        userName = "Guest";
        accountNIP = "";
    }

    public User(String lastName, String firstName, String userName, String accountNIP)
    {
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setUserName(userName);
        this.setAccountNIP(accountNIP);
    }

    public User(Client other)
    {
        this.setLastName(other.getLastName());
        this.setFirstName(other.getFirstName());
        this.setUserName(other.getUserName());
        this.setAccountNIP(other.getAccountNIP());
    }

    /************************************************************************/
    /* Getters/Setters                                                      */
    /************************************************************************/
    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getAccountNIP()
    {
        return accountNIP;
    }

    public void setAccountNIP(String accountNIP)
    {
        this.accountNIP = accountNIP;
    }

    /************************************************************************/
    /* Interface Implementation                                             */
    /************************************************************************/
    @Override
    public int compareTo(User other)
    {
        String thisUserLastName = lastName;
        String otherUserLastName = other.lastName;

        return thisUserLastName.compareToIgnoreCase(otherUserLastName);
    }

    /************************************************************************/
    /* Overridden Methods                                                   */
    /************************************************************************/
    @Override
    public String toString()
    {
        return "{" +
                "lastName='" + getLastName() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", userName='" + getUserName() + '\'' +
                ", accountNIP=" + getAccountNIP() +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Client other = (Client) o;

        if(!accountNIP.equalsIgnoreCase(other.getAccountNIP()))
        {
            return false;
        }
        if(!lastName.equalsIgnoreCase(other.getLastName()))
        {
            return false;
        }
        if(!userName.equalsIgnoreCase(other.getUserName()))
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getLastName(), getFirstName(), getUserName(), getAccountNIP());
    }
}
