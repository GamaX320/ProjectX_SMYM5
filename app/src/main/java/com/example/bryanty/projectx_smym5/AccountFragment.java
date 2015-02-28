package com.example.bryanty.projectx_smym5;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by BRYANTY on 28/02/2015.
 */
public class AccountFragment extends Fragment {
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_account, container, false);

        //listview account
        String[] accountName={"Steve","Cindy","Mark","Justin","Robert"};

        //custom adapter
        ListAdapter accountAdapter=new AccountRowAdapter(getActivity().getApplicationContext(), accountName);
        ListView accountListView=(ListView)rootView.findViewById(R.id.listView_account);
        accountListView.setAdapter(accountAdapter);

        //listview listener
        accountListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                // TODO Auto-generated method stub
                // Intent intent = new Intent(getActivity(), nextactivity.class);
                // startActivity(intent);
                Toast.makeText(getActivity(), "Click ListItem Number " + position, Toast.LENGTH_LONG).show();
            }
        });

        //add new account button listener
        ImageButton btnAdd=(ImageButton) rootView.findViewById(R.id.imageButton_add);
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               // Intent intent=new Intent(getActivity(),CreateAccountActivity.class);
               // startActivity(intent);
            }
        });

        return rootView;
    }
}
