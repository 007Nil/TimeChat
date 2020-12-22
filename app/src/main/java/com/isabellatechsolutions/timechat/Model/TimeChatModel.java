package com.isabellatechsolutions.timechat.Model;

import android.content.Intent;

import java.util.Date;

public class TimeChatModel {

    private int id;
    private String time;
    private Date createdAt;
    private Integer adminID;
    private String registedDate;


    public TimeChatModel() {
    }

    public TimeChatModel(int id, String time, Date createdAt, Integer adminID, String registedDate) {
        this.id = id;
        this.time = time;
        this.createdAt = createdAt;
        this.adminID = adminID;
        this.registedDate = registedDate;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    @Override
    public String toString() {
        return "TimeChatModel{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", createdAt=" + createdAt +
                ", adminID=" + adminID +
                ", registedDate='" + registedDate + '\'' +
                '}';
    }
}
