package com.example.myfit;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class AccountDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mabase.db";
    public static final int DATABASE_VERSION = 1;

    //je créer ma requete pr la base
    private static final String CREATE_BDD = "CREATE TABLE account"
            + "(nom varchar2(15) not null, prenom varchar2(15) not null, email varchar2(15) not null, sexe varchar2(15), pasword varchar2(15), checkpasword varchar2(15) " +
            ")";
    //creatuion automatique du constructeur de dbhelper
    public AccountDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "mabase.db", null, 2);

    }
    //constructeur en plus


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table accounts;");
        onCreate(sqLiteDatabase);
    }

    //on insère les données
    public void insertData(String nom, String prenom, String email, String sexe, String pasword, String checkpasword){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nom", nom);
        contentValues.put("prenom", prenom);
        contentValues.put("email", email);
        contentValues.put("sexe", sexe);
        contentValues.put("pasword", pasword);
        contentValues.put("checkpasword", checkpasword);

        sqLiteDatabase.insert("accounts", null, contentValues);
    }

    //prise ailleurs
    //elle sert à recuperer les données
    public ArrayList<AccountData> getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<AccountData> arrayList = new ArrayList<>();
        int i = 0;
        try {
            Cursor res = sqLiteDatabase.rawQuery("select * from accounts", null);
            res.moveToFirst();

            while (res.moveToNext()) {
                i++;
                arrayList.add(new AccountData(i, res.getString(0), res.getString(1), res.getString(2), res.getString(3),res.getString(4), res.getString(5), res.getString(6)));

                res.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private AccountDB dbHelper;

    public void open(){
        SQLiteDatabase database = dbHelper.getWritableDatabase();

    }

}



