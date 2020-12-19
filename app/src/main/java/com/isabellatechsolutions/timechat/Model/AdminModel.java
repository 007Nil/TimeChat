package com.isabellatechsolutions.timechat.Model;

public class AdminModel {

    private int id;
    private String adminName;
    private String adminEmail;
    private String adminPassword;
    private boolean isValidAdmin;

    public AdminModel() {
    }

    public AdminModel(int id, String adminName, String adminEmail, String adminPassword, boolean isValidAdmin) {
        this.id = id;
        this.adminName = adminName;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
        this.isValidAdmin = isValidAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public boolean isValidAdmin() {
        return isValidAdmin;
    }

    public void setValidAdmin(boolean validAdmin) {
        isValidAdmin = validAdmin;
    }

    @Override
    public String toString() {
        return "AdminModel{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", isValidAdmin=" + isValidAdmin +
                '}';
    }
}
