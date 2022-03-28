/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author HP
 */
public class User {
     private int id,password,cin;
    private String nom,prenom,datenaissance,role,access,image;

    public User() {
    }

    public User(int id, int cin) {
        this.id = id;
        this.cin = cin;
    }

    public User(int password, int cin, String nom, String prenom, String datenaissance, String role, String access, String image) {
        this.password = password;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.role = role;
        this.access = access;
        this.image = image;
    
    }

    public User(int id, int password, int cin, String nom, String prenom, String datenaissance, String role, String access, String image) {
        this.id = id;
        this.password = password;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.role = role;
        this.access = access;
        this.image = image;
       
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password=" + password + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", datenaissance=" + datenaissance + ", role=" + role + ", access=" + access + ", image=" + image + '}';
    }

    public int getId() {
        return id;
    }

    public int getPassword() {
        return password;
    }

    public int getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDatenaissance() {
        return datenaissance;
    }

    public String getRole() {
        return role;
    }

    public String getAccess() {
        return access;
    }

    public String getImage() {
        return image;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public void setImage(String image) {
        this.image = image;
    }

   
    
    
}
