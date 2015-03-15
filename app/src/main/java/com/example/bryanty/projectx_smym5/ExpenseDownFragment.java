package com.example.bryanty.projectx_smym5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bryanty.projectx_smym5.domain.Account;
import com.example.bryanty.projectx_smym5.domain.Expense;

import java.util.List;

/**
 * Created by BRYANTY on 15/03/2015.
 */
public class ExpenseDownFragment extends Fragment {
    View rootView;
    DBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_expense_down,container,false);

        //load all account from database
        dbHandler= new DBHandler(getActivity(),null,null,1);
        final List<Expense> expense = dbHandler.getAllExpense();

        //custom adapter
        ListAdapter expenseAdapter=new ExpenseRowAdapter(getActivity().getApplicationContext(), expense);
        ListView expenseListView=(ListView)rootView.findViewById(R.id.listView_expense_item);
        expenseListView.setAdapter(expenseAdapter);

        //listview listener
        expenseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                // TODO Auto-generated method stub

                Log.v("MyActivity", "exp=" + expense.get(position).get_expID()); //print message to console

            }
        });

        //get value from previous fragment
        Bundle bundle = this.getArguments();
        String test1= bundle.getString("accountID");
        Toast.makeText(getActivity(),"value pass from > "+test1,Toast.LENGTH_SHORT).show();

        return rootView;
    }

//    //get data from another fragment
//    public void getData(String message){
//        Toast.makeText(getActivity(), "this is the message u find so long > " + message, Toast.LENGTH_SHORT).show();
//    }
}
