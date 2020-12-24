package com.isabellatechsolutions.timechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.isabellatechsolutions.timechat.Database.DatabaseHelper;
import com.isabellatechsolutions.timechat.Fragments.AdminPanelFragment;

public class EditDataActivity extends AppCompatActivity {

    private TextInputEditText text1;
    private TextInputEditText text2;
    private TextInputEditText text3;

    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdata);

        Bundle bundle = getIntent().getExtras();

        String oneDigit = bundle.getString("oneDigit");
        String twoDigit = bundle.getString("twoDigit");
        String threeDigit = bundle.getString("threeDigit");
        int entryNo = bundle.getInt("entryNo");
        String date = bundle.getString("DATE");

        text1 = findViewById(R.id.value1);
        text2 = findViewById(R.id.value2);
        text3 = findViewById(R.id.value3);



        text1.setText(oneDigit);
        text2.setText(twoDigit);
        text3.setText(threeDigit);

        saveButton = findViewById(R.id.saveValues);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_value1 = text1.getText().toString();
                String txt_value2 = text2.getText().toString();
                String txt_value3 = text3.getText().toString();

                DatabaseHelper db = new DatabaseHelper(EditDataActivity.this);
                boolean result = db.updateChatTable(date,txt_value1,txt_value2,txt_value3,entryNo);

                if (result == true){
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", "test@gmail.com");
                    editor.putString("password", "1234567890#");
                    editor.commit();
                    Toast.makeText(EditDataActivity.this, "Success",Toast.LENGTH_SHORT).show();
                    Fragment fragment = new AdminPanelFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.admin_panel, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    Toast.makeText(EditDataActivity.this, "Data Update Successfully",Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(EditDataActivity.this, "Error",Toast.LENGTH_SHORT).show();
                }

            }
        });

//        Toast.makeText(this, entryNo+oneDigit+twoDigit+threeDigit,Toast.LENGTH_LONG).show();
    }
}