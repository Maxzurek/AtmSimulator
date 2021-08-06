package com.example.atmsimulator.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.atmsimulator.EViewKey;
import com.example.atmsimulator.R;
import com.example.atmsimulator.adapters.IListLayoutAdapter;
import com.example.atmsimulator.adapters.ListLayoutAdapter;

import java.util.ArrayList;

public class AdminListView extends AppCompatActivity
{
    /************************************************************************/
    /* Overridden Methods                                                   */
    /************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);

        final TextView textViewTitle = findViewById(R.id.textViewAdminListTitle);
        final String listTitle = getIntent().getStringExtra(EViewKey.ADMIN_LIST_TITLE.label);
        textViewTitle.setText(listTitle);

        ArrayList<IListLayoutAdapter> listLayoutData = (ArrayList<IListLayoutAdapter>) getIntent().getSerializableExtra(EViewKey.ADMIN_LIST_DATA.label);
        ListLayoutAdapter listLayoutAdapter = new ListLayoutAdapter(this, R.layout.list_layout, listLayoutData);

        final ListView listViewAdmin = findViewById(R.id.listViewAdmin);
        listViewAdmin.setAdapter(listLayoutAdapter);
    }
}