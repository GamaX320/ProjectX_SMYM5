package com.example.bryanty.projectx_smym5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by BRYANTY on 28/02/2015.
 */
public class ExpenseRowAdapter extends ArrayAdapter<String> {
    public ExpenseRowAdapter(Context context, String[] typeName) {
        super(context,R.layout.adapter_expense_row ,typeName);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater rowAccInflater= LayoutInflater.from(getContext());
        View customView= rowAccInflater.inflate(R.layout.adapter_expense_row,parent,false);

        String singleAcc= getItem(position);
        TextView expItemAvatar=(TextView)customView.findViewById(R.id.textView_expense_item_avatar);
        TextView expItemName=(TextView)customView.findViewById(R.id.textView_expense_item_name);
        TextView expItemAmount=(TextView)customView.findViewById(R.id.textView_expense_item_amount);

        expItemAvatar.setText(singleAcc.substring(0,1));
        expItemName.setText(singleAcc);
        expItemAmount.setText("RM10.00");

        return customView;
    }
}
