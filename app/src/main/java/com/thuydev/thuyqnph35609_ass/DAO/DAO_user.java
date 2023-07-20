package com.thuydev.thuyqnph35609_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thuydev.thuyqnph35609_ass.DTO.DTO_user;
import com.thuydev.thuyqnph35609_ass.Database.Dbhelper_ass;

import java.util.ArrayList;
import java.util.List;

public class DAO_user {
    Dbhelper_ass dbhelper_ass;
    SQLiteDatabase db;

    public DAO_user(Context context) {
        dbhelper_ass = new Dbhelper_ass(context);
        db = dbhelper_ass.getWritableDatabase();
    }

    public long AddRow(DTO_user dto_user) {
        ContentValues values = new ContentValues();
        values.put("id", dto_user.getId());
        values.put("username", dto_user.getUsername());
        values.put("email", dto_user.getEmail());
        values.put("password", dto_user.getPassword());
        values.put("fullname", dto_user.getFullname());
        return db.insert("Users", null, values);
    }

    public int DeleteRow(DTO_user dto_user) {
        String[] index = new String[]{
                String.valueOf(dto_user.getId())
        };
        return db.delete("Users", "id=?", index);
    }

    public int UpdateRow(DTO_user dto_user) {
        ContentValues values = new ContentValues();
        values.put("id", dto_user.getId());
        values.put("username", dto_user.getUsername());
        values.put("email", dto_user.getEmail());
        values.put("password", dto_user.getPassword());
        values.put("fullname", dto_user.getFullname());

        String[] index = new String[]{
                String.valueOf(dto_user.getId())
        };

        return db.update("Users", values, "id=?", index);
    }

    public List<DTO_user> getAll() {
        List<DTO_user> list = new ArrayList<>();
        Cursor c = db.rawQuery("select * from Users", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int id = c.getInt(0);
                String username = c.getString(1);
                String email = c.getString(2);
                String password = c.getString(3);
                String fullname = c.getString(4);
                DTO_user user = new DTO_user(id, username, email, password, fullname);
                list.add(user);
            } while (c.moveToNext());
        }
        return list;
    }
}
