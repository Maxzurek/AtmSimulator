package com.example.atmsimulator.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.atmsimulator.R;
import com.example.atmsimulator.models.account.Account;
import com.example.atmsimulator.models.users.User;

import java.util.ArrayList;

public class ListLayoutAdapter extends ArrayAdapter<IListLayoutAdapter>
{
    private ArrayList<IListLayoutAdapter> listLayoutData;
    private Context context;
    private int viewRes;
    private Resources res;

    public ListLayoutAdapter(@NonNull Context context, int resource, @NonNull ArrayList<IListLayoutAdapter> listLayoutData)
    {
        super(context, resource, listLayoutData);
        this.listLayoutData = listLayoutData;
        this.context = context;
        this.viewRes = resource;
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

        final IListLayoutAdapter data = listLayoutData.get(position);

        if (data != null)
        {
            final ImageView imageViewIcon = view.findViewById(R.id.imageViewListIcon);

            if(data instanceof User)
            {
                imageViewIcon.setImageResource(R.drawable.list_view_user_icon);
            }
            else if(data instanceof Account)
            {
                imageViewIcon.setImageResource(R.drawable.list_view_account_icon);
            }

            final TextView item1 = view.findViewById(R.id.textViewListItem1);
            item1.setText(data.getItem1());

            final TextView item2= view.findViewById(R.id.textViewListItem2);
            item2.setText(data.getItem2());
        }

        return view;
    }

    @Override
    public int getCount()
    {
        return listLayoutData.size();
    }
}
