package com.example.bryanty.projectx_smym5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bryanty.projectx_smym5.domain.Account;

import java.util.List;

/**
 * Created by BRYANTY on 28/02/2015.
 */
public class AccountFragment extends Fragment {
    View rootView;
    DBHandler dbHandler;

    Integer tempPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_account, container, false);

        //load all account from database
        dbHandler= new DBHandler(getActivity(),null,null,1);
       final List<Account> account = dbHandler.getAllAccount();

        //listview account
        String[] accountName={"Steve","Cindy","Mark","Justin","Robert"};

        //custom adapter
        ListAdapter accountAdapter=new AccountRowAdapter(getActivity().getApplicationContext(), account);
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

                Account myAccount= account.get(position);
                Log.v("MyActivity", "acc=" +myAccount.get_accID()); //print message to console

                /////
                Fragment objFragment=new UpdateAccountFragment();
                //pass value to another fragment
                Bundle bundle = new Bundle();
                bundle.putInt("accountID",account.get(position).get_accID());
                objFragment.setArguments(bundle);
                //ready to replace next fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container,objFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        //add new account button listener
        ImageButton btnAdd=(ImageButton) rootView.findViewById(R.id.imageButton_add);
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//              Intent intent=new Intent(getActivity(),CreateAccount.class);
//              startActivity(intent);
                Fragment objFragment=new CreateAccountFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                .replace(R.id.container,objFragment)
                .addToBackStack(null)
                .commit();
            }
        });

      //long press list view for delete selected item
        accountListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Alert!!");
                alert.setMessage("Are you sure to delete record " + account.get(position).get_accName());
                tempPosition=position;

                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do your work here
                        //load all account from database
                        dbHandler= new DBHandler(getActivity(),null,null,1);
                        dbHandler.deleteAccount(account.get(tempPosition).get_accID());

                        //refresh list view
                        //load all account from database
                        dbHandler= new DBHandler(getActivity(),null,null,1);
                        final List<Account> account = dbHandler.getAllAccount();
                        //custom adapter
                        ListAdapter accountAdapter=new AccountRowAdapter(getActivity().getApplicationContext(), account);
                        ListView accountListView=(ListView)rootView.findViewById(R.id.listView_account);
                        accountListView.setAdapter(accountAdapter);

                        dialog.dismiss();

                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
                return false;
            }
        });

        return rootView;
    }
}
