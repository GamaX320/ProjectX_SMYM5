package com.example.bryanty.projectx_smym5;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bryanty.projectx_smym5.domain.Account;

import java.util.List;

/**
 * Created by BRYANTY on 14/03/2015.
 */
public class ExpenseAccountAdapter extends ArrayAdapter<Account>{

    public ExpenseAccountAdapter(Context context, List<Account> account) {
        super(context,R.layout.adapter_account_row ,account);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater rowAccInflater= LayoutInflater.from(getContext());
        View customView= rowAccInflater.inflate(R.layout.adapter_account_row,parent,false);

        //get each account based on the position
        Account singleAcc=getItem(position);

        TextView accAvatar=(TextView)customView.findViewById(R.id.textView_acc_avatar);
        TextView accName=(TextView)customView.findViewById(R.id.textView_acc_name);
        TextView accAmount=(TextView)customView.findViewById(R.id.textView_acc_amount);

        //check selected avatar color
        int selectedAvatar= singleAcc.get_accColor();
        switch (selectedAvatar){
            case 1:
                accAvatar.setBackgroundResource(R.drawable.ic_avatar_black_1);
                break;
            case 2:
                accAvatar.setBackgroundResource(R.drawable.ic_avatar_black_2);
                break;
            case 3:
                accAvatar.setBackgroundResource(R.drawable.ic_avatar_black_3);
                break;
            case 4:
                accAvatar.setBackgroundResource(R.drawable.ic_avatar_black_4);
                break;
            case 5:
                accAvatar.setBackgroundResource(R.drawable.ic_avatar_black_5);
                break;
            case 6:
                accAvatar.setBackgroundResource(R.drawable.ic_avatar_black_6);
                break;
            case 7:
                accAvatar.setBackgroundResource(R.drawable.ic_avatar_black_7);
                break;
            default:
                accAvatar.setBackgroundResource(R.drawable.ic_avatar_black_1);
                break;
        }

        accAvatar.setText(singleAcc.get_accName().substring(0,1));
        accName.setText(singleAcc.get_accName());
        accAmount.setText("RM "+String.format("%.2f",singleAcc.get_accAmount()));

        accName.setTextColor(Color.WHITE);
        accAmount.setTextColor(Color.WHITE);

        return customView;
    }


}
