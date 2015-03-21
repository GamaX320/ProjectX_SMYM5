//package com.example.bryanty.projectx_smym5;
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.app.DialogFragment;
//import android.content.DialogInterface;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.Toast;
//
///**
// * Created by BRYANTY on 21/03/2015.
// */
//public class ColorDialogFragment extends DialogFragment implements OnClickListener {
//
//    //get shared preferences
//    SharedPreferences.Editor prefs;
//    SharedPreferences mySharedPreferences;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.dialog_fragment_color, container);
//
//        prefs=  PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
//        mySharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
//        int my_color_preference= mySharedPreferences.getInt("color_prefs",0);
//
//        Log.v("MyActivity", "you color selected2222 >>> " + my_color_preference);
//
//        view.findViewById(R.id.radioButton_color1).setOnClickListener(this);
//        view.findViewById(R.id.radioButton_color2).setOnClickListener(this);
//        view.findViewById(R.id.radioButton_color3).setOnClickListener(this);
//        view.findViewById(R.id.radioButton_color4).setOnClickListener(this);
//        view.findViewById(R.id.radioButton_color5).setOnClickListener(this);
//        view.findViewById(R.id.radioButton_color6).setOnClickListener(this);
//        view.findViewById(R.id.radioButton_color7).setOnClickListener(this);
//
//        Button cancelButton;
//        cancelButton=(Button)view.findViewById(R.id.button_color_cancel);
//        cancelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               getDialog().dismiss();
//            }
//        });
//
//        this.getDialog().setTitle("Select color");
//        return view;
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch(v.getId()){
//            case R.id.radioButton_color1:
//                prefs.putInt("color_prefs", 1);
//                break;
//
//            case R.id.radioButton_color2:
//                prefs.putInt("color_prefs", 2);
//                break;
//
//            case R.id.radioButton_color3:
//                prefs.putInt("color_prefs", 3);
//                break;
//
//            case R.id.radioButton_color4:
//                prefs.putInt("color_prefs", 4);
//                break;
//
//            case R.id.radioButton_color5:
//                prefs.putInt("color_prefs", 5);
//                break;
//
//            case R.id.radioButton_color6:
//                prefs.putInt("color_prefs", 6);
//                break;
//
//            case R.id.radioButton_color7:
//                prefs.putInt("color_prefs", 7);
//                break;
//        }
//
//        //commit
//        prefs.commit();
//        Toast.makeText(getActivity(), "Settings Saved, required restart application to take effect", Toast.LENGTH_LONG).show();
//    }
//}
