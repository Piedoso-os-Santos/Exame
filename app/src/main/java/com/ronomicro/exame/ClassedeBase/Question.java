package com.ronomicro.exame.ClassedeBase;

/**
 * Created by AMINE on 04/05/2017.
 */

public class Question {

    String num_Question ;
    String num_Pile;
    String text_Question;
    public Question() {
        this.num_Question = "";
        this.num_Pile = "";
        this.text_Question = "";
    }

    public Question(String num_Question, String text_Question,String num_Pile) {
        this.num_Question = num_Question;
        this.num_Pile = num_Pile;
        this.text_Question = text_Question;
    }

    public String getNum_Question() {
        return num_Question;
    }

    public void setNum_Question(String num_Question) {
        this.num_Question = num_Question;
    }

    public String getNum_Pile() {
        return num_Pile;
    }

    public void setNum_Pile(String num_Pile) {
        this.num_Pile = num_Pile;
    }

    public String getText_Question() {
        return text_Question;
    }

    public void setText_Question(String text_Question) {
        this.text_Question = text_Question;
    }
}
