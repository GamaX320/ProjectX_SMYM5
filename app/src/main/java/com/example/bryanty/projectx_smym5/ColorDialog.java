//package com.example.bryanty.projectx_smym5;
//
//import android.app.Activity;
//import android.app.DialogFragment;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.text.InputFilter;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//
///**
// * Created by BRYANTY on 17/03/2015.
// */
//public class ColorDialog extends DialogFragment {
//    View rootView;
//
//    RadioGroup radioGroupColor;
//    RadioButton radioButtonColor1;
//    RadioButton radioButtonColor2;
//    RadioButton radioButtonColor3;
//    RadioButton radioButtonColor4;
//    RadioButton radioButtonColor5;
//    RadioButton radioButtonColor6;
//    RadioButton radioButtonColor7;
//
//    Integer colorIndex;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        rootView= inflater.inflate(R.layout.fragment_dialog_color, container);
//
//        getDialog().setTitle("Select Color");
//
//        radioGroupColor= (RadioGroup)rootView.findViewById(R.id.radioGroup_color);
//        radioButtonColor1=(RadioButton)rootView.findViewById(R.id.radioButton_color_1);
//        radioButtonColor2=(RadioButton)rootView.findViewById(R.id.radioButton_color_2);
//        radioButtonColor3=(RadioButton)rootView.findViewById(R.id.radioButton_color_3);
//        radioButtonColor4=(RadioButton)rootView.findViewById(R.id.radioButton_color_4);
//        radioButtonColor5=(RadioButton)rootView.findViewById(R.id.radioButton_color_5);
//        radioButtonColor6=(RadioButton)rootView.findViewById(R.id.radioButton_color_6);
//        radioButtonColor7=(RadioButton)rootView.findViewById(R.id.radioButton_color_7);
//
//        radioGroupColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//                if (checkedId == R.id.radioButton_color_1) {
//                    colorIndex= 1;
//
//                } else if (checkedId == R.id.radioButton_color_2) {
//                    colorIndex= 2;
//
//                } else if (checkedId == R.id.radioButton_color_3) {
//                    colorIndex= 3;
//
//                }else if (checkedId == R.id.radioButton_color_4) {
//                    colorIndex= 4;
//
//                }else if (checkedId == R.id.radioButton_color_5) {
//                    colorIndex= 5;
//
//                }else if (checkedId == R.id.radioButton_color_6) {
//                    colorIndex= 6;
//
//                }
//                else if (checkedId == R.id.radioButton_color_7) {
//                    colorIndex= 7;
//
//                }
//                else {
//                    colorIndex= 1;
//                }
//
//                Log.d("MainActivity", "selected color index" + colorIndex);
//            }
//
//
//        });
//
//        return rootView;
//    }
//
//}
