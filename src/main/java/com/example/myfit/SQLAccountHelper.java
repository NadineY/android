package com.example.myfit;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase;

public class SQLAccountHelper<MySQLiteHelper> {

    private SQLiteDatabase database;
    private AccountDB dbHelper;

    public SQLAccountHelper(){
        database = dbHelper.getWritableDatabase();

    }

    public void close(){
        dbHelper.close();
    }




}
