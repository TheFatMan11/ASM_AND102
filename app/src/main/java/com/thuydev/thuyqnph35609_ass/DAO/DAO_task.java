package com.thuydev.thuyqnph35609_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thuydev.thuyqnph35609_ass.DTO.DTO_task;
import com.thuydev.thuyqnph35609_ass.Database.Dbhelper_ass;

import java.util.ArrayList;
import java.util.List;

public class DAO_task {
    Dbhelper_ass dbhelper_ass ;
    SQLiteDatabase db;

    public DAO_task(Context context){
        dbhelper_ass = new Dbhelper_ass(context);
        db = dbhelper_ass.getWritableDatabase();
    }

    public long AddRow(DTO_task task){
        ContentValues values = new ContentValues();
        values.put("name",task.getName());
        values.put("content",task.getConten());
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
        values.put("content",task.getConten());
        values.put("status",task.getStatus());
        values.put("start",task.getStart());
        values.put("endl",task.getEnd());
        values.put("id_user",task.getId_user());

        String [] index = new String[]{
                String.valueOf(task.getId())
        };

        return db.update("tasks",values,"id=?",index);
    }

    public List<DTO_task> getData(int id_user){
        List<DTO_task> list = new ArrayList<>();
        Cursor c =db.rawQuery("select * from tasks where id_user = "+ id_user,null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                DTO_task task = new DTO_task();
                task.setId(c.getInt(0));
                task.setName(c.getString(1));
                task.setConten(c.getString(2));
                task.setStatus(c.getInt(3));
                task.setStart(c.getString(4));
                task.setEnd(c.getString(5));
                task.setId_user(c.getInt(6));
                list.add(task);
            }while (c.moveToNext());
        }
        return list;
    }
}
