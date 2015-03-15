package com.example.bryanty.projectx_smym5;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
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
import android.widget.RadioGroup;
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
    Integer accID;
    String formatedDate;
    Date formatedDate_date;
    RadioGroup radioGroupRepeat;
    Integer dayAfter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create_expense, container, false);

        //get value from previous fragment
        Bundle bundle = this.getArguments();
        accID= bundle.getInt("accountID");;

        expAmmount = (EditText)rootView.findViewById(R.id.editText_ExpAmmount);
        expTimes = (EditText)rootView.findViewById(R.id.editText_times);
        radioGroupRepeat= (RadioGroup)rootView.findViewById(R.id.radioGroup_repeat);

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
                  // formatedDate = String.valueOf(dayOfMonth)+"-"+ String.valueOf(monthOfYear+1)+"-"+ String.valueOf(year);
                    formatedDate = String.valueOf(year)+"-"+ String.valueOf(monthOfYear+1)+"-"+String.valueOf(dayOfMonth);

                    try {
                        formatedDate_date= sdf.parse(formatedDate);
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            };
        });

        //initial radio group listener
        radioGroupRepeat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d("chk", "id" + checkedId);

                if (checkedId == R.id.radioButton_weekly) {
                    //min 1, max 52 because 1 year only 52 weeks
                    expTimes.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "52")});
                    dayAfter=7;
                } else if (checkedId ==R.id.radioButton_monthly) {
                    //min 1, max 12 because 1 year only 12 months
                    expTimes.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "12")});
                    dayAfter=30;
                }
                else{
                    dayAfter=0;
                }


            }

        });

        btnAdd=(ImageButton) rootView.findViewById(R.id.imageButtonAddExp);
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dbHandler= new DBHandler(getActivity(),null,null,1);

                Expense expense=new Expense(expType,Double.parseDouble(expAmmount.getText().toString()),formatedDate,accID);

                int repeat = Integer.parseInt(expTimes.getText().toString());
                Log.v("MyActivity", "repeat=" + repeat); //print message to console

//                ///////////////
//                //add more date
//                String dt = "2012-01-31";  // Start date
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                Calendar c = Calendar.getInstance();
//                try {
//                    c.setTime(sdf.parse(dt));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                c.add(Calendar.DATE, 7);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
//                String output = sdf.format(c.getTime());
//                Log.v("MyActivity", "day add=" + output); //print message to console

                formatedDate= addMoreDay(formatedDate,dayAfter);

                dbHandler.addExpense(expense);

                //update account amount
                boolean result = false;
                Account account = new Account();
                account.set_accID(accID);
                account.set_accAmount(Double.parseDouble(expAmmount.getText().toString()));
                result = dbHandler.updateAccount(account,1);

                if(result == true){
                    Toast.makeText(getActivity(), "Successful create expense" , Toast.LENGTH_LONG).show();
                }

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

    public String addMoreDay(String startDate,int days){
       // String startDate = "2012-01-04";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, days);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        String output = sdf.format(c.getTime());

        return output;
    }

}
