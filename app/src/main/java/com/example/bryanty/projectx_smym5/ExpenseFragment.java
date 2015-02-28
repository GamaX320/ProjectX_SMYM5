package com.example.bryanty.projectx_smym5;

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
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by BRYANTY on 28/02/2015.
 */
public class ExpenseFragment extends Fragment {
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_expense,container,false);

        String[] expenseItem={"Food","Transport","Entertainment","Food","Other"};

        //custom adapter
        ListAdapter expenseAdapter=new ExpenseRowAdapter(getActivity().getApplicationContext(), expenseItem);
        ListView expenseListView=(ListView)rootView.findViewById(R.id.listView_expense_item);
        expenseListView.setAdapter(expenseAdapter);

        //list view click listener
        expenseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                // TODO Auto-generated method stub
                // Intent intent = new Intent(getActivity(), nextactivity.class);
                // startActivity(intent);
                Toast.makeText(getActivity(), "Click ListItem Number " + position, Toast.LENGTH_LONG).show();
            }
        });

        TextView accAvatar=(TextView)rootView.findViewById(R.id.textView_expense_item_avatar);
        TextView accName=(TextView)rootView.findViewById(R.id.textView_expense_item_name);
        TextView accAmount=(TextView)rootView.findViewById(R.id.textView_expense_amount);
        accName.setText("Steve");
        accAmount.setText("RM 19.20");
        accAvatar.setText(accName.getText().toString().substring(0, 1));

        //add new expense button
        ImageButton btnAddExpense= (ImageButton)rootView.findViewById(R.id.imageButton_add_expense);
        btnAddExpense.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Intent intent=new Intent(getActivity(),CreateExpenseActivity.class);
                //startActivity(intent);
            }
        });

        return rootView;
    }
}
