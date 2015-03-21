//package com.example.bryanty.projectx_smym5;
//
//import android.app.DialogFragment;
//import android.app.FragmentManager;
//import android.os.Bundle;
//import android.preference.Preference;
//import android.preference.PreferenceFragment;
//
///**
// * Created by BRYANTY on 21/03/2015.
// */
//public class PrefsFragment extends PreferenceFragment {
//    Preference colorButton;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//    // TODO Auto-generated method stub
//        super.onCreate(savedInstanceState);
//        super.
//    // Load the preferences from an XML resource
//        addPreferencesFromResource(R.xml.preferences);
//
//        colorButton=(Preference)findPreference("color");
//        colorButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference arg0) {
//                //code for what you want it to do
//                DialogFragment newFragment = new ColorDialogFragment();
//                newFragment.show(getFragmentManager(), "dialog");
//
//                return true;
//            }
//        });
//    }
//}
