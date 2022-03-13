package com.example.myfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class BDD extends SQLiteOpenHelper {

    private static final String REQUETE_CREATION_BD = "create table accounts " +
            "(id integer PRIMARY KEY AUTOINCREMENT, nom varchar2(12) not null, pre varchar2(12) not null, email varchar2(15), password varchar2(12), pathImage TEXT, sexe TEXT" +
            ")";

    public BDD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUETE_CREATION_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table accounts;");
        onCreate(db);
    }

    public void insertData(String nom, String pre, String email, String password, String TEXT){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nom", nom);
        contentValues.put("pre", pre);
        contentValues.put("email", email);
        contentValues.put("password", password);
        byte[] pathImage = new byte[0];
        contentValues.put("image", pathImage);
        db.insert("accounts", null, contentValues);
    }

    public boolean existe (String mail){
        String requete = "select count(*) from accounts where email='"+mail+"'";
        SQLiteStatement state = getReadableDatabase().compileStatement(requete);
        long c = state.simpleQueryForLong();
        state.close();
        if(c==0){
            return true;
        }else{
            return false;
        }
    }

    public boolean connexion (String mail, String pass){
        String requete = "select count(*) from accounts where email='"+mail+"' and password='" +pass+"'";
        SQLiteStatement state = getReadableDatabase().compileStatement(requete);
        long c = state.simpleQueryForLong();
        state.close();
        if(c==1){
            return true;
        }else{
            return false;
        }
    }
}
