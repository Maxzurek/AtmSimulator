package com.example.atmsimulator;

public enum EViewKey
{
    ATM_DATA("atmData"),
    USER_ACCOUNTS("userAccounts"),
    ADMIN_LIST_TITLE("adminListTitle"),
    ADMIN_LIST_DATA("adminListData");

    public final String label;

    private EViewKey(String label)
    {
        this.label = label;
    }
}