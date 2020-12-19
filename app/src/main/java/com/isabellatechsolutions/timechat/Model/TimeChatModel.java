package com.isabellatechsolutions.timechat.Model;

import android.content.Intent;

import java.util.Date;

public class TimeChatModel {

    private Intent id;
    private String time;
    private Date createdAt;
    private Integer adminID;


    public TimeChatModel() {
    }

    public TimeChatModel(Intent id, String time, Date createdAt, Integer adminID) {
        this.id = id;
        this.time = time;
        this.createdAt = createdAt;
        this.adminID = adminID;
    }

    public Intent getId() {
        return id;
    }

    public void setId(Intent id) {
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

    @Override
    public String toString() {
        return "TimeChatModel{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", adminID=" + adminID +
                '}';
    }
}
