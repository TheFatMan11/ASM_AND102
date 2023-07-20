package com.thuydev.thuyqnph35609_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.thuydev.thuyqnph35609_ass.DTO.DTO_task;
import com.thuydev.thuyqnph35609_ass.Database.Dbhelper_ass;

public class DAO_task {
    Dbhelper_ass dbhelper_ass ;
    SQLiteDatabase db;

    public DAO_task(Context context){
        dbhelper_ass = new Dbhelper_ass(context);
        db = dbhelper_ass.getWritableDatabase();
    }

    public long AddRow(DTO_task task){
        ContentValues values = new ContentValues();
        values.put("id",task.getId());
        values.put("name",task.getName());
        values.put("status",task.getStatus());
        values.put("start",task.getStart());
        values.put("endl",task.getEnd());
        values.put("id_user",task.getId_user());

        return db.insert("tasks",null,values);
    }

    public int DeleteRow(DTO_task task){
        String [] index = new String[]{
                String.valueOf(task.getId())
        };
        return db.delete("tasks","id=?",index);
    }

    public int UpdateRow(DTO_task task){
        ContentValues values = new ContentValues();
        values.put("id",task.getId());
        values.put("name",task.getName());
        values.put("status",task.getStatus());
        values.put("start",task.getStart());
        values.put("endl",task.getEnd());
        values.put("id_user",task.getId_user());

        String [] index = new String[]{
                String.valueOf(task.getId())
        };

        return db.update("tasks",values,"id=?",index);
    }
}
