package com.isabellatechsolutions.timechat.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isabellatechsolutions.timechat.Model.ChatModel;
import com.isabellatechsolutions.timechat.R;

import java.util.ArrayList;

public class ChatModelAdopter extends RecyclerView.Adapter<ChatModelAdopter.ChatModelAdopterViewHolder> {

    private ArrayList<ChatModel> myChatModels;
    private static OnItemClickListener listener = null;


    public ChatModelAdopter() {
    }
    public ChatModelAdopter(ArrayList<ChatModel> myChatModels) {
        this.myChatModels = myChatModels;
    }

    public interface OnItemClickListener {
        void onItemClick(CharSequence time, CharSequence date);
    }
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

       public ChatModelAdopterViewHolder(View itemView) {
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


           textViewTime1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   System.out.println("CLICK");
                   if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                       listener.onItemClick(textViewTime1.getText(), textViewDate.getText());
                   }else{
                       System.out.println(listener);
                   }
               }
           });

           textViewTime2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   System.out.println("CLICK");
                   if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                       listener.onItemClick(textViewTime2.getText(), textViewDate.getText());
                   }else{
                       System.out.println(listener);
                   }
               }
           });

           textViewTime3.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   System.out.println("CLICK");
                   if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                       listener.onItemClick(textViewTime3.getText(), textViewDate.getText());
                   }else{
                       System.out.println(listener);
                   }
               }
           });

           textViewTime4.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   System.out.println("CLICK");
                   if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                       listener.onItemClick(textViewTime4.getText(), textViewDate.getText());
                   }else{
                       System.out.println(listener);
                   }
               }
           });

           textViewTime5.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   System.out.println("CLICK");
                   if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                       listener.onItemClick(textViewTime5.getText(), textViewDate.getText());
                   }else{
                       System.out.println(listener);
                   }
               }
           });

           textViewTime6.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   System.out.println("CLICK");
                   if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                       listener.onItemClick(textViewTime6.getText(), textViewDate.getText());
                   }else{
                       System.out.println(listener);
                   }
               }
           });

           textViewTime7.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   System.out.println("CLICK");
                   if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                       listener.onItemClick(textViewTime7.getText(), textViewDate.getText());
                   }else{
                       System.out.println(listener);
                   }
               }
           });

           textViewTime8.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   System.out.println("CLICK");
                   if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                       listener.onItemClick(textViewTime8.getText(), textViewDate.getText());
                   }else{
                       System.out.println(listener);
                   }
               }
           });
       }
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


        holder.textViewDate.setText(currentItem.getChatDate());
        System.out.println(currentItem.getChatTime1());
        if (currentItem.getChatTime1().equals("1") || currentItem.getChatTime1() == null) {
            holder.textViewTime1.setText("[ 1 Time: {#?} {#??} {#???} ]");
        }else if (currentItem.getChatTime1().contains("Entry1")){
            String time = currentItem.getChatTime1();
            String[] parts = time.split("-");
            holder.textViewTime1.setText("[ 1 Time: {#"+parts[1]+"} {#"+parts[2]+"} {#"+parts[3]+"} ]");
        }


        if (currentItem.getChatTime2().equals("2") || currentItem.getChatTime2() == null) {
            holder.textViewTime2.setText("[ 2 Time: {#?} {#??} {#???} ]");
        }else if (currentItem.getChatTime2().contains("Entry2")){
            String[] parts = getRequriedData(currentItem.getChatTime2());
            holder.textViewTime2.setText("[ 2 Time: {#"+parts[1]+"} {#"+parts[2]+"} {#"+parts[3]+"} ]");
        }


        if (currentItem.getChatTime3().equals("3") || currentItem.getChatTime3() == null){
            holder.textViewTime3.setText("[ 3 Time: {#?} {#??} {#???} ]");
        }else if (currentItem.getChatTime3().contains("Entry3")){
            String[] parts = getRequriedData(currentItem.getChatTime3());
            holder.textViewTime3.setText("[ 3 Time: {#"+parts[1]+"} {#"+parts[2]+"} {#"+parts[3]+"} ]");
        }


        if (currentItem.getChatTime4().equals("4") || currentItem.getChatTime4() == null){
            holder.textViewTime4.setText("[ 4 Time: {#?} {#??} {#???} ]");
        }else if (currentItem.getChatTime4().contains("Entry4")){
            String[] parts = getRequriedData(currentItem.getChatTime4());
            holder.textViewTime4.setText("[ 4 Time: {#"+parts[1]+"} {#"+parts[2]+"} {#"+parts[3]+"} ]");
        }


        if (currentItem.getChatTime5().equals("5") || currentItem.getChatTime5() == null){
            holder.textViewTime5.setText("[ 5 Time: {#?} {#??} {#???} ]");
        }else if (currentItem.getChatTime5().contains("Entry5")){
            String[] parts = getRequriedData(currentItem.getChatTime5());
            holder.textViewTime5.setText("[ 5 Time: {#"+parts[1]+"} {#"+parts[2]+"} {#"+parts[3]+"} ]");
        }


        if (currentItem.getChatTime6().equals("6") || currentItem.getChatTime6() == null){
            holder.textViewTime6.setText("[ 6 Time: {#?} {#??} {#???} ]");
        }else if (currentItem.getChatTime6().contains("Entry6")){
            String[] parts = getRequriedData(currentItem.getChatTime6());
            holder.textViewTime6.setText("[ 6 Time: {#"+parts[1]+"} {#"+parts[2]+"} {#"+parts[3]+"} ]");
        }


        if (currentItem.getChatTime7().equals("7") || currentItem.getChatTime7() == null){
            holder.textViewTime7.setText("[ 7 Time: {#?} {#??} {#???} ]");
        }else if (currentItem.getChatTime7().contains("Entry7")){
            String[] parts = getRequriedData(currentItem.getChatTime7());
            holder.textViewTime7.setText("[ 7 Time: {#"+parts[1]+"} {#"+parts[2]+"} {#"+parts[3]+"} ]");
        }


        if (currentItem.getChatTime8().equals("8") || currentItem.getChatTime8() == null){
            holder.textViewTime8.setText("[ 8 Time: {#?} {#??} {#???} ]");
        }else if (currentItem.getChatTime8().contains("Entry8")){
            String[] parts = getRequriedData(currentItem.getChatTime8());
            holder.textViewTime8.setText("[ 8 Time: {#"+parts[1]+"} {#"+parts[2]+"} {#"+parts[3]+"} ]");
        }
    }

    @Override
    public int getItemCount() {
        return myChatModels.size();
    }

    public void setOnClickListener(OnItemClickListener listener){
//        System.out.println("sssssssss"+listener);
        this.listener = listener;
    }

    public String[] getRequriedData(String time){
        return time.split("-");
    }

}
