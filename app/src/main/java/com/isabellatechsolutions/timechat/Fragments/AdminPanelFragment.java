package com.isabellatechsolutions.timechat.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.isabellatechsolutions.timechat.Adopter.ChatModelAdopter;
import com.isabellatechsolutions.timechat.Database.DatabaseHelper;
import com.isabellatechsolutions.timechat.Model.ChatModel;
import com.isabellatechsolutions.timechat.Model.TimeChatModel;
import com.isabellatechsolutions.timechat.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AdminPanelFragment extends Fragment {

    private TextView textView_date;
    private TextView textView_time;
    private Button saveDataBtn;

    private Button get_date_btn;
    private Button get_time_btn;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private AdminPanelFragmentationListener adminPanelFragmentationListener;

    public AdminPanelFragment(){

    }
    public interface AdminPanelFragmentationListener{
        void onInputSendAdminPanel(CharSequence charSequenceDate, CharSequence charSequenceTime);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_admin_panel, container, false);

        textView_date = view.findViewById(R.id.date_picker);
        textView_time = view.findViewById(R.id.time_picker);
        saveDataBtn = view.findViewById(R.id.save_data);
//        textView_date.setText("2020-12-20");
//        textView_time.setText("8:22 AM");

        get_date_btn = view.findViewById(R.id.get_date);
        get_time_btn = view.findViewById(R.id.get_time);

        get_date_btn.setOnClickListener(new DateClickListener());
        get_time_btn.setOnClickListener(new TimeClickListener());

        DatabaseHelper db = new DatabaseHelper(getActivity());
        List<String> distinctDates = db.distinctDates();
        List<TimeChatModel> timeChatModels = db.findAllData();
        ArrayList<ChatModel> chatModel = new ArrayList<ChatModel>();

        for (String eachDate: distinctDates){
            String argumentString = eachDate+",";
            int i = 1;
//            System.out.println(eachDate);
            for (TimeChatModel eachItem : timeChatModels.stream().filter(item -> item.getRegistedDate().equals(eachDate)).collect(Collectors.toList())){
                argumentString += String.valueOf(i)+" Time:"+eachItem.getTime()+",";
                i++;
            }

            argumentString = argumentString.substring(0, argumentString.length() - 1);

            String[] parts = argumentString.split(",");
            String modelDate = parts[0];
            String time1 = "";
            String time2 = "";
            String time3 = "";
            String time4 = "";
            String time5 = "";
            String time6 = "";
            String time7 = "";
            String time8 = "";

            try{
                time1 = parts[1];
            }catch (Exception e){
                time1 = null;
            }
            try {
                time2 = parts[2];
            }catch (Exception e){
                time2 = null;
            }
            try {
                time3 = parts[3];
            }catch (Exception e){
                time3 = null;
            }
            try {
                time4 = parts[4];
            }catch (Exception e){
                time4 = null;
            }
            try {
                time5 = parts[5];
            }catch (Exception e){
                time5 = null;
            }
            try {
                time6 = parts[6];
            }catch (Exception e){
                time6 = null;
            }
            try {
                time7 = parts[7];
            }catch (Exception e){
                time7 = null;
            }
            try {
                time8 = parts[8];
            }catch (Exception e){
                time8 = null;
            }

            chatModel.add(new ChatModel(modelDate,time1,time2,time3,time4,time5,time6,time7,time8));

        }

//        chatModel.add(new ChatModel("2020-12-20", "time1", "time1", "time1", "time1", "time1", "time1", "time1"));
//        chatModel.add(new ChatModel("2020-12-22", "time1", "time1", "time1", "time1", "time1", "time1", "time1", "time1"));

        recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new ChatModelAdopter(chatModel);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

//        listView = view.findViewById(R.id.admin_listview);
//        DatabaseHelper db = new DatabaseHelper(getActivity());
////        db.findAllData();
//        arrayAdapter = new ArrayAdapter<TimeChatModel>(getActivity(),android.R.layout.simple_list_item_1, db.findAllData());
//        listView.setAdapter(arrayAdapter);

        saveDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence charSequenceDate = textView_date.getText();
                CharSequence charSequenceTime = textView_time.getText();
//                System.out.println(textView_date.getText().toString());

                adminPanelFragmentationListener.onInputSendAdminPanel(charSequenceDate, charSequenceTime);
                textView_date.setText("");
                textView_time.setText("");


                DatabaseHelper db = new DatabaseHelper(getActivity());
                List<String> distinctDates = db.distinctDates();
                List<TimeChatModel> timeChatModels = db.findAllData();
                ArrayList<ChatModel> chatModel = new ArrayList<ChatModel>();

                for (String eachDate: distinctDates){
                    String argumentString = eachDate+",";
                    int i = 1;
//            System.out.println(eachDate);
                    for (TimeChatModel eachItem : timeChatModels.stream().filter(item -> item.getRegistedDate().equals(eachDate)).collect(Collectors.toList())){
                        argumentString += String.valueOf(i)+"Time:"+eachItem.getTime()+",";
                        i++;
                    }

                    argumentString = argumentString.substring(0, argumentString.length() - 1);

                    String[] parts = argumentString.split(",");
                    String modelDate = parts[0];
                    String time1 = "";
                    String time2 = "";
                    String time3 = "";
                    String time4 = "";
                    String time5 = "";
                    String time6 = "";
                    String time7 = "";
                    String time8 = "";

                    try{
                        time1 = parts[1];
                    }catch (Exception e){
                        time1 = null;
                    }
                    try {
                        time2 = parts[2];
                    }catch (Exception e){
                        time2 = null;
                    }
                    try {
                        time3 = parts[3];
                    }catch (Exception e){
                        time3 = null;
                    }
                    try {
                        time4 = parts[4];
                    }catch (Exception e){
                        time4 = null;
                    }
                    try {
                        time5 = parts[5];
                    }catch (Exception e){
                        time5 = null;
                    }
                    try {
                        time6 = parts[6];
                    }catch (Exception e){
                        time6 = null;
                    }
                    try {
                        time7 = parts[7];
                    }catch (Exception e){
                        time7 = null;
                    }
                    try {
                        time8 = parts[8];
                    }catch (Exception e){
                        time8 = null;
                    }

                    chatModel.add(new ChatModel(modelDate,time1,time2,time3,time4,time5,time6,time7,time8));

                }

                recyclerView = view.findViewById(R.id.recycle_view);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getActivity());
                adapter = new ChatModelAdopter(chatModel);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragment.LoginFragmentListener){
            adminPanelFragmentationListener = (AdminPanelFragment.AdminPanelFragmentationListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement AdminPanelFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        adminPanelFragmentationListener = null;
    }

    private DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onDateSet(final DatePicker view, final int year, final int month, final int dayOfMonth) {
//            System.out.println("HIT3");
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

            String currentDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
            String[] parts = currentDate.split(",");
            String monthDate = parts[0];
            String selectedYear = parts[1].replace(" ","");

//            Split monthDate to month and date

            String[] parts2 = monthDate.split(" ");
            String selectedMonth = getMonthNumaric(parts2[0]);
            String selectedDate = parts2[1];

            String selectedValue = selectedYear+"-"+selectedMonth+"-"+selectedDate;
//            String currentDate = format.format((TemporalAccessor) c.getTime());
//            System.out.println(currentDate);
            textView_date.setText(selectedValue);
        }
    };


    private TimePickerDialog.OnTimeSetListener onTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view,final int hourOfDay,final int minute) {
            String stringhour = "";
            String stringMinute = "";
            if (hourOfDay == 0 || hourOfDay == 1 || hourOfDay == 2 || hourOfDay == 3 || hourOfDay == 4
                    || hourOfDay == 5 || hourOfDay == 6 || hourOfDay == 7 || hourOfDay == 8 || hourOfDay == 9){
                stringhour = "0"+String.valueOf(hourOfDay);
            }else{
                stringhour = String.valueOf(hourOfDay);
            }

            if (minute == 0){
                stringMinute = "0"+String.valueOf(minute);
            }else{
                stringMinute = String.valueOf(minute);
            }
//            System.out.println(stringhour+":"+String.valueOf(minute)+":00");
//            System.out.println("HIT")
            textView_time.setText(convert12(stringhour+":"+stringMinute));
        }
    };

    public String convert12(String str)
    {
// Get Hours
        System.out.println(str);
        int h1 = (int)str.charAt(0) - '0';
        int h2 = (int)str.charAt(1)- '0';


        String minutes = String.valueOf(str.charAt(3))+String.valueOf(str.charAt(4));
        String hour = "";

//        System.out.println(str.charAt(3));
//        System.out.println(str.charAt(4));

        int hh = h1 * 10 + h2;

        // Finding out the Meridien of time
        // ie. AM or PM
        String Meridien;
        if (hh < 12) {
            Meridien = "AM";
        }
        else
            Meridien = "PM";

        hh %= 12;

        // Handle 00 and 12 case separately
        if (hh == 0) {
            hour = "12";
        }
        else {
            hour = String.valueOf(hh);
        }

        return  hour+":"+minutes+" "+Meridien;
    }


    public String getMonthNumaric(String month){
        String[] months = {"Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"};
        String returnString = "0";

//        for (String eachMonth: months){
//            if (eachMonth.equals(month)){
//                returnString = String.valueOf(ArrayUtiles)
//            }
//        }

        for (int i = 1; i <= months.length; i++ ){
            if (months[i-1].equals(month)){
                returnString = String.valueOf(i);
                break;
            }
        }
        return returnString;
    }

    private class DateClickListener implements View.OnClickListener {
        @Override
        public void onClick(final View v) {
            DatePickerFragment dpf = new DatePickerFragment().newInstance();
            dpf.setCallBack(onDate);
            dpf.show(getFragmentManager().beginTransaction(),"DatePickerFragment");
        }
    }

    private class TimeClickListener implements View.OnClickListener{

        @Override
        public void onClick(final View v) {
            TimePickerFragment tpf = new TimePickerFragment().newInstance();
            tpf.setCallBack(onTime);
            tpf.show(getFragmentManager().beginTransaction(),"TimePickerFragment");
        }
    }

    public void clearTextField(){
        System.out.println("HIT");
    }
}
