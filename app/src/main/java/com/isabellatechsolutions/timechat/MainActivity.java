package com.isabellatechsolutions.timechat;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.isabellatechsolutions.timechat.Adopter.ChatModelAdopter;
import com.isabellatechsolutions.timechat.Database.DatabaseHelper;
import com.isabellatechsolutions.timechat.Fragments.AdminPanelFragment;
import com.isabellatechsolutions.timechat.Fragments.HomeFragment;
import com.isabellatechsolutions.timechat.Fragments.LoginFragment;
import com.isabellatechsolutions.timechat.Fragments.RegisterAdminFragment;
import com.isabellatechsolutions.timechat.Model.AdminModel;
import com.isabellatechsolutions.timechat.Model.TimeChatModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LoginFragment.LoginFragmentListener
    , AdminPanelFragment.AdminPanelFragmentationListener
{


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private LoginFragment loginFragment;
    private DatabaseHelper databaseHelper;
    private AdminPanelFragment adminPanelFragment;


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String ID = "id";

    private String text_email;
    private String text_passwd;
    private int id = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        removeSharedPrefrenceData();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                R.string.navigation_draw_open,R.string.navigation_draw_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }

        AdminModel adminModel = new AdminModel(1,"Test","test@gmail.com","1234567890#",true);
//        Database Opertaions

        databaseHelper = new DatabaseHelper(MainActivity.this);
        databaseHelper.addAdmin(adminModel);

//        final ChatModelAdopter chatModelAdopter = new ChatModelAdopter();
//
//        chatModelAdopter.setOnClickListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.login :
                removeSharedPrefrenceData();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LoginFragment()).commit();
                navigationView.setCheckedItem(R.id.login);
                break;
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.admin_panel:
                saveId(R.id.admin_panel);
                loadData();
                if (text_email.equals("") || text_passwd.equals("")) {
                    Toast.makeText(MainActivity.this, "You need to login as Admin", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new LoginFragment()).commit();
                    navigationView.setCheckedItem(R.id.login);
                }else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new AdminPanelFragment()).commit();
                }
//                Toast.makeText(MainActivity.this, text_email,Toast.LENGTH_SHORT).show();
                break;
            case R.id.register:
                saveId(R.id.register);
                loadData();
                if (text_email.equals("") || text_passwd.equals("") || text_passwd.equals("password") || text_email.equals("email")) {
                    Toast.makeText(MainActivity.this, "You need to login as Admin", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new LoginFragment()).commit();
                    navigationView.setCheckedItem(R.id.login);
                }else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new RegisterAdminFragment()).commit();
                }
                break;
            case R.id.logout:
                loadData();
                if (text_email.equals("") || text_passwd.equals("") || text_passwd.equals("password") || text_email.equals("email")) {
                    Toast.makeText(MainActivity.this, "You Are Not Logged in", Toast.LENGTH_SHORT).show();
                }else {
                    removeSharedPrefrenceData();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new HomeFragment()).commit();
                    navigationView.setCheckedItem(R.id.home);
                    Toast.makeText(MainActivity.this, "Logout Successful !!", Toast.LENGTH_SHORT).show();
                }

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onInputSent(CharSequence input_emailID, CharSequence input_password) {
        if (databaseHelper.checkAdminExistOrNot(input_emailID.toString(),input_password.toString()) == true){

            saveData(input_emailID.toString(),input_password.toString());
            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            loadID();
            if (id == R.id.admin_panel) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AdminPanelFragment()).commit();
                navigationView.setCheckedItem(R.id.admin_panel);
            }else if (id == R.id.register){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RegisterAdminFragment()).commit();
                navigationView.setCheckedItem(R.id.register);
            }else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AdminPanelFragment()).commit();
                navigationView.setCheckedItem(R.id.admin_panel);
            }

        }else{
            Toast.makeText(MainActivity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveData(String email, String passwd){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(EMAIL, email);
        editor.putString(PASSWORD, passwd);

        editor.apply();

    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        text_email = sharedPreferences.getString(EMAIL, "");
        text_passwd = sharedPreferences.getString(PASSWORD, "");
    }

    public void saveId (int id){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("ID", id);
        editor.apply();

    }

    public void loadID(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        id = sharedPreferences.getInt("ID", 0);
    }

    public void removeSharedPrefrenceData(){
        try {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(EMAIL);
            editor.remove(PASSWORD);

            editor.commit();
            editor.clear();
        }catch (Exception e){
            Log.i("EXCEPTION", e.toString());
        }
    }

    @Override
    public void onInputSendAdminPanel(CharSequence charSequenceDate, CharSequence charSequenceTime) {
//        System.out.println(databaseHelper.findDateCount(charSequenceDate.toString()));
        if (databaseHelper.findDateCount(charSequenceDate.toString()) <= 8) {
            TimeChatModel timeChatModel = new TimeChatModel();
            try {
                timeChatModel.setTime(charSequenceTime.toString());
//            timeChatModel.setCreatedAt(date);
                timeChatModel.setAdminID(1);
                timeChatModel.setRegistedDate(charSequenceDate.toString());
            } catch (Exception e) {
                System.out.println(e);
            }

//        System.out.println(timeChatModel.getCreatedAt().toString());

            boolean result = databaseHelper.addTimes(timeChatModel);

            if (result == true) {
                List<TimeChatModel> timeChaTData = databaseHelper.findAllData();
            Toast.makeText(MainActivity.this, "Successful !!", Toast.LENGTH_SHORT).show();
                adminPanelFragment = new AdminPanelFragment();
                adminPanelFragment.clearTextField();


            } else {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(MainActivity.this, "8 entries are done for "+charSequenceDate.toString(), Toast.LENGTH_LONG).show();
        }

    }

//    @Override
//    public void onItemClick() {
//
//    }
}