package com.ronomicro.exame.ClassedeBase;

/**
 * Created by AMINE on 04/05/2017.
 */

public class Professeur {
    String num_prof ;
    String pass_prof;
    String nom_prof;
    String prenom_prof;
    String nom_compte;

    public Professeur() {

    }

    public Professeur(String num_prof, String pass_prof, String nom_prof, String prenom_prof, String nom_compte) {
        this.num_prof = num_prof;
        this.pass_prof = pass_prof;
        this.nom_prof = nom_prof;
        this.prenom_prof = prenom_prof;
        this.nom_compte = nom_compte;
    }

    public String getNum_prof() {
        return num_prof;
    }

    public String getPass_prof() {
        return pass_prof;
    }

    public String getNom_prof() {
        return nom_prof;
    }

    public String getPrenom_prof() {
        return prenom_prof;
    }

    public String getNom_compte() {
        return nom_compte;
    }

    //*****************************

    public void setNum_prof(String num_prof) {
        this.num_prof = num_prof;
    }

    public void setPass_prof(String pass_prof) {
        this.pass_prof = pass_prof;
    }

    public void setNom_prof(String nom_prof) {
        this.nom_prof = nom_prof;
    }

    public void setPrenom_prof(String prenom_prof) {
        this.prenom_prof = prenom_prof;
    }

    public void setNom_compte(String nom_compte) {
        this.nom_compte = nom_compte;
    }
}
