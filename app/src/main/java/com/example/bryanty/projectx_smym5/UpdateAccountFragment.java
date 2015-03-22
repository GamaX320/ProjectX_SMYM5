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
import com.example.bryanty.projectx_smym5.domain.History;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by BRYANTY on 15/03/2015.
 */
public class UpdateAccountFragment extends Fragment  implements OnClickListener {
    View rootView;
    DBHandler dbHandler;

    TextView title;
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

    int accID;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create_account, container, false);

        //get value from previous fragment
        Bundle bundle = this.getArguments();
        accID= bundle.getInt("accountID");;

        accName = (EditText)rootView.findViewById(R.id.editText_AccountName);
        accAmount = (EditText)rootView.findViewById(R.id.editText_AccountAmount);

        //get account information
        Account account = new Account();
        dbHandler= new DBHandler(getActivity(),null,null,1);
        account= dbHandler.getAccount(accID);
//        Log.v("MyActivity", "1=" + accID); //print message to console
//        Log.v("MyActivity", "asdasdasd=" + account.get_accName()); //print message to console
        //load account information
        accName.setText(account.get_accName());
        accAmount.setText(""+account.get_accAmount());

        title= (TextView)rootView.findViewById(R.id.textView5);
        title.setText("Update Account");

        btnAdd=(ImageButton) rootView.findViewById(R.id.imageButtonAddAcc);
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dbHandler= new DBHandler(getActivity(),null,null,1);

                if(accName.getText().toString().equals("") || accName.getText().toString().length() == 0){
                    accName.setFocusable(true);
                    accName.setHint("Please enter name");
                    accName.setHintTextColor(Color.RED);
                }
                else if(accAmount.getText().toString().equals("") || accAmount.getText().toString().length() == 0){
                    accAmount.setFocusable(true);
                    accAmount.setHint("Please enter amount");
                    accAmount.setHintTextColor(Color.RED);
                }
                else {
                    boolean result =false;
                    Account account = new Account(accID,accName.getText().toString(), selectedAvatar, Double.parseDouble(accAmount.getText().toString()));
                    result= dbHandler.updateAccount(account,2);

                    if(result == true){
                        Toast.makeText(getActivity(), "Successful update account", Toast.LENGTH_LONG).show();
                    }

                    //add new history
                    //default get today date
                    Calendar c = Calendar.getInstance();
                    String todayDate="";
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");
                    todayDate = df.format(c.getTime());
                    History history=new History("Update account "+accName.getText().toString(),todayDate);
                    dbHandler.addHistory(history);

                    //back to account fragment
                    Fragment objFragment = new AccountFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, objFragment)
                            .addToBackStack(null)
                            .commit();

                }
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

        //load avatar from database
        Log.v("MyActivity", "avatar number=" + account.get_accColor()); //print message to console
        loadAvatar(account.get_accColor());

        btnCancel=(ImageButton) rootView.findViewById(R.id.imageButtonCancelAcc);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //back to account fragment
                Fragment objFragment=new AccountFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container,objFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

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

    public void loadAvatar(int selectedAvatar){
        switch(selectedAvatar){
            case 1:
                btnAvatar1.setBackgroundColor(Color.GRAY);
                break;
            case 2:
                btnAvatar2.setBackgroundColor(Color.GRAY);
                break;
            case 3:
                btnAvatar3.setBackgroundColor(Color.GRAY);
                break;
            case 4:
                btnAvatar4.setBackgroundColor(Color.GRAY);
                break;
            case 5:
                btnAvatar5.setBackgroundColor(Color.GRAY);
                break;
            case 6:
                btnAvatar6.setBackgroundColor(Color.GRAY);
                break;
            case 7:
                btnAvatar7.setBackgroundColor(Color.GRAY);
                break;

        }
    }
}
