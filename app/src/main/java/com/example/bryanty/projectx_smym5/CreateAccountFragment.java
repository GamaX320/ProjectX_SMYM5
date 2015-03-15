package com.example.bryanty.projectx_smym5;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.example.bryanty.projectx_smym5.domain.Account;

import java.util.List;

/**
 * Created by BRYANTY on 08/03/2015.
 */
public class CreateAccountFragment extends Fragment implements OnClickListener {
    View rootView;
    DBHandler dbHandler;

    EditText accName;
    EditText accAmount;

    ImageButton btnAdd;
    ImageButton btnCancel;
    ImageButton btnAvatar1;
    ImageButton btnAvatar2;
    ImageButton btnAvatar3;
    ImageButton btnAvatar4;
    ImageButton btnAvatar5;
    ImageButton btnAvatar6;
    ImageButton btnAvatar7;

    //selected avatar index
    Integer selectedAvatar=0;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create_account, container, false);

        btnAdd=(ImageButton) rootView.findViewById(R.id.imageButtonAddAcc);
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                accName = (EditText)rootView.findViewById(R.id.editText_AccountName);
                accAmount = (EditText)rootView.findViewById(R.id.editText_AccountAmount);
                dbHandler= new DBHandler(getActivity(),null,null,1);

                Account account=new Account(accName.getText().toString(),selectedAvatar,Double.parseDouble(accAmount.getText().toString()) );
                dbHandler.addAccount(account);
                Log.v("MyActivity", "index=" +Double.parseDouble(accAmount.getText().toString()) ); //print message to console
                Toast.makeText(getActivity(), "Successful create account" , Toast.LENGTH_LONG).show();
                printDatabase();

                //back to account fragment
                Fragment objFragment=new AccountFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container,objFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnAvatar1=(ImageButton) rootView.findViewById(R.id.imageButtonAvatar1);
        btnAvatar2=(ImageButton) rootView.findViewById(R.id.imageButtonAvatar2);
        btnAvatar3=(ImageButton) rootView.findViewById(R.id.imageButtonAvatar3);
        btnAvatar4=(ImageButton) rootView.findViewById(R.id.imageButtonAvatar4);
        btnAvatar5=(ImageButton) rootView.findViewById(R.id.imageButtonAvatar5);
        btnAvatar6=(ImageButton) rootView.findViewById(R.id.imageButtonAvatar6);
        btnAvatar7=(ImageButton) rootView.findViewById(R.id.imageButtonAvatar7);

        btnAvatar1.setOnClickListener(this);
        btnAvatar2.setOnClickListener(this);
        btnAvatar3.setOnClickListener(this);
        btnAvatar4.setOnClickListener(this);
        btnAvatar5.setOnClickListener(this);
        btnAvatar6.setOnClickListener(this);
        btnAvatar7.setOnClickListener(this);

        //make the keyboard do not put view to upper
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..

        btnAvatar1.setBackgroundColor(Color.parseColor("#DEDEDE"));
        btnAvatar2.setBackgroundColor(Color.parseColor("#DEDEDE"));
        btnAvatar3.setBackgroundColor(Color.parseColor("#DEDEDE"));
        btnAvatar4.setBackgroundColor(Color.parseColor("#DEDEDE"));
        btnAvatar5.setBackgroundColor(Color.parseColor("#DEDEDE"));
        btnAvatar6.setBackgroundColor(Color.parseColor("#DEDEDE"));
        btnAvatar7.setBackgroundColor(Color.parseColor("#DEDEDE"));

        switch(v.getId()){
            case R.id.imageButtonAvatar1:
                btnAvatar1.setBackgroundColor(Color.GRAY);
                selectedAvatar=1;
                break;
            case R.id.imageButtonAvatar2:
                btnAvatar2.setBackgroundColor(Color.GRAY);
                selectedAvatar=2;
                break;
            case R.id.imageButtonAvatar3:
                btnAvatar3.setBackgroundColor(Color.GRAY);
                selectedAvatar=3;
                break;
            case R.id.imageButtonAvatar4:
                btnAvatar4.setBackgroundColor(Color.GRAY);
                selectedAvatar=4;
                break;
            case R.id.imageButtonAvatar5:
                btnAvatar5.setBackgroundColor(Color.GRAY);
                selectedAvatar=5;
                break;
            case R.id.imageButtonAvatar6:
                btnAvatar6.setBackgroundColor(Color.GRAY);
                selectedAvatar=6;
                break;
            case R.id.imageButtonAvatar7:
                btnAvatar7.setBackgroundColor(Color.GRAY);
                selectedAvatar=7;
                break;
        }

        Log.v("MyActivity", "selected=" + selectedAvatar); //print message to console
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


        List<Account> values = dbHandler.getAllAccount();

//        while(values != null){
//            abc.setText(values.toString());
//        }


        accName.setText("");
        accAmount.setText("");
    }
}
