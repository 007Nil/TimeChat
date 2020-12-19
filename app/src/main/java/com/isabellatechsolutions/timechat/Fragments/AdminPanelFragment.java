package com.isabellatechsolutions.timechat.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.isabellatechsolutions.timechat.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class AdminPanelFragment extends Fragment {

    private TextView textView_date;
    private TextView textView_time;
    private Button saveDataBtn;

    private AdminPanelFragmentationListener adminPanelFragmentationListener;

    public AdminPanelFragment(){

    }
    public interface AdminPanelFragmentationListener{
        void onInputSendAdminPanel(CharSequence charSequencedate);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_admin_panel, container, false);

        textView_date = view.findViewById(R.id.date_picker);
        textView_time = view.findViewById(R.id.time_picker);
        saveDataBtn = view.findViewById(R.id.save_data);
//        textView_time.setText("22");

        textView_date.setOnClickListener(new DateClickListener());
        textView_time.setOnClickListener(new TimeClickListener());

        saveDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence charSequenceDate = textView_date.getText();
                System.out.println(textView_date.getText().toString());
                adminPanelFragmentationListener.onInputSendAdminPanel(charSequenceDate);
//                System.out.println("TIME");
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
        @Override
        public void onDateSet(final DatePicker view, final int year, final int month, final int dayOfMonth) {
//            System.out.println("HIT3");
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
//            System.out.println(currentDate);
            textView_date.setText(currentDate);
        }
    };

    private TimePickerDialog.OnTimeSetListener onTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(final TimePicker view,final int hourOfDay,final int minute) {
//            textView_time.setText("22");
            System.out.println(hourOfDay);
            System.out.println(minute);
            System.out.println("HIT");
        }
    };

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
}
