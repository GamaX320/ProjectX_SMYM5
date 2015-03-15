package com.example.bryanty.projectx_smym5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bryanty.projectx_smym5.domain.Expense;

import java.util.List;

/**
 * Created by BRYANTY on 28/02/2015.
 */
//public class ExpenseRowAdapter extends ArrayAdapter<String> {
public class ExpenseRowAdapter extends ArrayAdapter<Expense> {
    public ExpenseRowAdapter(Context context, List<Expense> expenses) {
        super(context,R.layout.adapter_expense_row ,expenses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater rowAccInflater= LayoutInflater.from(getContext());
        View customView= rowAccInflater.inflate(R.layout.adapter_expense_row,parent,false);

        Expense singleExp= getItem(position);
        TextView expItemAvatar=(TextView)customView.findViewById(R.id.textView_expense_item_avatar);
        TextView expItemName=(TextView)customView.findViewById(R.id.textView_expense_item_name);
        TextView expItemAmount=(TextView)customView.findViewById(R.id.textView_expense_item_amount);
        TextView expItemDate=(TextView)customView.findViewById(R.id.textView_expense_date);

        //check avatar color
        String selectedAvatar=singleExp.get_expType();
        switch (selectedAvatar){
            case "Food":
                expItemAvatar.setBackgroundResource(R.drawable.ic_food);
                break;
            case "Transport":
                expItemAvatar.setBackgroundResource(R.drawable.ic_transport);
                break;
            case "Entertainment":
                expItemAvatar.setBackgroundResource(R.drawable.ic_entertainment);
                break;
            case "Communication":
                expItemAvatar.setBackgroundResource(R.drawable.ic_communication);
                break;
            case "Housing":
                expItemAvatar.setBackgroundResource(R.drawable.ic_housing);
                break;
            case "Other":
                expItemAvatar.setBackgroundResource(R.drawable.ic_other);
                break;
            default:
                expItemAvatar.setBackgroundResource(R.drawable.ic_other);
                break;
        }

        //expItemAvatar.setText(singleExp.get_expType().substring(0,1));
        expItemName.setText(singleExp.get_expType());
        expItemAmount.setText("RM "+singleExp.get_expAmount());
        expItemDate.setText(singleExp.get_expDate());

        return customView;
    }
}
