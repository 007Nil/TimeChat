package com.isabellatechsolutions.timechat.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.isabellatechsolutions.timechat.R;

import java.util.Calendar;

public class RegisterAdminFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_register_admin, container, false);

//        textView_date = view.findViewById(R.id.date_picker);

//        textInputEditText_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//
//                int YEAR = calendar.get(Calendar.YEAR);
//                int MONTH = calendar.get(Calendar.MONTH);
//                int DATE = calendar.get(Calendar.DATE);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(min, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        String dateString = year+ " " + month + " " + dayOfMonth;
//                        textView_date.setText(dateString);
//                    }
//                },YEAR<MONTH,DATE);
//                listener.onInputSendAdminPanel(textInputEditText_date.getText());
//            }
//        });
//        textView_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("HIT");
//            }

//                System.out.println("HIT");
//                CharSequence date_value = textInputEditText_date.getText();
//                listener.onInputSendAdminPanel(date_value);

//        });
        return view;
    }
}
