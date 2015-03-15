package com.example.bryanty.projectx_smym5;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bryanty.projectx_smym5.domain.Account;
import com.example.bryanty.projectx_smym5.domain.Expense;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by BRYANTY on 14/03/2015.
 */
public class CreateExpenseFragment extends Fragment {
    View rootView;
    DBHandler dbHandler;

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    Button buttonSetDate;
    ImageButton btnAdd;
    ImageButton btnCancel;
    EditText expAmmount;
    EditText expTimes;
    String expType;
    String accID;
    String formatedDate;
    Date formatedDate_date;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create_expense, container, false);

        //get value from previous fragment
        Bundle bundle = this.getArguments();
        accID= bundle.getString("accountID");
        Toast.makeText(getActivity(),"value pass from > "+accID,Toast.LENGTH_SHORT).show();

        //initial the spinner
        spinner= (Spinner)rootView.findViewById(R.id.spinner_expense_type);
        adapter=ArrayAdapter.createFromResource(getActivity(),R.array.type_array,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void  onItemSelected(AdapterView<?> parent,View view,int position,long id){
                Toast.makeText(getActivity(),"item selected > "+parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                expType= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //initial set date button
        buttonSetDate=(Button)rootView.findViewById(R.id.button_set_date);
        buttonSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment date = new DatePickerFragment();
                /**
                 * Set Up Current Date Into dialog
                 */
                Calendar calender = Calendar.getInstance();
                Bundle args = new Bundle();
                args.putInt("year", calender.get(Calendar.YEAR));
                args.putInt("month", calender.get(Calendar.MONTH));
                args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
                date.setArguments(args);
                /**
                 * Set Call back to capture selected date
                 */
                date.setCallBack(ondate);
                date.show(getFragmentManager(), "Date Picker");
            }

            DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    Toast.makeText(
                            getActivity(),
                            String.valueOf(year) + "-" + String.valueOf(monthOfYear+1)
                                    + "-" + String.valueOf(dayOfMonth),
                            Toast.LENGTH_LONG).show();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                   formatedDate = String.valueOf(dayOfMonth)+"-"+ String.valueOf(monthOfYear+1)+"-"+ String.valueOf(year);

                    try {
                        formatedDate_date= sdf.parse(formatedDate);
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            };
        });

        btnAdd=(ImageButton) rootView.findViewById(R.id.imageButtonAddExp);
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                expAmmount = (EditText)rootView.findViewById(R.id.editText_ExpAmmount);
                expTimes = (EditText)rootView.findViewById(R.id.editText_times);
                dbHandler= new DBHandler(getActivity(),null,null,1);

                String hawaii= expType+Double.parseDouble(expAmmount.getText().toString())+">>>"+formatedDate+"<<<<<"+accID;

                Expense expense=new Expense(expType,Double.parseDouble(expAmmount.getText().toString()),formatedDate,Integer.parseInt(accID));

                Log.v("MyActivity", "====>" +hawaii ); //print message to console
                Log.v("MyActivity", "====!!!!!!!!!!!!!!!!>" +formatedDate_date ); //print message to console

                Log.v("MyActivity", "type>" +expense.get_expType() ); //print message to console
                Log.v("MyActivity", "amount>" +expense.get_expAmount() ); //print message to console
                Log.v("MyActivity", "date>" +expense.get_expDate() ); //print message to console
                Log.v("MyActivity", "id>" +expense.get_accID() ); //print message to console

                Toast.makeText(getActivity(),"item selected > "+hawaii,Toast.LENGTH_SHORT).show();
               dbHandler.addExpense(expense);

                Toast.makeText(getActivity(), "Successful create expense" , Toast.LENGTH_LONG).show();

//                //back to account fragment
//                Fragment objFragment=new ExpenseFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction()
//                        .replace(R.id.container,objFragment)
//                        .addToBackStack(null)
//                        .commit();
            }
        });

        //make the keyboard do not put view to upper
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        return rootView;
    }
}
