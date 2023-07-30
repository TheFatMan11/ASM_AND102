package com.thuydev.thuyqnph35609_ass.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper_ass extends SQLiteOpenHelper {
    static final String nameDB = "AssDB";
   static final int vertionDB =1;

    public Dbhelper_ass(Context context){
        super(context,nameDB,null,vertionDB);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String user = "CREATE TABLE Users (     id       INTEGER PRIMARY KEY AUTOINCREMENT                      UNIQUE                      NOT NULL,     username TEXT    UNIQUE                      NOT NULL,     email    TEXT    UNIQUE                      NOT NULL,     password TEXT    NOT NULL,     fullname TEXT    NOT NULL );";
        String task = "CREATE TABLE tasks (     id      INTEGER PRIMARY KEY AUTOINCREMENT                     NOT NULL                     UNIQUE,     name    TEXT    NOT NULL,     content TEXT,     status  INTEGER NOT NULL                     DEFAULT (0),     start   TEXT    NOT NULL,     endl    TEXT    NOT NULL,     id_user INTEGER NOT NULL                     REFERENCES Users (id) )";
        String Firt = "CREATE TABLE phienChay (     solan   INTEGER PRIMARY KEY AUTOINCREMENT,     sophien INTEGER )";
        String luuDangNhap = "CREATE TABLE luuaccount (     id        INTEGER PRIMARY KEY,     username  TEXT,     pasword   TEXT,     trangthai INTEGER )";
        db.execSQL(user);
        db.execSQL(task);
        db.execSQL(Firt);
        db.execSQL(luuDangNhap);
        db.execSQL("insert into Users values(1,'thuy','thuyqnph35609@fpt.edu.vn','1','Quàng Ngọc Thủy')");
        db.execSQL("insert into phienChay values (1,0)");
        db.execSQL("insert into luuaccount values(1,'ok','ok',0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
