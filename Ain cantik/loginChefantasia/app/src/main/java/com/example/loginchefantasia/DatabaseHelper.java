package com.example.loginchefantasia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    public DatabaseHelper(Context context) {
        super(context, "Chefantasia.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE user (username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
    }

    public boolean addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        long ins = db.insert("user",null,contentValues);
        if(ins==-1) return false;
        else return true;
    }

    public Boolean checkUser(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username=?",new String[]{username});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    public Boolean checkUserLogin(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }
}
