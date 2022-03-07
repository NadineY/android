package com.example.myfit;

import android.database.sqlite.SQLiteDatabase;
import android.widget.RadioButton;

public class AccountData {
    //il contient tt nos elements
    private int id;
    private String nom ;
    private String prenom;
    private String email;
    private RadioButton sexe;
    private String password;
    private String checkpassword;
    //constructeur
    public AccountData(int id, String nom, String prenom, String email, RadioButton sexe, String password, String checkpassword ) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.password = password;
        this.checkpassword = checkpassword;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {

        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RadioButton getSexe() {
        return sexe;
    }

    public void setSexe(RadioButton sexe) {
        this.sexe = sexe;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckpassword() {
        return checkpassword;
    }

    public void setCheckpassword(String checkpassword) {
        this.checkpassword = checkpassword;
    }

}

