package com.example.myfit;

class Utilisateurs {


    private int id;
    private String nom, pre, email, password, pathImage;

    public Utilisateurs(int id, String nom, String pre, String email, String password, String pathImage) {
        this.nom=nom;
        this.pre=pre;
        this.email = email;
        this.password = password;
        this.pathImage = pathImage;

        this.id = id;
    }


    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPre() {
        return pre;
    }
    public void setPre(String nom) {
        this.pre = pre;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setPathImage(String pathImage) { this.pathImage = pathImage; }
    public String getPathImage() {
        return pathImage;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String toString() {
        return "UserID : " + id + "\nNom : " + nom + "\nPrenom : " + pre + "\nE-mail : " + email + "\nPassword : " + password + "\nImage" +pathImage;
    }
}

