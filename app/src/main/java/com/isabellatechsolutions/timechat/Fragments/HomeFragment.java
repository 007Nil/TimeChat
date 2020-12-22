package com.isabellatechsolutions.timechat.Fragments;

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
import com.isabellatechsolutions.timechat.Model.ChatModel;
import com.isabellatechsolutions.timechat.Model.TimeChatModel;
import com.isabellatechsolutions.timechat.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);


        DatabaseHelper db = new DatabaseHelper(getActivity());
        List<String> distinctDates = db.distinctDates();
        List<TimeChatModel> timeChatModels = db.findAllData();
        ArrayList<ChatModel> chatModel = new ArrayList<>();

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

        recyclerView = view.findViewById(R.id.home_recycle_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new ChatModelAdopter(chatModel);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return view;
    }
}
