package com.ronomicro.exame.ClassedeBase;

/**
 * Created by AMINE on 08/05/2017.
 */

public class Specialite {
    String numS;
    String nomS;
    public Specialite() {
        this.numS = "";
        this.nomS = "";
    }

    public Specialite(String numS, String nomS) {
        this.numS = numS;
        this.nomS = nomS;
    }

    public String getNumS() {
        return numS;
    }

    public void setNumS(String numM) {
        this.numS = numM;
    }

    public String getNomS() {
        return nomS;
    }

    public void setNomS(String nomM) {
        this.nomS = nomM;
    }
}
