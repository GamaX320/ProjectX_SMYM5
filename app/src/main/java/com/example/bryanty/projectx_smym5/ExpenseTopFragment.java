package com.example.bryanty.projectx_smym5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.bryanty.projectx_smym5.domain.Account;

import java.util.List;

/**
 * Created by BRYANTY on 14/03/2015.
 */
public class ExpenseTopFragment extends Fragment {
    View rootView;
    DBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_expense_top,container,false);

        //load all account from database
        dbHandler= new DBHandler(getActivity(),null,null,1);
        final List<Account> account = dbHandler.getAllAccount();

        //initial the spinner
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner_expense_account_top);
        ExpenseAccountAdapter expenseAccountAdapter= new ExpenseAccountAdapter(getActivity().getApplicationContext(),account);
        spinner.setAdapter(expenseAccountAdapter);

        return rootView;

    }
}
