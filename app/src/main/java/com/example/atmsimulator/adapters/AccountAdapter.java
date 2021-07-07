package com.example.atmsimulator.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.atmsimulator.R;
import com.example.atmsimulator.models.account.Account;

import java.util.ArrayList;

public class AccountAdapter extends ArrayAdapter<Account>
{
    private ArrayList<Account> accounts;
    private Context context;
    private int viewRes;
    private Resources res;

    public AccountAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Account> accounts)
    {
        super(context, resource, accounts);
        this.accounts = accounts;
        this.context = context;
        this.viewRes = viewRes;
        this.res = context.getResources();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view = convertView;

        if (view == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
        }

        final Account account = accounts.get(position);

        if (account != null)
        {
            final TextView item1 = view.findViewById(R.id.textViewListItem1);
            final TextView item2= view.findViewById(R.id.textViewListItem2);

            //TODO
        }

        return view;
    }

    @Override
    public int getCount()
    {
        return accounts.size();
    }
}
