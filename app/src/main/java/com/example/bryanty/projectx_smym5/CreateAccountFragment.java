package com.example.bryanty.projectx_smym5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bryanty.projectx_smym5.domain.Account;

import java.util.List;

/**
 * Created by BRYANTY on 08/03/2015.
 */
public class CreateAccountFragment extends Fragment {
    View rootView;
    DBHandler dbHandler;

    EditText accName;
    EditText accAmount;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create_account, container, false);

        ImageButton btnAdd=(ImageButton) rootView.findViewById(R.id.imageButtonAddAcc);
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                accName = (EditText)rootView.findViewById(R.id.editText_AccountName);
                accAmount = (EditText)rootView.findViewById(R.id.editText_AccountAmount);
                dbHandler= new DBHandler(getActivity(),null,null,1);

                Account account=new Account(accName.getText().toString(),1,Double.parseDouble(accAmount.getText().toString()) );
                dbHandler.addAccount(account);
                Toast.makeText(getActivity(), "Success" , Toast.LENGTH_LONG).show();
                printDatabase();
            }
        });

        return rootView;
    }

//    //create new account listener
//    public void createAccountClicked(View v){
//
//        accName = (EditText)rootView.findViewById(R.id.editText_AccountName);
//        accAmount = (EditText)rootView.findViewById(R.id.editText_AccountAmount);
//        dbHandler= new DBHandler(getActivity(),null,null,1);
//
//        Account account=new Account(accName.getText().toString(),Integer.parseInt(accAmount.getText().toString()) );
//        dbHandler.addAccount(account);
//        printDatabase();
//    }

    public void printDatabase(){
        TextView abc;
        abc= (TextView)rootView.findViewById(R.id.textViewABC);

        List<Account> values = dbHandler.getAllAccount();
        String values2= dbHandler.getAccount();

//        while(values != null){
//            abc.setText(values.toString());
//        }
        abc.setText(values2);

        accName.setText("");
        accAmount.setText("");
    }
}
