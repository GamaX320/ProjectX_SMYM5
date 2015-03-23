package com.example.bryanty.projectx_smym5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bryanty.projectx_smym5.domain.Account;

import java.util.List;

/**
 * Created by BRYANTY on 28/02/2015.
 */
public class ExpenseFragment extends Fragment{
    View rootView;
    int accountID;
    DBHandler dbHandler;

//    SendMessage SM;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_expense,container,false);

        //load all account from database
        dbHandler= new DBHandler(getActivity(),null,null,1);
        final List<Account> account = dbHandler.getAllAccount();

        //initial the spinner
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner_expense_account_top);
        ExpenseAccountAdapter expenseAccountAdapter= new ExpenseAccountAdapter(getActivity().getApplicationContext(),account);
        spinner.setAdapter(expenseAccountAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void  onItemSelected(AdapterView<?> parent,View view,int position,long id){
                //Toast.makeText(getActivity(), "account selected > " + account.get(position).get_accID(), Toast.LENGTH_SHORT).show();
//              SM.sendData("Finally");

                accountID= account.get(position).get_accID();

                Fragment objFragment=new ExpenseDownFragment();
                //pass value to another fragment
                Bundle bundle = new Bundle();
                bundle.putInt("accountID",account.get(position).get_accID());
                objFragment.setArguments(bundle);
                //ready to replace next fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_expense,objFragment)
                        .addToBackStack(null)
                        .commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //add button listener
        ImageButton btnAddExpense= (ImageButton)rootView.findViewById(R.id.imageButton_add_expense);
        btnAddExpense.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment objFragment=new CreateExpenseFragment();
                //pass value to another fragment
                Bundle bundle = new Bundle();
                bundle.putInt("accountID",accountID);
                objFragment.setArguments(bundle);
                //ready to replace next fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container,objFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }

}
