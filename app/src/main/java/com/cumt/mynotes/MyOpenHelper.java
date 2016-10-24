package com.cumt.mynotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 重写了SQLiteOpenHelper类，用来建立数据库，还有表
 */

public class MyOpenHelper extends SQLiteOpenHelper {

//    public static final String TABLE_NAME="notes";
//    public static final String TITLE="title";
//    public static final String CONTENT="content";
//    public static final String PATH="path";
//    public static final String VIDEO="video";
//    public static final String ID="_id";
//    public static final String TIME="time";

    public MyOpenHelper(Context context) {
        super(context,"notes", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table notes(_id integer PRIMARY KEY autoincrement,\n" +
                "title text,content text,time text)");

//        db.execSQL("CREATE TABLE"+TABLE_NAME+"("+
//        ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
//        TITLE+" TEXT NOT NULL,"+
//        CONTENT+" TEXT NOT NULL,"+
//        TIME+" TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
