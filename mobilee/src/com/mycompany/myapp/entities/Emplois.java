/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.entities;
import com.mycompany.myapp.entities.User;

/**
 *
 * @author HP
 */
public class Emplois {
     private int id,cin;
 private String nom,prenom,ddebut,dfin;
    private User u;
    public Emplois(int id, int cin, String nom, String prenom, String ddebut, String dfin, User u) {
        this.id = id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.ddebut = ddebut;
        this.dfin = dfin;
        this.u = u;
    }

    public Emplois(int cin, String nom, String prenom, String ddebut, String dfin, User u) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.ddebut = ddebut;
        this.dfin = dfin;
        this.u = u;
    }

    public Emplois(int cin, String ddebut, String dfin) {
        this.cin = cin;
        this.ddebut = ddebut;
        this.dfin = dfin;
    }

    public Emplois() {
    }

    public int getId() {
        return id;
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

    public String getDdebut() {
        return ddebut;
    }

    public String getDfin() {
        return dfin;
    }

    public User getU() {
        return u;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setDdebut(String ddebut) {
        this.ddebut = ddebut;
    }

    public void setDfin(String dfin) {
        this.dfin = dfin;
    }

    public void setU(User u) {
        this.u = u;
    }

    @Override
    public String toString() {
        return "Emplois{" + "id=" + id + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", ddebut=" + ddebut + ", dfin=" + dfin + ", u=" + u + '}';
    }
   
}
