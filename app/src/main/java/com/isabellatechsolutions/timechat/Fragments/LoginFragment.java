package com.isabellatechsolutions.timechat.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.isabellatechsolutions.timechat.Database.DatabaseHelper;
import com.isabellatechsolutions.timechat.MainActivity;
import com.isabellatechsolutions.timechat.R;

public class LoginFragment extends Fragment {

    private Button button;
    private TextInputEditText emailId;
    private TextInputEditText password;

    private LoginFragmentListener listener;

    public interface LoginFragmentListener {
        void onInputSent (CharSequence input_email, CharSequence input_password);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_login, container, false);

        button = (Button) view.findViewById(R.id.login_btn);

        emailId = view.findViewById(R.id.email_id);
        password = view.findViewById(R.id.password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(getActivity());
                CharSequence email_value = emailId.getText();
                CharSequence password_value = password.getText();
                listener.onInputSent(email_value, password_value);
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragmentListener){
            listener = (LoginFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
            + "must implement LoginFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}