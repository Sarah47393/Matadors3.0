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
 public User(User u_c) {
      this.id = u_c.id;
      this.nom = u_c.nom;
      this.prenom = u_c.prenom;
       this.password = u_c.password;
        this.cin = u_c.cin;
        this.role = u_c.role;
        this.access = u_c.access;
        this.image = u_c.image;
          this.datenaissance = u_c.datenaissance;
    }
 
    public User(int password, int cin, String nom, String prenom, String datenaissance, String access, String image) {
        this.password = password;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaissance = datenaissance;
        this.access = access;
        this.image = image;
    }

    public User(int cin, int password) {
       
         this.cin = cin;
         this.password = password;
    }
      public User(int id, String nom, String prenom, int password, int cin, String access, String image, String datenaissance) {
        this.id = id;
       
        this.nom = nom;
        this.prenom = prenom;
         this.password = password;
        this.cin = cin;
      
        this.access = access;
        this.image = image;
         this.datenaissance = datenaissance;
    }

    public User( String nom, String prenom,int password, int cin, String role, String access, String image, String datenaissance) {
       
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.cin = cin;
        this.role = role;
        this.access = access;
        this.image = image;
         this.datenaissance = datenaissance;
    
    }

  

    public User(int id, String nom, String prenom, int password, int cin , String role, String access, String image,String datenaissance) {
        this.id = id;
       
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.cin = cin;
        this.role = role;
        this.access = access;
        this.image = image;
         this.datenaissance = datenaissance;
       
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id +  ", nom=" + nom + ", prenom=" + prenom +", password=" + password + ", cin=" + cin +  ", role=" + role + ", access=" + access + ", image=" + image +", datenaissance=" + datenaissance + '}';
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
