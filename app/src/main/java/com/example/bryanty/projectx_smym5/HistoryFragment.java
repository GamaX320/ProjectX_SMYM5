package com.example.bryanty.projectx_smym5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bryanty.projectx_smym5.domain.Account;
import com.example.bryanty.projectx_smym5.domain.History;

import java.util.List;

/**
 * Created by BRYANTY on 22/03/2015.
 */
public class HistoryFragment extends Fragment {
    View rootView;
    DBHandler dbHandler;

    Integer tempPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_history, container, false);

        //load all account from database
        dbHandler= new DBHandler(getActivity(),null,null,1);
        final List<History> histories = dbHandler.getAllHistory();

        //custom adapter
        ListAdapter historyAdapter=new HistoryRowAdapter(getActivity().getApplicationContext(), histories);
        final ListView historyListView=(ListView)rootView.findViewById(R.id.listView_history);
        historyListView.setAdapter(historyAdapter);

        //listview listener
        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

            }
        });

        //add new account button listener
        ImageButton btnClear=(ImageButton) rootView.findViewById(R.id.imageButton_clear);
        btnClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Delete History");
                alert.setMessage("Are you sure to delete all records ");

                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do your work here
                        //load all account from database
                        dbHandler= new DBHandler(getActivity(),null,null,1);
                        dbHandler.deleteAllHistory();

                        //refresh list view
                        //load all account from database
                        dbHandler= new DBHandler(getActivity(),null,null,1);
                        final List<History> history = dbHandler.getAllHistory();
                        //custom adapter
                        ListAdapter historyAdapter=new HistoryRowAdapter(getActivity().getApplicationContext(), history);
                        ListView historyListView=(ListView)rootView.findViewById(R.id.listView_history);
                        historyListView.setAdapter(historyAdapter);

                        dialog.dismiss();

                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });

        //long press list view for delete selected item
        historyListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Delete History");
                alert.setMessage("Are you sure to delete this record ");
                tempPosition=position;

                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do your work here
                        //load all account from database
                        dbHandler= new DBHandler(getActivity(),null,null,1);
                        dbHandler.deleteHistory(histories.get(tempPosition).get_hisID());

                        //refresh list view
                        //load all account from database
                        dbHandler= new DBHandler(getActivity(),null,null,1);
                        final List<History> history = dbHandler.getAllHistory();
                        //custom adapter
                        ListAdapter historyAdapter=new HistoryRowAdapter(getActivity().getApplicationContext(), history);
                        ListView historyListView=(ListView)rootView.findViewById(R.id.listView_history);
                        historyListView.setAdapter(historyAdapter);

                        dialog.dismiss();

                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();

                return false;
            }
        });

        return rootView;
    }
}
