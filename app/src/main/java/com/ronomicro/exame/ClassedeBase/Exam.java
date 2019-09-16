package com.ronomicro.exame.ClassedeBase;

/**
 * Created by AMINE on 10/05/2017.
 */

public class Exam {
    String num_Exam ;
    String Date_exam ;
    String Duree;
    String num_Pile;

    public Exam() {
        this.num_Exam = "";
        Date_exam = "";
        Duree = "";
        this.num_Pile = "";
    }

    public Exam(String num_Exam, String date_exam, String duree, String num_Pile) {
        this.num_Exam = num_Exam;
        this.Date_exam = date_exam;
        this.Duree = duree;
        this.num_Pile = num_Pile;
    }

    public String getNum_Exam() {
        return num_Exam;
    }

    public void setNum_Exam(String num_Exam) {
        this.num_Exam = num_Exam;
    }

    public String getDate_exam() {
        return Date_exam;
    }

    public void setDate_exam(String date_exam) {
        Date_exam = date_exam;
    }

    public String getDuree() {
        return Duree;
    }

    public void setDuree(String duree) {
        Duree = duree;
    }

    public String getNum_Pile() {
        return num_Pile;
    }

    public void setNum_Pile(String num_Pile) {
        this.num_Pile = num_Pile;
    }
}
