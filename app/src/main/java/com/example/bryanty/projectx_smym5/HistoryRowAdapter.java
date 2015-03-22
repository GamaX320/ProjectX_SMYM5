package com.example.bryanty.projectx_smym5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bryanty.projectx_smym5.domain.History;

import java.util.List;
import java.util.Random;

/**
 * Created by BRYANTY on 22/03/2015.
 */
public class HistoryRowAdapter extends ArrayAdapter<History> {

    public HistoryRowAdapter(Context context, List<History> expenses) {
        super(context,R.layout.adapter_history_row ,expenses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater rowAccInflater= LayoutInflater.from(getContext());
        View customView= rowAccInflater.inflate(R.layout.adapter_history_row,parent,false);

        History singleHistory= getItem(position);
        TextView hisAvatar= (TextView)customView.findViewById(R.id.textView_history_avatar);
        TextView hisType= (TextView)customView.findViewById(R.id.textView_history_type);
        TextView hisDate= (TextView)customView.findViewById(R.id.textView_history_date);

        //random select avatar background
        Random randomAvatar = new Random();
        int res;
        int i= randomAvatar.nextInt(11);

        switch (i) {
            case 0: res = R.drawable.ic_avatar_black_1; break;
            case 1: res = R.drawable.ic_avatar_black_2; break;
            case 2: res = R.drawable.ic_avatar_black_3; break;
            case 3: res = R.drawable.ic_avatar_black_4; break;
            case 4: res = R.drawable.ic_avatar_black_5; break;
            case 5: res = R.drawable.ic_avatar_black_6; break;
            case 6: res = R.drawable.ic_avatar_black_7; break;
            default:res = R.drawable.ic_avatar_black_1; break;
        }

        hisAvatar.setBackgroundResource(res);
        hisAvatar.setText(singleHistory.get_hisType().substring(0,1));
        hisType.setText(singleHistory.get_hisType());
        hisDate.setText(singleHistory.get_hisDate());

        return customView;
    }
}
