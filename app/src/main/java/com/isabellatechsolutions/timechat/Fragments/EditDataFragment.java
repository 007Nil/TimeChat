package com.isabellatechsolutions.timechat.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.isabellatechsolutions.timechat.Database.DatabaseHelper;
import com.isabellatechsolutions.timechat.EditDataActivity;
import com.isabellatechsolutions.timechat.MainActivity;
import com.isabellatechsolutions.timechat.R;


public class EditDataFragment extends Fragment {

    private TextInputEditText text1;
    private TextInputEditText text2;
    private TextInputEditText text3;

    private Button saveButton;

    String oneDigit;
    String twoDigit;
    String threeDigit;
    int entryNo;
    String date;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        oneDigit = bundle.getString("oneDigit");
        twoDigit = bundle.getString("twoDigit");
        threeDigit = bundle.getString("threeDigit");
        entryNo = bundle.getInt("entryNo");
        date = bundle.getString("DATE");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_editdata, container, false);

        text1 = view.findViewById(R.id.value1);
        text2 = view.findViewById(R.id.value2);
        text3 = view.findViewById(R.id.value3);

        saveButton = view.findViewById(R.id.saveValues);



        text1.setText(oneDigit);
        text2.setText(twoDigit);
        text3.setText(threeDigit);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_value1 = text1.getText().toString();
                String txt_value2 = text2.getText().toString();
                String txt_value3 = text3.getText().toString();

                DatabaseHelper db = new DatabaseHelper(getActivity());
                boolean result = db.updateChatTable(date,txt_value1,txt_value2,txt_value3,entryNo);

                if (result == true){

                    Toast.makeText(getActivity(), "Success",Toast.LENGTH_SHORT).show();
                    Fragment fragment = new AdminPanelFragment();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new AdminPanelFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
//                    Toast.makeText(EditDataActivity.this, "Data Update Successfully",Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(getActivity(), "Error",Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }
}