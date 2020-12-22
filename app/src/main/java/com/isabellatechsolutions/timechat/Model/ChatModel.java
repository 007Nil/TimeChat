package com.isabellatechsolutions.timechat.Model;

public class ChatModel {

    private String chatDate;
    private String chatTime1;
    private String chatTime2;
    private String chatTime3;
    private String chatTime4;
    private String chatTime5;
    private String chatTime6;
    private String chatTime7;
    private String chatTime8;

    public ChatModel(String chatDate, String chatTime1, String chatTime2, String chatTime3, String chatTime4, String chatTime5, String chatTime6, String chatTime7, String chatTime8) {
        this.chatDate = chatDate;
        this.chatTime1 = chatTime1;
        this.chatTime2 = chatTime2;
        this.chatTime3 = chatTime3;
        this.chatTime4 = chatTime4;
        this.chatTime5 = chatTime5;
        this.chatTime6 = chatTime6;
        this.chatTime7 = chatTime7;
        this.chatTime8 = chatTime8;
    }

    public ChatModel(String chatDate, String chatTime1, String chatTime2, String chatTime3, String chatTime4, String chatTime5, String chatTime6, String chatTime7) {
        this.chatDate = chatDate;
        this.chatTime1 = chatTime1;
        this.chatTime2 = chatTime2;
        this.chatTime3 = chatTime3;
        this.chatTime4 = chatTime4;
        this.chatTime5 = chatTime5;
        this.chatTime6 = chatTime6;
        this.chatTime7 = chatTime7;
    }

    public ChatModel(String chatDate, String chatTime1, String chatTime2, String chatTime3, String chatTime4, String chatTime5, String chatTime6) {
        this.chatDate = chatDate;
        this.chatTime1 = chatTime1;
        this.chatTime2 = chatTime2;
        this.chatTime3 = chatTime3;
        this.chatTime4 = chatTime4;
        this.chatTime5 = chatTime5;
        this.chatTime6 = chatTime6;
    }


    public ChatModel(String chatDate, String chatTime1, String chatTime2, String chatTime3, String chatTime4, String chatTime5) {
        this.chatDate = chatDate;
        this.chatTime1 = chatTime1;
        this.chatTime2 = chatTime2;
        this.chatTime3 = chatTime3;
        this.chatTime4 = chatTime4;
        this.chatTime5 = chatTime5;
    }

    public ChatModel(String chatDate, String chatTime1, String chatTime2, String chatTime3, String chatTime4) {
        this.chatDate = chatDate;
        this.chatTime1 = chatTime1;
        this.chatTime2 = chatTime2;
        this.chatTime3 = chatTime3;
        this.chatTime4 = chatTime4;
    }

    public ChatModel(String chatDate, String chatTime1, String chatTime2, String chatTime3) {
        this.chatDate = chatDate;
        this.chatTime1 = chatTime1;
        this.chatTime2 = chatTime2;
        this.chatTime3 = chatTime3;
    }

    public ChatModel(String chatDate, String chatTime1, String chatTime2) {
        this.chatDate = chatDate;
        this.chatTime1 = chatTime1;
        this.chatTime2 = chatTime2;
    }

    public ChatModel(String chatDate, String chatTime1) {
        this.chatDate = chatDate;
        this.chatTime1 = chatTime1;
    }

    public ChatModel(String chatDate) {
        this.chatDate = chatDate;
    }

    public ChatModel() {
    }

    public String getChatDate() {
        return chatDate;
    }

    public void setChatDate(String chatDate) {
        this.chatDate = chatDate;
    }

    public String getChatTime1() {
        return chatTime1;
    }

    public void setChatTime1(String chatTime1) {
        this.chatTime1 = chatTime1;
    }

    public String getChatTime2() {
        return chatTime2;
    }

    public void setChatTime2(String chatTime2) {
        this.chatTime2 = chatTime2;
    }

    public String getChatTime3() {
        return chatTime3;
    }

    public void setChatTime3(String chatTime3) {
        this.chatTime3 = chatTime3;
    }

    public String getChatTime4() {
        return chatTime4;
    }

    public void setChatTime4(String chatTime4) {
        this.chatTime4 = chatTime4;
    }

    public String getChatTime5() {
        return chatTime5;
    }

    public void setChatTime5(String chatTime5) {
        this.chatTime5 = chatTime5;
    }

    public String getChatTime6() {
        return chatTime6;
    }

    public void setChatTime6(String chatTime6) {
        this.chatTime6 = chatTime6;
    }

    public String getChatTime7() {
        return chatTime7;
    }

    public void setChatTime7(String chatTime7) {
        this.chatTime7 = chatTime7;
    }

    public String getChatTime8() {
        return chatTime8;
    }

    public void setChatTime8(String chatTime8) {
        this.chatTime8 = chatTime8;
    }

    @Override
    public String toString() {
        return "ChatModel{" +
                "chatDate='" + chatDate + '\'' +
                ", chatTime1='" + chatTime1 + '\'' +
                ", chatTime2='" + chatTime2 + '\'' +
                ", chatTime3='" + chatTime3 + '\'' +
                ", chatTime4='" + chatTime4 + '\'' +
                ", chatTime5='" + chatTime5 + '\'' +
                ", chatTime6='" + chatTime6 + '\'' +
                ", chatTime7='" + chatTime7 + '\'' +
                ", chatTime8='" + chatTime8 + '\'' +
                '}';
    }
}
