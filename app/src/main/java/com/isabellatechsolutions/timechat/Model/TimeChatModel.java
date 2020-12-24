package com.isabellatechsolutions.timechat.Model;

import android.content.Intent;

import java.util.Date;

public class TimeChatModel {

    private int id;
    private String time;
    private Integer adminID;
    private String registedDate;
    private int entry_no;

    public TimeChatModel() {
    }

    public TimeChatModel(int id, String time, Integer adminID, String registedDate, int entry_no) {
        this.id = id;
        this.time = time;
        this.adminID = adminID;
        this.registedDate = registedDate;
        this.entry_no = entry_no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }

    public String getRegistedDate() {
        return registedDate;
    }

    public void setRegistedDate(String registedDate) {
        this.registedDate = registedDate;
    }

    public int getEntry_no() {
        return entry_no;
    }

    public void setEntry_no(int entry_no) {
        this.entry_no = entry_no;
    }

    @Override
    public String toString() {
        return "TimeChatModel{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", adminID=" + adminID +
                ", registedDate='" + registedDate + '\'' +
                ", entry_no=" + entry_no +
                '}';
    }
}

