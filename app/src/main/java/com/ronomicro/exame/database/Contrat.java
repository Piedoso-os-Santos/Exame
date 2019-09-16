package com.ronomicro.exame.database;

/**
 * Created by AMINE on 30/04/2017.
 */

public class Contrat {
    private Contrat(){

    }
    public static  class Base{
        public  static  String Basename="examBase.db";
        public static int version=3;
    }

    public static class Table_Prof{
    public static String Table_Prof="Prof";
    public static String  Column_num_prof="num_prof";
    public static String Column_nom_prof="nom_prof";
    public static String Column_prenom_prof="prenom_prof";
    }
    public static class Table_Compte{
        public static String Table_Compte="Compte";
        public static String Column_nom_compt="nom_compt";
        public static String Column_mot_de_passe="mot_de_passe";
        public static String Column_num_prof="num_prof";
    }
    public static class Table_Module{
        public static String Table_Module="Module";
        public static String Column_num_Module="num_module";
        public static String Column_nom_Module="nom_module";
    }
    public static class Table_Pile{
        public static String Table_Pile="Pile";
        public static String Column_num_Pile="num_Pile";
        public static String Column_Descr="descr";
        public static String Column_num_prof="num_prof";
        public static String Column_num_Module="num_Module";
    }
    public static class Table_Exam{
        public static String Table_Exam="Exam";
        public static String Column_num_Exam="num_Exam";
        public static String Column_Date_exam="Date_exam";
        public static String Column_Duree="Duree";
        public static String Column_num_Pile="num_Pile";
    }

    public  static class Table_Question{
        public static String Table_Question="Question";
        public static String Column_num_Question="num_Question";
        public static String Column_num_Pile="num_Pile";
        public static String Column_text_question="text_question";
    }
    public static class Table_Reponse{
        public static String Table_Reponse="Reponse";
        public static String Column_num_reponse="num_reponse";
        public static String Column_reponse="reponse";
        public static String Column_true_flase="true_flase";
        public static String Column_num_question="num_question";
    }

    public static class Table_Specialite{
        public static String Table_Specialite="Specialite";
        public static String Column_num_specialite ="num_specialite";
        public static String Column_nom_specialite="nom_specialite";
    }
    public static class Table_Etudiant {
        public static String Table_Etudiant="Etudiant";
        public static String Column_num_etudiant = "num_etudiant";
        public static String Column_nom_etudiant = "nom_etudiant";
        public static String Column_prenom_etudiant = "prenom_etudiant";
        public static String Column_pass = "pass";
        public static String Column_num_spe = "num_spe";
        public static String Column_num_prof = "num_prof";
    }
    public static class Table_Exam_Etudiant {
        public static String Table_Exam_Etudiant="Exam_Etudiant";
        public static String Column_num_etudiant="num_etudiant";
        public static String Column_num_exam="num_exam";
    }

}
