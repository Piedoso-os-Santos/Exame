package com.ronomicro.exame.ClassedeBase;

/**
 * Created by AMINE on 04/05/2017.
 */

public class Etudiant {

    String num_etudiant ;
    String pass_etudiant;
    String nom_etudiant;
    String prenom_etudiant;
    String num_spe ;
    String num_prof;
    public Etudiant() {

    }

    public Etudiant(String num_etudiant,String pass_etudiant, String nom_etudiant, String prenom_etudiant, String num_spe, String num_prof) {
        this.num_etudiant = num_etudiant;
        this.pass_etudiant=pass_etudiant;
        this.nom_etudiant = nom_etudiant;
        this.prenom_etudiant=prenom_etudiant;
        this.num_spe = num_spe;
        this.num_prof = num_prof;
    }



    public void setNum_etudiant(String num_etudiant) {
        this.num_etudiant = num_etudiant;
    }
    public void setPass_etudiant(String pass) {
        this.pass_etudiant = pass;
    }
    public void setNom_etudiant(String nom_etudiant) {
        this.nom_etudiant = nom_etudiant;
    }
    public void setPrenom_etudiant(String prenom_etudiant) {
        this.prenom_etudiant = prenom_etudiant;
    }

    public void setNum_spe(String num_spe) {
        this.num_spe = num_spe;
    }

    public void setNum_prof(String num_prof) {
        this.num_prof = num_prof;
    }



    public String getNum_etudiant() {
        return num_etudiant;
    }
    public String getPass_etudiant() {
        return pass_etudiant;
    }
    public String getNom_etudiant() {
        return nom_etudiant;
    }

    public String getPrenom_etudiant() {
        return prenom_etudiant;
    }

    public String getNum_spe() {
        return num_spe;
    }

    public String getNum_prof() {
        return num_prof;
    }



}
