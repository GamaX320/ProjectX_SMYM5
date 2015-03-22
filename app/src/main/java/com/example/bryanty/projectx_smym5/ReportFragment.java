package com.example.bryanty.projectx_smym5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.bryanty.projectx_smym5.domain.Account;

import java.util.List;

/**
* Created by Hansem on 16-Mar-15.
*/
public class ReportFragment extends Fragment {
    View rootView;
    int accountID;
    DBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_report,container,false);

        dbHandler= new DBHandler(getActivity(),null,null,1);
        final List<Account> account = dbHandler.getAllAccount();

        //initial the spinner
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner_report_view);
        ExpenseAccountAdapter expenseAccountAdapter= new ExpenseAccountAdapter(getActivity().getApplicationContext(),account);
        spinner.setAdapter(expenseAccountAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void  onItemSelected(AdapterView<?> parent,View view,int position,long id){

                accountID= account.get(position).get_accID();

                Fragment objFragment=new PieChartFragment();
                //pass value to another fragment
                Bundle bundle = new Bundle();
                bundle.putInt("accountID",account.get(position).get_accID());
                objFragment.setArguments(bundle);
                //ready to replace next fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_report,objFragment)
                        .addToBackStack(null)
                        .commit();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return rootView;
    }
}
