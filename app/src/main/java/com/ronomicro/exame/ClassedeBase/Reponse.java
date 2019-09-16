package com.ronomicro.exame.ClassedeBase;

/**
 * Created by AMINE on 09/05/2017.
 */

public class Reponse {
    String num_reponse;
    String reponse;
    String true_false;
    String num_question;
    public Reponse() {
        this.num_reponse = "";
        this.reponse = "";
        this.true_false = "";
        this.num_question ="";
    }

    public Reponse(String num_reponse, String reponse, String true_false, String num_question) {
        this.num_reponse = num_reponse;
        this.reponse = reponse;
        this.true_false = true_false;
        this.num_question = num_question;
    }

    public String getNum_reponse() {
        return num_reponse;
    }

    public void setNum_reponse(String num_reponse) {
        this.num_reponse = num_reponse;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getTrue_false() {
        return true_false;
    }

    public void setTrue_false(String true_false) {
        this.true_false = true_false;
    }

    public String getNum_question() {
        return num_question;
    }

    public void setNum_question(String num_question) {
        this.num_question = num_question;
    }
}
