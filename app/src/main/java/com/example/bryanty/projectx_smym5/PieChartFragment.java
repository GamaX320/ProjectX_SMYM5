//package com.example.bryanty.projectx_smym5;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.LinearLayout;
//import android.support.v4.app.Fragment;
//
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//import android.support.annotation.Nullable;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import com.example.bryanty.projectx_smym5.domain.Expense;
//
//public class PieChartFragment extends Fragment {
//    Integer accID;
//    DBHandler dbHandler;
//
//    View rootView;
//    LinearLayout pane;
//    private DrawGraph dg;
//
//    ArrayList<Integer> amtValue = new ArrayList<Integer>();
//
//    double total=0,calFood=0, calTransport=0,calEntertainment=0,calCommunication=0,calHousing=0,calOther=0;
//    TextView percentFood,percentTransport,percentEntertainment,percentCommunication,percentHousing,percentOther,
//            displayDate,titleDayRange;
//
//    Spinner spinnerReportView;
//    ArrayAdapter<CharSequence> adapterReportView;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        rootView = inflater.inflate(R.layout.fragment_pie_chart3, container, false);
//
//        percentFood = (TextView) rootView.findViewById(R.id.textViewFood);
//        percentTransport = (TextView) rootView.findViewById(R.id.textViewTransport);
//        percentCommunication = (TextView) rootView.findViewById(R.id.textViewCommunication);
//        percentEntertainment = (TextView) rootView.findViewById(R.id.textViewEntertainment);
//        percentHousing = (TextView) rootView.findViewById(R.id.textViewHousing);
//        percentOther = (TextView) rootView.findViewById(R.id.textViewOther);
//        displayDate = (TextView) rootView.findViewById(R.id.textDisplayDate);
//        titleDayRange  = (TextView) rootView.findViewById(R.id.textDateRange);
//        titleDayRange.setTextColor(Color.BLACK);
//        pane = (LinearLayout) rootView.findViewById(R.id.pane);
//
//        //get value from previous fragment
//        Bundle bundle = this.getArguments();
//        accID = bundle.getInt("accountID");
//
//        //Spinner Get Account id
//        dbHandler = new DBHandler(getActivity(), null, null, 1);
//        final List<Expense> expense = dbHandler.getExpense(accID);
//
//        //Spinner View Type
//        spinnerReportView = (Spinner) rootView.findViewById(R.id.spinnerReportView);
//        adapterReportView = ArrayAdapter.createFromResource(getActivity(), R.array.Type_report_view, android.R.layout.simple_spinner_dropdown_item);
//        adapterReportView.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerReportView.setAdapter(adapterReportView);
//        spinnerReportView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                int itemFood=0, itemTransport=0,itemEntertainment=0,itemCommunication=0,itemHousing=0,itemOther=0;
//                percentFood.setText("");
//                percentTransport.setText("");
//                percentEntertainment.setText("");
//                percentCommunication.setText("");
//                percentHousing.setText("");
//                percentOther.setText("");
//                displayDate.setText("");
//                displayDate.setTextColor(Color.BLACK);
//
//                    switch (position){
//                        case 0:
//                        SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd");
//                        Calendar c = Calendar.getInstance();
//                        String today = df.format(c.getTime());
//                        displayDate.setText(today.toString());
//                        for (int a = 0; a < expense.size(); a++) {
//                            if (expense.get(a).get_expDate().equals(today)) {
//                                String expType = expense.get(a).get_expType();
//                                if (expType.equals("Food") ||expType.equals("Makanan")|| expType.equals("食品")) {
//                                    itemFood += expense.get(a).get_expAmount();
//
//                                } else if (expType.equals("Transport")||expType.equals("Pengangkutan")|| expType.equals("运输")) {
//                                    itemTransport += expense.get(a).get_expAmount();
//
//                                } else if (expType.equals("Entertainment")|| expType.equals("Hiburan")|| expType.equals("娱乐")) {
//
//                                    itemEntertainment += expense.get(a).get_expAmount();
//
//                                } else if (expType.equals("Communication")||expType.equals("Komunikasi")|| expType.equals("交流")) {
//                                    itemCommunication += expense.get(a).get_expAmount();
//
//                                } else if (expType.equals("Housing")|| expType.equals("Perumahan")|| expType.equals("家用")) {
//                                    itemHousing += expense.get(a).get_expAmount();
//
//                                } else if (expType.equals("Other")||expType.equals("Lain")|| expType.equals("其他")) {
//                                    itemOther += expense.get(a).get_expAmount();
//
//                                }
//                            }
//                        }
//                        break;
//
//                        case 1:
//                            SimpleDateFormat df4 =  new SimpleDateFormat("yyyy-M-dd");
//                            Calendar calendarFirstDay = Calendar.getInstance();
//                            Calendar calendarLastDay = Calendar.getInstance();
//                            calendarFirstDay.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
//                            calendarLastDay.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
//                            displayDate.setText(df4.format(calendarFirstDay.getTime())+" - "+df4.format(calendarLastDay.getTime()));
//
//                            Calendar c2= Calendar.getInstance();
//                            c2.set(Calendar.DAY_OF_WEEK, c2.getFirstDayOfWeek());
//
//                            for (int i = 0; i < 7; i++) {
//                                String week = df4.format(c2.getTime());
//                                c2.add(Calendar.DAY_OF_WEEK, 1);
//                                for (int a = 0; a < expense.size(); a++) {
//                                    if (expense.get(a).get_expDate().equals(week)) {
//                                        String expTypWeek = expense.get(a).get_expType();
//                                        if (expTypWeek.equals("Food")|| expTypWeek.equals("Makanan")|| expTypWeek.equals("食品")) {
//                                            itemFood += expense.get(a).get_expAmount();
//
//                                        } else if (expTypWeek.equals("Transport")|| expTypWeek.equals("Pengangkutan")|| expTypWeek.equals("运输")) {
//                                            itemTransport += expense.get(a).get_expAmount();
//
//                                        } else if (expTypWeek.equals("Entertainment")|| expTypWeek.equals("Hiburan")|| expTypWeek.equals("娱乐")) {
//                                            itemEntertainment += expense.get(a).get_expAmount();
//
//                                        } else if (expTypWeek.equals("Communication")|| expTypWeek.equals("Komunikasi")|| expTypWeek.equals("交流")) {
//                                            itemCommunication += expense.get(a).get_expAmount();
//
//                                        } else if (expTypWeek.equals("Housing")|| expTypWeek.equals("Perumahan")|| expTypWeek.equals("家用")) {
//                                            itemHousing += expense.get(a).get_expAmount();
//
//                                        } else if (expTypWeek.equals("Other")|| expTypWeek.equals("Lain")|| expTypWeek.equals("其他")) {
//                                            itemOther += expense.get(a).get_expAmount();
//
//                                        }
//                                    }
//                                }
//                            }
//                        break;
//
//                        case 2:
//                        SimpleDateFormat df2 = new SimpleDateFormat("M");
//                        SimpleDateFormat df3 = new SimpleDateFormat("yyyy");
//                        SimpleDateFormat df5 = new SimpleDateFormat("MMMM");
//                        Calendar c3 = Calendar.getInstance();
//                        String month = df2.format(c3.getTime());
//                        String year = df3.format(c3.getTime());
//                        String monthWord = df5.format(c3.getTime());
//                        displayDate.setText(monthWord+"-"+year);
//                        for (int a = 0; a < expense.size(); a++) {
//                            if (expense.get(a).get_expDate().substring(0,4).equals(year) && expense.get(a).get_expDate().substring(5,6).equals(month)) {
//                                String expTypeMonth = expense.get(a).get_expType();
//
//                                if (expTypeMonth.equals("Food")|| expTypeMonth.equals("Makanan")|| expTypeMonth.equals("食品")) {
//                                    itemFood += expense.get(a).get_expAmount();
//
//                                } else if (expTypeMonth.equals("Transport")|| expTypeMonth.equals("Pengangkutan")|| expTypeMonth.equals("运输")) {
//                                    itemTransport += expense.get(a).get_expAmount();
//
//                                } else if (expTypeMonth.equals("Entertainment")|| expTypeMonth.equals("Hiburan")|| expTypeMonth.equals("娱乐")) {
//                                    itemEntertainment += expense.get(a).get_expAmount();
//
//                                } else if (expTypeMonth.equals("Communication")|| expTypeMonth.equals("Komunikasi")|| expTypeMonth.equals("交流")){
//                                    itemCommunication += expense.get(a).get_expAmount();
//
//                                } else if (expTypeMonth.equals("Housing")|| expTypeMonth.equals("Perumahan")|| expTypeMonth.equals("家用")) {
//                                    itemHousing += expense.get(a).get_expAmount();
//
//                                } else if (expTypeMonth.equals("Other")||expTypeMonth.equals("Lain")|| expTypeMonth.equals("其他")) {
//                                    itemOther += expense.get(a).get_expAmount();
//
//                                }
//                            }
//                        }
//                        break;
//                    }
//
//                total = itemFood + itemTransport + itemEntertainment + itemCommunication + itemHousing + itemOther;
//                calFood = itemFood / total * 100;
//                calTransport = itemTransport / total * 100;
//                calEntertainment = itemEntertainment / total * 100;
//                calCommunication = itemCommunication / total * 100;
//                calHousing = itemHousing / total * 100;
//                calOther = itemOther / total * 100;
//
//                percentFood.setText(String.format("%.2f", calFood) + "%");
//                percentTransport.setText(String.format("%.2f", calTransport) + "%");
//                percentEntertainment.setText(String.format("%.2f", calEntertainment) + "%");
//                percentCommunication.setText(String.format("%.2f", calCommunication) + "%");
//                percentHousing.setText(String.format("%.2f", calHousing) + "%");
//                percentOther.setText(String.format("%.2f", calOther) + "%");
//
//                amtValue.clear();
//                amtValue.add(itemFood);
//                amtValue.add(itemTransport);
//                amtValue.add(itemEntertainment);
//                amtValue.add(itemCommunication);
//                amtValue.add(itemHousing);
//                amtValue.add(itemOther);
//
//                pane.removeView(dg);
//                dg = new DrawGraph(getActivity(), amtValue);
//                pane.addView(dg);
//            }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        return rootView;
//    }
//
//
//}
