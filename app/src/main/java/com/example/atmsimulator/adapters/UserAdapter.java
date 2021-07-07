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
import com.example.atmsimulator.models.users.User;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User>
{
    private ArrayList<User> users;
    private Context context;
    private int viewRes;
    private Resources res;

    public UserAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> users)
    {
        super(context, resource, users);
        this.users = users;
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

        final User user = users.get(position);

        if (user != null)
        {
            final TextView item1 = view.findViewById(R.id.textViewListItem1);
            String firstName = user.getFirstName();
            item1.setText(firstName);

            final TextView item2= view.findViewById(R.id.textViewListItem2);
            String lastName = user.getLastName();
            item2.setText(lastName);
        }

        return view;
    }

    @Override
    public int getCount()
    {
        return users.size();
    }
}
