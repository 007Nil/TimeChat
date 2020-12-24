package com.isabellatechsolutions.timechat.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.isabellatechsolutions.timechat.Adopter.ChatModelAdopter;
import com.isabellatechsolutions.timechat.Database.DatabaseHelper;
import com.isabellatechsolutions.timechat.EditDataActivity;
import com.isabellatechsolutions.timechat.Model.ChatModel;
import com.isabellatechsolutions.timechat.Model.TimeChatModel;
import com.isabellatechsolutions.timechat.R;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HomeFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);

//      Get Dates in Between

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        LocalDate MONDAY = now.with(DayOfWeek.MONDAY);
        LocalDate NEXMONDAYTDAY = now.with(DayOfWeek.SUNDAY).plusDays(1);

        List<String> sevenDaysOfaWeek = getDatesBetweenUsingJava8(MONDAY, NEXMONDAYTDAY);
        DatabaseHelper db = new DatabaseHelper(getActivity());


        for (String eachDate: sevenDaysOfaWeek){
            for (int i = 1; i <=8; i++) {
                boolean result = db.checkIsExistorNot(eachDate);
                if( db.findDateCount(eachDate) < 8) {
                    if (result == false) {
//                    System.out.println("HIT");
                    TimeChatModel timechatModel = new TimeChatModel();
                    timechatModel.setTime(String.valueOf(i));
                    timechatModel.setRegistedDate(eachDate);
                    timechatModel.setEntry_no(i);
                    timechatModel.setAdminID(1);

                    db.addTimes(timechatModel);
                }else{
                        System.out.println("HIT");
                        TimeChatModel timechatModel = new TimeChatModel();
                        timechatModel.setTime(String.valueOf(i));
                        timechatModel.setRegistedDate(eachDate);
                        timechatModel.setEntry_no(i);
                        timechatModel.setAdminID(1);
                        db.addTimes(timechatModel); }
                }
            }
        }



        List<String> distinctDates = db.distinctDates();
        List<TimeChatModel> timeChatModels = db.findAllData();
        ArrayList<ChatModel> chatModel = new ArrayList<>();

        for (String eachDate: distinctDates){
            String argumentString = eachDate+",";
            System.out.println(eachDate);
            int i = 1;
            for (TimeChatModel eachItem : timeChatModels.stream().filter(item -> item.getRegistedDate().equals(eachDate)).collect(Collectors.toList())){

                argumentString += eachItem.getTime()+",";
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

        recyclerView = view.findViewById(R.id.home_recycle_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new ChatModelAdopter(chatModel);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



        return view;
    }

    public static List<String> getDatesBetweenUsingJava8(LocalDate startDate, LocalDate endDate) {

        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i).toString())
                .collect(Collectors.toList());
    }


}
