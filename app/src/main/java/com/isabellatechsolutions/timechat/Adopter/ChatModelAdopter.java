package com.isabellatechsolutions.timechat.Adopter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isabellatechsolutions.timechat.Model.ChatModel;
import com.isabellatechsolutions.timechat.R;

import java.util.ArrayList;

public class ChatModelAdopter extends RecyclerView.Adapter<ChatModelAdopter.ChatModelAdopterViewHolder> {

    private ArrayList<ChatModel> myChatModels;

   public static class ChatModelAdopterViewHolder extends RecyclerView.ViewHolder{

       public TextView textViewDate;
       public TextView textViewTime1;
       public TextView textViewTime2;
       public TextView textViewTime3;
       public TextView textViewTime4;
       public TextView textViewTime5;
       public TextView textViewTime6;
       public TextView textViewTime7;
       public TextView textViewTime8;

       public ChatModelAdopterViewHolder(@NonNull View itemView) {
           super(itemView);
           textViewDate = itemView.findViewById(R.id.date_timechat);
           textViewTime1 = itemView.findViewById(R.id.chatTime1);
           textViewTime2 = itemView.findViewById(R.id.chatTime2);
           textViewTime3 = itemView.findViewById(R.id.chatTime3);
           textViewTime4 = itemView.findViewById(R.id.chatTime4);
           textViewTime5 = itemView.findViewById(R.id.chatTime5);
           textViewTime6 = itemView.findViewById(R.id.chatTime6);
           textViewTime7 = itemView.findViewById(R.id.chatTime7);
           textViewTime8 = itemView.findViewById(R.id.chatTime8);

       }
   }


   public ChatModelAdopter(ArrayList<ChatModel> chatModels) {
       myChatModels = chatModels;
   }

    @NonNull
    @Override
    public ChatModelAdopterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.timechat_item,parent,false);
        ChatModelAdopterViewHolder chatModelAdopterViewHolder = new ChatModelAdopterViewHolder(v);
        return chatModelAdopterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatModelAdopterViewHolder holder, int position) {
        ChatModel currentItem = myChatModels.get(position);

        if (currentItem.getChatDate() != null) {
            holder.textViewDate.setText(currentItem.getChatDate());
        }
        holder.textViewTime1.setText(currentItem.getChatTime1());
        holder.textViewTime2.setText(currentItem.getChatTime2());
        holder.textViewTime3.setText(currentItem.getChatTime3());
        holder.textViewTime4.setText(currentItem.getChatTime4());
        holder.textViewTime5.setText(currentItem.getChatTime5());
        holder.textViewTime6.setText(currentItem.getChatTime6());
        holder.textViewTime7.setText(currentItem.getChatTime7());
        holder.textViewTime8.setText(currentItem.getChatTime8());
    }

    @Override
    public int getItemCount() {
        return myChatModels.size();
    }
}
