package com.thuydev.thuyqnph35609_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thuydev.thuyqnph35609_ass.Database.Dbhelper_ass;

public class DAO_phien {
    Dbhelper_ass dbhelper_ass;
    SQLiteDatabase db;

    public DAO_phien(Context context) {
        dbhelper_ass = new Dbhelper_ass(context);
        db = dbhelper_ass.getWritableDatabase();
    }
public int soPhien(){
        int so = 1;
    ContentValues values = new ContentValues();
    values.put("sophien",1);

    String [] index = new String[]{
            String.valueOf(so)
    };
        return db.update("phienChay",values,"solan=?",index);
}

public int so(){
        int a = 0;
    Cursor c = db.rawQuery("select * from phienChay",null);
    if(c!=null&&c.getCount()>0){
        c.moveToFirst();
        a=c.getInt(1);
    }
        return a;
}
}
