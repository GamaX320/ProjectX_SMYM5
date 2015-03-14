package com.example.bryanty.projectx_smym5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by BRYANTY on 14/03/2015.
 */
public class CreateExpenseFragment extends Fragment {
    View rootView;

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create_expense, container, false);

        //initial the spinner
        spinner= (Spinner)rootView.findViewById(R.id.spinner_expense_type);
        adapter=ArrayAdapter.createFromResource(getActivity(),R.array.type_array,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void  onItemSelected(AdapterView<?> parent,View view,int position,long id){
                Toast.makeText(getActivity(),"item selected > "+parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //get value from previous fragment
        Bundle bundle = this.getArguments();
        String test1= bundle.getString("accountID");
        Toast.makeText(getActivity(),"value pass from > "+test1,Toast.LENGTH_SHORT).show();

        return rootView;
    }
}
