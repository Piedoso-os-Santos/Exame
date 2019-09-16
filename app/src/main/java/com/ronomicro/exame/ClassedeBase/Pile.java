package com.ronomicro.exame.ClassedeBase;

/**
 * Created by AMINE on 08/05/2017.
 */

public class Pile {
    String numP;
    String descP;
    String numProf;
    String numModu;
    public Pile() {
        this.numP = "";
        this.descP ="";
        this.numProf = "";
        this.numModu = "";
    }

    public Pile(String numP, String descP, String numProf, String numModu) {
        this.numP = numP;
        this.descP = descP;
        this.numProf = numProf;
        this.numModu = numModu;
    }

    public String getNumP() {
        return numP;
    }

    public void setNumP(String numP) {
        this.numP = numP;
    }

    public String getDescP() {
        return descP;
    }

    public void setDescP(String descP) {
        this.descP = descP;
    }

    public String getNumProf() {
        return numProf;
    }

    public void setNumProf(String numProf) {
        this.numProf = numProf;
    }

    public String getNumModu() {
        return numModu;
    }

    public void setNumModu(String numModu) {
        this.numModu = numModu;
    }
}
