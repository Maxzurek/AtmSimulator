package com.example.atmsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.atmsimulator.adapters.IListLayoutAdapter;
import com.example.atmsimulator.adapters.ListLayoutAdapter;

import java.util.ArrayList;

public class AdminListActivity extends AppCompatActivity
{
    /************************************************************************/
    /* Class attributes                                                     */
    /************************************************************************/
    private final String LIST_DATA_KEY = "listDataKey";
    private final String LIST_TITLE_KEY = "listTitleKey";

    /************************************************************************/
    /* Overridden Methods                                                   */
    /************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);

        ArrayList<IListLayoutAdapter> listLayoutData = (ArrayList<IListLayoutAdapter>) getIntent().getSerializableExtra(LIST_DATA_KEY);
        ListLayoutAdapter listLayoutAdapter = new ListLayoutAdapter(this, R.layout.list_layout, listLayoutData);

        final ListView listViewAdmin = findViewById(R.id.listViewAdmin);
        final TextView textViewTitle = findViewById(R.id.textViewAdminListTitle);
        final String listTitle = getIntent().getStringExtra(LIST_TITLE_KEY);

        textViewTitle.setText(listTitle);
        listViewAdmin.setAdapter(listLayoutAdapter);
    }
}