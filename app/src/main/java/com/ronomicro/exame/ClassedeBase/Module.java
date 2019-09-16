package com.ronomicro.exame.ClassedeBase;

/**
 * Created by AMINE on 08/05/2017.
 */

public class Module {
    String numM;
    String nomM;
    public Module() {
        this.numM = "";
        this.nomM = "";
    }

    public Module(String numM, String nomM) {
        this.numM = numM;
        this.nomM = nomM;
    }

    public String getNumM() {
        return numM;
    }

    public void setNumM(String numM) {
        this.numM = numM;
    }

    public String getNomM() {
        return nomM;
    }

    public void setNomM(String nomM) {
        this.nomM = nomM;
    }
}
