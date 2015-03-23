package com.example.bryanty.projectx_smym5;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputFilter;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bryanty.projectx_smym5.domain.Account;
import com.example.bryanty.projectx_smym5.domain.Expense;
import com.example.bryanty.projectx_smym5.domain.History;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by BRYANTY on 16/03/2015.
 */
public class UpdateExpenseFragment extends Fragment {
    View rootView;
    DBHandler dbHandler;

    Integer accID;
    Integer expID;
    Double oldAmount;

    TextView title;
    Button buttonSetDate;
    ImageButton btnAdd;
    ImageButton btnCancel;
    EditText expAmmount;
    EditText expTimes;
    String expType;
    String formatedDate;
    Date formatedDate_date;
    RadioGroup radioGroupRepeat;
    Integer dayAfter = 0;
    RadioButton radioButtonOnce;
    RadioButton radioButtonDaily;
    RadioButton radioButtonWeekly;
    RadioButton radioButtonMonthly;

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_update_expense, container, false);

        //get value from previous fragment
        Bundle bundle = this.getArguments();
        accID= bundle.getInt("accountID");
        expID= bundle.getInt("expenseID");

        //get expense information
        Expense expense = new Expense();
        dbHandler= new DBHandler(getActivity(),null,null,1);
        expense= dbHandler.getExpenseByID(accID,expID);

        title=(TextView)rootView.findViewById(R.id.textView15);
        title.setText("Update Expense");

        expAmmount = (EditText)rootView.findViewById(R.id.editText_ExpAmmount);
        expTimes = (EditText)rootView.findViewById(R.id.editText_times);
        radioGroupRepeat= (RadioGroup)rootView.findViewById(R.id.radioGroup_repeat);
        radioButtonOnce= (RadioButton)rootView.findViewById(R.id.radioButton_once);
        radioButtonDaily= (RadioButton)rootView.findViewById(R.id.radioButton_daily);
        radioButtonWeekly= (RadioButton)rootView.findViewById(R.id.radioButton_weekly);
        radioButtonMonthly= (RadioButton)rootView.findViewById(R.id.radioButton_monthly);

        //default get today date
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");
        formatedDate = df.format(c.getTime());

        //initial the spinner
        spinner= (Spinner)rootView.findViewById(R.id.spinner_expense_type);
        adapter= ArrayAdapter.createFromResource(getActivity(), R.array.type_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void  onItemSelected(AdapterView<?> parent,View view,int position,long id){
                expType= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //load account information
        spinner.setSelection(adapter.getPosition(expense.get_expType()));
        expAmmount.setText(""+expense.get_expAmount());
        oldAmount=expense.get_expAmount();

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
//        radioGroupRepeat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                Log.d("chk", "id" + checkedId);
//
//                if (checkedId == R.id.radioButton_weekly) {
//                    //min 1, max 4 because 1 month only 4 weeks
//                    expTimes.setEnabled(true);
//                    expTimes.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "4")});
//                    dayAfter=7;
//                } else if (checkedId ==R.id.radioButton_monthly) {
//                    //min 1, max 12 because 1 year only 12 months
//                    expTimes.setEnabled(true);
//                    expTimes.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "12")});
//                    dayAfter=30;
//                }
//                else if (checkedId ==R.id.radioButton_daily){
//                    //min 1, max 7 because 1 weak only 7 days
//                    expTimes.setEnabled(true);
//                    expTimes.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "7")});
//                    dayAfter=1;
//                }
//                else{
//                    expTimes.setEnabled(false);
//                    dayAfter=0;
//                }
//            }
//
//        });

        btnAdd=(ImageButton) rootView.findViewById(R.id.imageButtonAddExp);
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dbHandler= new DBHandler(getActivity(),null,null,1);

                if(expAmmount.getText().toString().equals("") || expAmmount.getText().length() == 0){
                    expAmmount.setFocusable(true);
                    expAmmount.setHint("Please enter amount");
                    expAmmount.setHintTextColor(Color.RED);
                }
                else{
                    // int repeat = Integer.parseInt(expTimes.getText().toString());
                    int repeat = 1;

                    //check whether the rapeat radio button checked or not
//                    if(radioButtonWeekly.isChecked() || radioButtonMonthly.isChecked() || radioButtonDaily.isChecked()){
//                        repeat = Integer.parseInt(expTimes.getText().toString());
//                    }
//                    else{
//                        repeat= 1;
//                    }

                   // Log.v("MyActivity", "repeat=" + repeat); //print message to console

                    boolean result = false;

                        //Log.v("MyActivity", "day add=" +formatedDate); //print message to console
                        Expense expense=new Expense(expID,expType,Double.parseDouble(expAmmount.getText().toString()),formatedDate,accID);

                        dbHandler.updateExpense(expense);

                        //update account amount
                        Account account = new Account();
                        Double newAmount=Double.parseDouble(expAmmount.getText().toString());
                        if(newAmount != oldAmount){
                            newAmount= newAmount-oldAmount;
                        }

                        account.set_accID(accID);
                        account.set_accAmount(newAmount);
                        result = dbHandler.updateAccount(account,1);


                    if(result == true){
                        Toast.makeText(getActivity(), "Successful create expense" , Toast.LENGTH_LONG).show();
                    }

                    //add new history
                    //default get today date
                    Calendar c = Calendar.getInstance();
                    String todayDate="";
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");
                    todayDate = df.format(c.getTime());
                    History history=new History("Update expense "+expType,todayDate);
                    dbHandler.addHistory(history);

                    //back to expense fragment
                    Fragment objFragment=new ExpenseFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container,objFragment)
                            .addToBackStack(null)
                            .commit();
                }   }
        });

        btnCancel=(ImageButton) rootView.findViewById(R.id.imageButtonCancelExp);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //back to account fragment
                Fragment objFragment=new ExpenseFragment();
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

    public String addMoreDay(String startDate,int days){
        // String startDate = "2012-01-04";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
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
