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
public class AccountRowAdapter extends ArrayAdapter<String> {
    public AccountRowAdapter(Context context, String[] accountName) {
        super(context,R.layout.adapter_account_row ,accountName);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater rowAccInflater= LayoutInflater.from(getContext());
        View customView= rowAccInflater.inflate(R.layout.adapter_account_row,parent,false);

        String singleAcc= getItem(position);
        TextView accAvatar=(TextView)customView.findViewById(R.id.textView_acc_avatar);
        TextView accName=(TextView)customView.findViewById(R.id.textView_acc_name);
        TextView accAmount=(TextView)customView.findViewById(R.id.textView_acc_amount);

        accAvatar.setText(singleAcc.substring(0,1));
        accName.setText(singleAcc);
        accAmount.setText("RM50.00");

        return customView;
    }
}
