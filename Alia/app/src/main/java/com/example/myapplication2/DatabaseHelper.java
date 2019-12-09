package com.example.myapplication2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="RecipeBook.db";
    public static final String TABLE_NAME ="recipe_table";
    public static final String COL_1 = "title";
    public static final String COL_2 = "ingredient";
    public static final String COL_3 = "instruction";
    public static final String COL_4 = "cookingtime";
    public static final String COL_5 = "rating";
    public static final String COL_6 = "nutrition";
    public static final String COL_7 = "calories";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table" + TABLE_NAME + "(title, )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
