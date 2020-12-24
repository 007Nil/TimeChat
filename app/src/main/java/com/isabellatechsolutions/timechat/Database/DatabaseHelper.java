package com.isabellatechsolutions.timechat.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.isabellatechsolutions.timechat.Model.AdminModel;
import com.isabellatechsolutions.timechat.Model.TimeChatModel;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String ADMIN_TABLE_NAME = "ADMIN_TABLE";
    public static final String ADMIN_ID_COL = "ID";
    public static final String ADMIN_NAME_COL = "ADMIN_NAME";
    public static final String ADMIN_EMAIL_COL = "ADMIN_EMAIL_ID";
    public static final String ADMIN_PASSWORD = "ADMIN_PASSWORD";
    public static final String ADMIN_VALID_COL = "IS_VALID_ADMIN";

    public static final String TIMECHAT_TABLE_NAME = "TIMECHAT_TABLE";
    public static final String ID_COL = "ID";
    public static final String TIME_COL1 = "TIME1";
    public static final String ENTRY_NO = "ENTRY_NO";

    public static final String CREATED_AT_COL = "CREATED_AT";
    public static final String ADMIN_ID_FOR_COL = "ADMIN_ID";

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate now = LocalDate.now();
    LocalDate MONDAY = now.with(DayOfWeek.MONDAY);
    LocalDate SUNDAY = now.with(DayOfWeek.SUNDAY);

    public DatabaseHelper(@Nullable Context context) {
        super(context, "timechat.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createAdminTableStatement = "CREATE TABLE " + ADMIN_TABLE_NAME +"("+ADMIN_ID_COL+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ADMIN_NAME_COL + " TEXT NOT NULL ,"+ ADMIN_EMAIL_COL + " TEXT NOT NULL,"+ ADMIN_PASSWORD + " TEXT NOT NULL,"+ ADMIN_VALID_COL + " BOOL)";

        String createTimeChatTableStatement = "CREATE TABLE "+ TIMECHAT_TABLE_NAME+ " ("+ID_COL+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TIME_COL1+" TEXT NOT NULL ,"+ENTRY_NO+" INTEGER NOT NULL , "+CREATED_AT_COL+" DATE NOT NULL, "+ADMIN_ID_FOR_COL+ " INTEGER)";

        db.execSQL(createAdminTableStatement);
        db.execSQL(createTimeChatTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addAdmin (AdminModel adminModel){
        List<String> allAdminEmail = findAllAdminEmail();
        boolean return_value = true;
            if (!allAdminEmail.contains(adminModel.getAdminEmail())){
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues cv = new ContentValues();

                cv.put(ADMIN_NAME_COL,"Test");
                cv.put(ADMIN_EMAIL_COL,"test@gmail.com");
                cv.put(ADMIN_PASSWORD,"1234567890#");
                cv.put(ADMIN_VALID_COL,true);

                long result = db.insert(ADMIN_TABLE_NAME,null,cv);

                if (result == -1){
                    return_value = false;
                }else{
                    return_value = true;
                }
        }
            return  return_value;
    }

    public List<String> findAllAdminEmail () {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> returnData = new ArrayList<String>();
        String queryString = "SELECT " +ADMIN_EMAIL_COL+" FROM " + ADMIN_TABLE_NAME;

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                String adminEmail = cursor.getString(0);

                returnData.add(adminEmail);
            } while (cursor.moveToNext());
        } else {
            // error occur do noting
        }
        cursor.close();
        db.close();

        return returnData;
    }

    public List<AdminModel> findAllAdmins (){
        List<AdminModel> allAdminData = new ArrayList<AdminModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String queryString = "SELECT * FROM "+ ADMIN_TABLE_NAME;

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do {
                int adminID = cursor.getInt(0);
                String adminName = cursor.getString(1);
                String adminEmail = cursor.getString(2);
                String adminPassword = cursor.getString(3);
                boolean isActive = cursor.getInt(4) == 1 ? true: false;

                AdminModel adminModel = new AdminModel(adminID,adminName,adminEmail,adminPassword,isActive);

                allAdminData.add(adminModel);
            }while (cursor.moveToNext());
        }else{
//            Error
        }
        return allAdminData;
    }

    public boolean checkAdminExistOrNot(String emailId, String password){
        boolean return_data = true;
        List<AdminModel> adminModel = findAllAdmins();

        for(AdminModel eachAdmin: adminModel){
            if (eachAdmin.getAdminEmail().equals(emailId) && eachAdmin.getAdminPassword().equals(password)){
                return_data = true;
            }else{
                return_data = false;
            }
        }
        return  return_data;
    }


//    TIMECHAT TABLE OPERATIONS

    public boolean addTimes (TimeChatModel timeChatModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TIME_COL1,timeChatModel.getTime());
//        cv.put(TIME_COL2,timeChatModel.getTime2());
//        cv.put(TIME_COL3,timeChatModel.getTime3());
        cv.put(ENTRY_NO,timeChatModel.getEntry_no());
        cv.put(CREATED_AT_COL,timeChatModel.getRegistedDate());
        cv.put(ADMIN_ID_FOR_COL, timeChatModel.getAdminID());

        long result = db.insert(TIMECHAT_TABLE_NAME,null,cv);

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }


    public List<TimeChatModel> findAllData(){
        List<TimeChatModel> chatModel = new ArrayList<TimeChatModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM "+TIMECHAT_TABLE_NAME+" WHERE "+CREATED_AT_COL+" <= DATE('"+SUNDAY+"') AND CREATED_AT >= DATE('"+MONDAY+"') ORDER BY CREATED_AT ASC ";
//          String queryString = "SELECT * FROM "+TIMECHAT_TABLE_NAME;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                TimeChatModel chat = new TimeChatModel();
                chat.setId(cursor.getInt(0));
                chat.setTime(cursor.getString(1));
                chat.setEntry_no(cursor.getInt(2));
                chat.setRegistedDate(cursor.getString(3));
                chat.setAdminID(cursor.getInt(4));
//                System.out.println("HITT"+cursor.getString(2));

                chatModel.add(chat);

            } while (cursor.moveToNext());
        } else {
            // error occur do noting
        }
        cursor.close();
        db.close();
        return  chatModel;
    }


    public int findDateCount(String date){
        int returnData = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT count(*) FROM TIMECHAT_TABLE WHERE CREATED_AT = DATE('"+date+"');";
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()) {
            do {
                returnData = cursor.getInt(0);
            } while (cursor.moveToNext());
        }else{
//            Error
        }

        return returnData;
    }

    public List<String> distinctDates(){
        List<String> listOfDistinct = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT DISTINCT CREATED_AT FROM TIMECHAT_TABLE WHERE CREATED_AT <= DATE('"+SUNDAY+"') AND CREATED_AT >= DATE('"+MONDAY+"') ORDER BY CREATED_AT ASC ;";
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do{
                listOfDistinct.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }else{
//            Error
        }
        return listOfDistinct;
    }

    public  boolean checkIsExistorNot(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlStatement = "SELECT * FROM "+TIMECHAT_TABLE_NAME+" WHERE "+CREATED_AT_COL+" = '"+date+"';";
        Cursor cursor = db.rawQuery(sqlStatement,null);

        if (cursor != null && cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }


    public boolean updateChatTable(String date,String time1,String time2, String time3, int entryNo){
        String dataEntryString = "Entry"+String.valueOf(entryNo)+"-"+time1+"-"+time2+"-"+time3;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TIME_COL1,dataEntryString);
        try {
            db.update(TIMECHAT_TABLE_NAME, contentValues, CREATED_AT_COL + "= ? AND " + ENTRY_NO + " = ?", new String[]{date, String.valueOf(entryNo)});
            return true;
        }catch (Exception e){
            return false;
        }
    }



}
