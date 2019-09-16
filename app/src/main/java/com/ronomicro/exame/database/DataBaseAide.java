package com.ronomicro.exame.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.ronomicro.exame.ClassedeBase.Etudiant;
import com.ronomicro.exame.ClassedeBase.Exam;
import com.ronomicro.exame.ClassedeBase.Module;
import com.ronomicro.exame.ClassedeBase.Pile;
import com.ronomicro.exame.ClassedeBase.Professeur;
import com.ronomicro.exame.ClassedeBase.Question;
import com.ronomicro.exame.ClassedeBase.Reponse;
import com.ronomicro.exame.ClassedeBase.Specialite;
import com.ronomicro.exame.gestion.GestionCompte;

import java.util.ArrayList;

/**
 * Created by AMINE on 30/04/2017.
 */

public class DataBaseAide extends SQLiteOpenHelper {
    private String name;
    private Context c;
    private  int version;
     /****************************************************************************************/
     /*                       Constructeur De la classe DataBaseAide
     /*
     /* *************************************************************************************/
    public DataBaseAide(Context context, String name,  int version) {
        super(context, name, null, version);
        this.name=name;
        this.c=context;
        this.version=version;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String tableProf="Create table "+
                Contrat.Table_Prof.Table_Prof+"( " +
                Contrat.Table_Prof.Column_num_prof+" nvarchar(50) primary key , " +
                Contrat.Table_Prof.Column_nom_prof+" nvarchar(50) ," +
                Contrat.Table_Prof.Column_prenom_prof+" nvarchar(50) " +
                ")";
        sqLiteDatabase.execSQL(tableProf);

        final String tableCompte= "Create table "+
                Contrat.Table_Compte.Table_Compte+"( " +
                Contrat.Table_Compte.Column_nom_compt+" nvarchar(50) primary key , " +
                Contrat.Table_Compte.Column_mot_de_passe+" nvarchar(50), " +
                Contrat.Table_Compte.Column_num_prof+" nvarchar(50) , foreign key ("+Contrat.Table_Compte.Column_num_prof+") references "+
                Contrat.Table_Prof.Table_Prof+"("+Contrat.Table_Prof.Column_num_prof+") " +
                ")" +
                "";
        sqLiteDatabase.execSQL(tableCompte);


                final String tableModule="Create table "+
                Contrat.Table_Module.Table_Module+"( " +
                Contrat.Table_Module.Column_num_Module+" nvarchar(50) primary key ," +
                Contrat.Table_Module.Column_nom_Module+" nvarchar(50) " +
                ")";
        sqLiteDatabase.execSQL(tableModule);

                final String tablePile= "Create table "+
                Contrat.Table_Pile.Table_Pile+"( " +
                Contrat.Table_Pile.Column_num_Pile+" nvarchar(50) primary key , " +
                Contrat.Table_Pile.Column_Descr+" nvarchar(50), " +
                Contrat.Table_Pile.Column_num_prof+" nvarchar(50) ," +
                Contrat.Table_Pile.Column_num_Module+" nvarchar(50) ," +
                "foreign key ("+Contrat.Table_Pile.Column_num_prof+") references "+
                Contrat.Table_Prof.Table_Prof+"("+Contrat.Table_Prof.Column_num_prof+"), " +
                "foreign key ("+Contrat.Table_Pile.Column_num_Module+") references "+
                Contrat.Table_Module.Table_Module+"("+
                Contrat.Table_Module.Column_num_Module+") " +
                ") ";
        sqLiteDatabase.execSQL(tablePile);

                final String tableExam="Create table "+
                Contrat.Table_Exam.Table_Exam+"( " +
                Contrat.Table_Exam.Column_num_Exam+" nvarchar(50) primary key , " +
                Contrat.Table_Exam.Column_Date_exam+" Datetime, " +
                Contrat.Table_Exam.Column_Duree+" float , " +
                Contrat.Table_Exam.Column_num_Pile+" nvarchar(50), " +
                "foreign key ("+Contrat.Table_Exam.Column_num_Pile+") references "+
                Contrat.Table_Pile.Table_Pile+"("+Contrat.Table_Pile.Column_num_Pile+") " +
                ") " ;

        sqLiteDatabase.execSQL(tableExam);
                final String tableQuestion="Create table "+
                Contrat.Table_Question.Table_Question+"( " +
                Contrat.Table_Question.Column_num_Question + " nvarchar(50) primary key , " +
                Contrat.Table_Question.Column_num_Pile +" nvarchar(50)," +
                 Contrat.Table_Question.Column_text_question+" text ," +
                " foreign key ("+Contrat.Table_Question.Column_num_Pile+") references "+
                Contrat.Table_Pile.Table_Pile+"("+
                Contrat.Table_Pile.Column_num_Pile+") " +
                ") " ;
        sqLiteDatabase.execSQL(tableQuestion);

               final String tableReponse="Create table "+
               Contrat.Table_Reponse.Table_Reponse+" ( " +
               Contrat.Table_Reponse.Column_num_reponse+" nvarchar(50) primary key , " +
               Contrat.Table_Reponse.Column_reponse+" nvarchar(50), " +
               Contrat.Table_Reponse.Column_true_flase+" nchar(1), " +
               Contrat.Table_Reponse.Column_num_question+" nvarchar(50) ," +
               "foreign key ("+Contrat.Table_Reponse.Column_num_question+") references "+
               Contrat.Table_Question.Table_Question+"("+
               Contrat.Table_Question.Column_num_Question+") " +
                ") ";
        sqLiteDatabase.execSQL(tableReponse);

                final String tableSpecialite="Create table "+
                Contrat.Table_Specialite.Table_Specialite+"( " +
                Contrat.Table_Specialite.Column_num_specialite+" nvarchar(50) primary key , " +
                Contrat.Table_Specialite.Column_nom_specialite+" nvarchar(50) " +
                ") ";
        sqLiteDatabase.execSQL(tableSpecialite);

                final String tableEtudiant="Create table "+Contrat.Table_Etudiant.Table_Etudiant+"( " +
                Contrat.Table_Etudiant.Column_num_etudiant+" nvarchar(50) primary key , " +
                Contrat.Table_Etudiant.Column_nom_etudiant+" nvarchar(50), " +
                Contrat.Table_Etudiant.Column_prenom_etudiant+" nvarchar(50), " +
                Contrat.Table_Etudiant.Column_pass+" nvarchar(50), " +
                Contrat.Table_Etudiant.Column_num_spe+" nvarchar(50)," +
                Contrat.Table_Etudiant.Column_num_prof+" nvarchar(50) ," +
                "foreign key ("+Contrat.Table_Etudiant.Column_num_spe+") references "+
                Contrat.Table_Specialite.Table_Specialite+"("+
                Contrat.Table_Specialite.Column_num_specialite+"), " +
                "foreign key ("+Contrat.Table_Etudiant.Column_num_prof+") references prof(num_prof) " +
                ") ";
        sqLiteDatabase.execSQL(tableEtudiant);

                final String tableExam_Etudiant="Create table "+Contrat.Table_Exam_Etudiant.Table_Exam_Etudiant+"( " +
                Contrat.Table_Exam_Etudiant.Column_num_etudiant+" nvarchar(50)," +
                Contrat.Table_Exam_Etudiant.Column_num_exam+" nvarchar(50) ," +
                "foreign key ("+Contrat.Table_Exam_Etudiant.Column_num_exam+") references "+
                Contrat.Table_Exam.Table_Exam+"("+Contrat.Table_Exam.Column_num_Exam+"), " +
                "foreign key ("+Contrat.Table_Exam_Etudiant.Column_num_etudiant+") references "+
                Contrat.Table_Etudiant.Table_Etudiant+"("+
                Contrat.Table_Etudiant.Column_num_etudiant+"), " +
                "primary key ("+Contrat.Table_Exam_Etudiant.Column_num_etudiant+","+
                Contrat.Table_Exam_Etudiant.Column_num_exam+") " +
                ") ";
        sqLiteDatabase.execSQL(tableExam_Etudiant);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists "+Contrat.Table_Prof.Table_Prof);
        sqLiteDatabase.execSQL("Drop table if exists "+Contrat.Table_Compte.Table_Compte);
        sqLiteDatabase.execSQL("Drop table if exists "+Contrat.Table_Module.Table_Module);
        sqLiteDatabase.execSQL("Drop table if exists "+Contrat.Table_Pile.Table_Pile);
        sqLiteDatabase.execSQL("Drop table if exists "+Contrat.Table_Exam.Table_Exam);
        sqLiteDatabase.execSQL("Drop table if exists "+Contrat.Table_Question.Table_Question);
        sqLiteDatabase.execSQL("Drop table if exists "+Contrat.Table_Reponse.Table_Reponse);
        sqLiteDatabase.execSQL("Drop table if exists "+Contrat.Table_Specialite.Table_Specialite);
        sqLiteDatabase.execSQL("Drop table if exists "+Contrat.Table_Etudiant.Table_Etudiant);
        sqLiteDatabase.execSQL("Drop table if exists "+Contrat.Table_Exam_Etudiant.Table_Exam_Etudiant);
        onCreate(sqLiteDatabase);

    }

    /****************************************************************************************/
     /*                                     Table Etudiant
     /* *************************************************************************************/
    //Ajouter un étudiant
    public void InsertEtudiant(String num,String nom,String prenom,String pass)
    {
       SQLiteDatabase db=getWritableDatabase();
            db.execSQL("insert into  "+Contrat.Table_Etudiant.Table_Etudiant+"("+
                    Contrat.Table_Etudiant.Column_num_etudiant+
                    ","+Contrat.Table_Etudiant.Column_nom_etudiant+
                    ","+Contrat.Table_Etudiant.Column_prenom_etudiant+
                    ","+Contrat.Table_Etudiant.Column_pass+
                    ") values('"+num+"','"+nom+"','"+prenom+"','"+pass+"')");


    }
    //Retourner les information d'un étudiant sous la forme d'un objet Etudiant
    public Etudiant infoetudiant(String login,String pass)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Etudiant.Table_Etudiant+
                " where "+Contrat.Table_Etudiant.Column_num_etudiant+
                " = '"+login
                +"' and "+Contrat.Table_Etudiant.Column_pass+" = '"+pass+"'",null);
        cr.moveToFirst();
        Etudiant e=new Etudiant();
        String num=cr.getString(cr.getColumnIndex(Contrat.Table_Etudiant.Column_num_etudiant));
        String passw=cr.getString(cr.getColumnIndex(Contrat.Table_Etudiant.Column_pass));
        String nom=cr.getString(cr.getColumnIndex(Contrat.Table_Etudiant.Column_nom_etudiant));
        String prenom=cr.getString(cr.getColumnIndex(Contrat.Table_Etudiant.Column_prenom_etudiant));
        String spe=cr.getString(cr.getColumnIndex(Contrat.Table_Etudiant.Column_num_spe));
        String prof=cr.getString(cr.getColumnIndex(Contrat.Table_Etudiant.Column_num_prof));
        e =new Etudiant(num,passw,nom,prenom,spe,prof);
        cr.close();
        return e;
    }
    public void updateEtudiant(String num,String nom,String prenom,String pass)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("update  "+Contrat.Table_Etudiant.Table_Etudiant+" set "+
                Contrat.Table_Etudiant.Column_nom_etudiant+"='"+nom+"',"+
                Contrat.Table_Etudiant.Column_prenom_etudiant+"='"+prenom+"',"+
                Contrat.Table_Etudiant.Column_pass+"='"+pass+"' where "+
                Contrat.Table_Etudiant.Column_num_etudiant+"='"+num+"'");
    }
    public void updateProfEtudiant(String num,String prof)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("update  "+Contrat.Table_Etudiant.Table_Etudiant+" set "+
                Contrat.Table_Etudiant.Column_num_prof+"='"+prof+"' where "+
                Contrat.Table_Etudiant.Column_num_etudiant+"='"+num+"'");
    }
    public void updateSpecEtudiant(String num,String spec)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("update  "+Contrat.Table_Etudiant.Table_Etudiant+" set "+
                Contrat.Table_Etudiant.Column_num_spe+"='"+spec+"' where "+
                Contrat.Table_Etudiant.Column_num_etudiant+"='"+num+"'");
    }

    /****************************************************************************************/
     /*                                     Table Professeur
     /* *************************************************************************************/
    //Ajouter un professeur
    public void InsertProf(String num,String nom,String prenom,String pass)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("insert into  "+Contrat.Table_Prof.Table_Prof+"("+
                Contrat.Table_Prof.Column_num_prof+
                ","+Contrat.Table_Prof.Column_nom_prof+","
                +Contrat.Table_Prof.Column_prenom_prof+
                ") values('"+num+"','"+nom+"','"+prenom+"')");
        db.execSQL("insert into  "+Contrat.Table_Compte.Table_Compte+"("+
                Contrat.Table_Compte.Column_nom_compt+
                ","+Contrat.Table_Compte.Column_mot_de_passe+","+
                Contrat.Table_Compte.Column_num_prof+
                ") values('"+num+nom+"','"+pass+"','"+num+"')");
    }
    //Retourner les information d'un professeur sous la forme d'un objet Professeur
    public Professeur infoprofesseur(String login,String pass)
    {

        SQLiteDatabase db=getReadableDatabase();
        String req="select  p."+
                Contrat.Table_Prof.Column_num_prof+
                ",p."+Contrat.Table_Prof.Column_nom_prof+
                ",p."+Contrat.Table_Prof.Column_prenom_prof+
                ",c."+Contrat.Table_Compte.Column_mot_de_passe+
                ",c."+Contrat.Table_Compte.Column_nom_compt+"\n" +
                "from "+Contrat.Table_Prof.Table_Prof+" as p,"+Contrat.Table_Compte.Table_Compte+" as c\n" +
                "where  p."+
                Contrat.Table_Prof.Column_num_prof+"=c."+
                Contrat.Table_Compte.Column_num_prof+" and p."+Contrat.Table_Prof.Column_num_prof+"='"+login+"' and c."+
                Contrat.Table_Compte.Column_mot_de_passe+"='"+pass+"'";



        Cursor cr=db.rawQuery(req,null);
        cr.moveToFirst();
        Professeur p=new Professeur();
        String num=cr.getString(cr.getColumnIndex(Contrat.Table_Prof.Column_num_prof));
        String passw=cr.getString(cr.getColumnIndex(Contrat.Table_Prof.Column_nom_prof));
        String nom=cr.getString(cr.getColumnIndex(Contrat.Table_Prof.Column_prenom_prof));
        String prenom=cr.getString(cr.getColumnIndex(Contrat.Table_Compte.Column_mot_de_passe));
        String nomcompte=cr.getString(cr.getColumnIndex(Contrat.Table_Compte.Column_nom_compt));

        p =new Professeur(num,passw,nom,prenom,nomcompte);
        cr.close();
        return p;
    }
   //Tout les professeurs
    public ArrayList<Professeur> allProf()
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Prof.Table_Prof,null);
        cr.moveToFirst();
        ArrayList<Professeur> am=new ArrayList<Professeur>();
        while (cr.isAfterLast()==false) {
            String num_prof=cr.getString(cr.getColumnIndex(Contrat.Table_Prof.Column_num_prof));
            String nom_prof=cr.getString(cr.getColumnIndex(Contrat.Table_Prof.Column_nom_prof));
            String prenom_prof=cr.getString(cr.getColumnIndex(Contrat.Table_Prof.Column_prenom_prof));
            Professeur m = new Professeur( num_prof ,"", nom_prof, prenom_prof,"");
            am.add(m);
            cr.moveToNext();
        }
        cr.close();
        return am;
    }


    /****************************************************************************************/
     /*                                     Table Module
     /* *************************************************************************************/
    //Insertion table module
    public void insModule(String num,String nom)
    {
        String req="insert into "+Contrat.Table_Module.Table_Module+" values('"+num+"','"+nom+"')";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);

    }
    //Retourner les information d'un Module
    public Module infoModule(String numM)
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Module.Table_Module+
                " where "+Contrat.Table_Module.Column_num_Module+
                " = '"+numM
                +"'",null);
        cr.moveToFirst();
        Module m=new Module();
        if(cr.getCount()>0) {
            String num = cr.getString(cr.getColumnIndex(Contrat.Table_Module.Column_num_Module));
            String nom = cr.getString(cr.getColumnIndex(Contrat.Table_Module.Column_nom_Module));
            m = new Module(num, nom);
        }
        cr.close();
        return m;
    }
    //Modification d'un table module
    public void modiModule(String num,String nom)
    {
        String req="update  "+Contrat.Table_Module.Table_Module+" set "+ Contrat.Table_Module.Column_nom_Module+" ='"+nom+"' where "+Contrat.Table_Module.Column_num_Module+" = '"+num+"'";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);

    }
    //Supprimer un module
    public void delModule(String num)
    {
        String req="delete from "+Contrat.Table_Module.Table_Module+" where "+Contrat.Table_Module.Column_num_Module+" ='"+num+"'";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);
    }
    //Retourner les information de tout les Module
    public ArrayList<Module> allModule()
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Module.Table_Module,null);
        cr.moveToFirst();
       ArrayList<Module> am=new ArrayList<Module>();
        while (cr.isAfterLast()==false) {
            String num = cr.getString(cr.getColumnIndex(Contrat.Table_Module.Column_num_Module));
            String nom = cr.getString(cr.getColumnIndex(Contrat.Table_Module.Column_nom_Module));
            Module m = new Module(num, nom);
            am.add(m);
            cr.moveToNext();
        }
        cr.close();
        return am;
    }
    //Retourner les information de tout les Module
    public ArrayList<String> allNumModuleString()
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Module.Table_Module,null);
        cr.moveToFirst();
        ArrayList<String> am=new ArrayList<String>();
        while (cr.isAfterLast()==false) {
            String num = cr.getString(cr.getColumnIndex(Contrat.Table_Module.Column_num_Module));
            am.add(num);
            cr.moveToNext();
        }
        cr.close();
        return am;
    }
    /****************************************************************************************/
     /*                                     Table Specialite
     /* *************************************************************************************/
    //Insertion table specialite
    public void insSpec(String num,String nom)
    {
        String req="insert into "+Contrat.Table_Specialite.Table_Specialite+" values('"+num+"','"+nom+"')";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);

    }
    //Retourner les information d'une Specialite
    public Specialite infoSpec(String numS)
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Specialite.Table_Specialite+
                " where "+Contrat.Table_Specialite.Column_num_specialite+
                " = '"+numS
                +"'",null);
        cr.moveToFirst();
        Specialite S=new Specialite();
        if(cr.getCount()>0) {
            String num = cr.getString(cr.getColumnIndex(Contrat.Table_Specialite.Column_num_specialite));
            String nom = cr.getString(cr.getColumnIndex(Contrat.Table_Specialite.Column_nom_specialite));
            S = new Specialite(num, nom);
        }
        cr.close();
        return S;
    }
    //Modification d'un ligne dans la table Specialite
    public void modiSpec(String num,String nom)
    {
        String req="update  "+
                Contrat.Table_Specialite.Table_Specialite+
                " set "+
                Contrat.Table_Specialite.Column_nom_specialite+" ='"+nom+
                "' where "+
                Contrat.Table_Specialite.Column_num_specialite+" = '"+num+"'";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);

    }
    //Supprimer une specialite
    public void delSpec(String num)
    {
        String req="delete from "+Contrat.Table_Specialite.Table_Specialite+" where "+Contrat.Table_Specialite.Column_num_specialite+" ='"+num+"'";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);
    }
    //Retourner les information d'un Module
    public ArrayList<Specialite> allSpec()
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Specialite.Table_Specialite,null);
        cr.moveToFirst();
        ArrayList<Specialite> as=new ArrayList<Specialite>();
        while (cr.isAfterLast()==false) {
            String num = cr.getString(cr.getColumnIndex(Contrat.Table_Specialite.Column_num_specialite));
            String nom = cr.getString(cr.getColumnIndex(Contrat.Table_Specialite.Column_nom_specialite));
            Specialite s = new Specialite(num, nom);
            as.add(s);
            cr.moveToNext();
        }
        cr.close();
        return as;
    }
    /****************************************************************************************/
     /*                                     Table Pile
     /* *************************************************************************************/
    //Insertion table Pile
    public void insPile(String numP,String desc,String numM)
    {
        String req="insert into "+Contrat.Table_Pile.Table_Pile+" values('"+numP+"','"+desc+"','"+ GestionCompte.loginCompte+"','"+numM+"')";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);

    }
    //Retourner les information d'un Pile
    public Pile infoPile(String numP)
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Pile.Table_Pile+
                " where "+Contrat.Table_Pile.Column_num_Pile+
                " = '"+numP
                +"'",null);
        cr.moveToFirst();
        Pile p=new Pile();
        if(cr.getCount()>0) {
            String numPi = cr.getString(cr.getColumnIndex(Contrat.Table_Pile.Column_num_Pile));
            String descPi = cr.getString(cr.getColumnIndex(Contrat.Table_Pile.Column_Descr));
            String numPro = cr.getString(cr.getColumnIndex(Contrat.Table_Pile.Column_num_prof));
            String numMod = cr.getString(cr.getColumnIndex(Contrat.Table_Pile.Column_num_Module));
            p = new Pile(numPi,descPi,numPro,numMod);
        }
        cr.close();
        return p;
    }
    //Modification d'un table Pile
    public void modiPile(String numP,String desc,String numM)
    {
        String req="update  "+Contrat.Table_Pile.Table_Pile+
                " set "+
                Contrat.Table_Pile.Column_Descr+" ='"+desc+"', "+
                Contrat.Table_Pile.Column_num_prof+"='"+GestionCompte.loginCompte+"', "+
                Contrat.Table_Pile.Column_num_Module+"='"+numM+"' " +
                "where "+
                Contrat.Table_Pile.Column_num_Pile+" = '"+numP+"'";

        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);

    }
    //Supprimer un Pile
    public void delPile(String numP)
    {
        String req="delete from "+Contrat.Table_Pile.Table_Pile+" where "+Contrat.Table_Pile.Column_num_Pile+" ='"+numP+"'";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);
    }
    //Retourner les information d'un Pile
    public ArrayList<Pile> allPile()
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Pile.Table_Pile,null);
        cr.moveToFirst();
        ArrayList<Pile> ap=new ArrayList<Pile>();
        while (cr.isAfterLast()==false) {
            String numPi = cr.getString(cr.getColumnIndex(Contrat.Table_Pile.Column_num_Pile));
            String descPi = cr.getString(cr.getColumnIndex(Contrat.Table_Pile.Column_Descr));
            String numPro = cr.getString(cr.getColumnIndex(Contrat.Table_Pile.Column_num_prof));
            String numMod = cr.getString(cr.getColumnIndex(Contrat.Table_Pile.Column_num_Module));
            Pile m = new Pile(numPi,descPi,numPro,numMod);
            ap.add(m);
            cr.moveToNext();
        }
        cr.close();
        return ap;
    }
    //Retourner les num d'un pile
    public ArrayList<String> allNumPileString()
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Pile.Table_Pile,null);
        cr.moveToFirst();
        ArrayList<String> am=new ArrayList<String>();
        while (cr.isAfterLast()==false) {
            String num = cr.getString(cr.getColumnIndex(Contrat.Table_Pile.Column_num_Pile));
            am.add(num);
            cr.moveToNext();
        }
        cr.close();
        return am;
    }
    /****************************************************************************************/
     /*                                     Table Question                                  */
     /* *************************************************************************************/
    //Insertion table Question
    public void insQuestion(String numq,String question,String numP)
    {
        String req="insert into "+Contrat.Table_Question.Table_Question+" values('"+numq+"','"+numP+"','"+question+"')";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);

    }
    //Retourner les information d'une Question
    public Question infoQuestion(String numQ)
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Question.Table_Question+
                " where "+Contrat.Table_Question.Column_num_Question+
                " = '"+numQ
                +"'",null);
        cr.moveToFirst();
        Question q=new Question();
        if(cr.getCount()>0) {
            String numq = cr.getString(cr.getColumnIndex(Contrat.Table_Question.Column_num_Question));
            String ques = cr.getString(cr.getColumnIndex(Contrat.Table_Question.Column_text_question));
            String numP = cr.getString(cr.getColumnIndex(Contrat.Table_Question.Column_num_Pile));

            q = new Question(numq,ques,numP);
        }
        cr.close();
        return q;
    }
    //Modification d'un table Pile
    public void modiQuestion(String numq,String ques,String numP)
    {
        String req="update  "+Contrat.Table_Question.Table_Question+
                " set "+
                Contrat.Table_Question.Column_text_question+" ='"+ques+"', "+
                Contrat.Table_Question.Column_num_Pile+"='"+numP+"' " +
                "where "+
                Contrat.Table_Question.Column_num_Question+" = '"+numq+"'";

        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);

    }
    //Supprimer un Pile
    public void delQuestion(String numQ)
    {
        String req="delete from "+Contrat.Table_Question.Table_Question+" where "+Contrat.Table_Question.Column_num_Question+" ='"+numQ+"'";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);
    }
    //Retourner les information d'un Module
    public ArrayList<Question> allQuestion()
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Question.Table_Question,null);
        cr.moveToFirst();
        ArrayList<Question> aq=new ArrayList<Question>();
        while (cr.isAfterLast()==false) {
            int nn=cr.getColumnIndex(Contrat.Table_Question.Column_num_Question);
            int nq=cr.getColumnIndex(Contrat.Table_Question.Column_text_question);
            int np=cr.getColumnIndex(Contrat.Table_Question.Column_num_Pile);
            String numq = cr.getString(nn);
            String ques = cr.getString(nq);
            String numP = cr.getString(np);

            Question q = new Question(numq,ques,numP);
            aq.add(q);
            cr.moveToNext();
        }
        cr.close();
        return aq;
    }
    public ArrayList<String> allNumQuestionString()
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Question.Table_Question,null);
        cr.moveToFirst();
        ArrayList<String> aq=new ArrayList<String>();
        while (cr.isAfterLast()==false) {
            String num = cr.getString(cr.getColumnIndex(Contrat.Table_Question.Column_num_Question));
            aq.add(num);
            cr.moveToNext();
        }
        cr.close();
        return aq;
    }
    /****************************************************************************************/
     /*                                     Table Reponse                                 */
     /* *************************************************************************************/
    //Insertion table Reponse
    public void insReponse(String num_reponse, String reponse, String true_false, String num_question)
    {
        String req="insert into "+Contrat.Table_Reponse.Table_Reponse+" values('"+num_reponse+"','"+reponse+"','"+true_false+"','"+num_question+"')";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);

    }
    //Retourner les information d'une Réponse
    public Reponse infoReponse(String numR)
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Reponse.Table_Reponse+
                " where "+Contrat.Table_Reponse.Column_num_reponse+
                " = '"+numR
                +"'",null);
        cr.moveToFirst();
        Reponse r=new Reponse();
        if(cr.getCount()>0) {
            String num_reponse=cr.getString(cr.getColumnIndex(Contrat.Table_Reponse.Column_num_reponse));
            String reponse=cr.getString(cr.getColumnIndex(Contrat.Table_Reponse.Column_reponse));
            String true_false=cr.getString(cr.getColumnIndex(Contrat.Table_Reponse.Column_true_flase));
            String num_question=cr.getString(cr.getColumnIndex(Contrat.Table_Reponse.Column_num_question));
            r = new Reponse(num_reponse,reponse,true_false,num_question);
        }
        cr.close();
        return r;
    }
    //Modification d'un table Reponse
    public void modiReponse(String num_reponse, String reponse, String true_false, String num_question)
    {
        String req="update  "+Contrat.Table_Reponse.Table_Reponse+
                " set "+
                Contrat.Table_Reponse.Column_reponse+" ='"+reponse+"', "+
                Contrat.Table_Reponse.Column_true_flase+"='"+true_false+"' ," +
                Contrat.Table_Reponse.Column_num_question+"='"+num_question+"' " +
                "where "+
                Contrat.Table_Reponse.Column_num_reponse+" = '"+num_reponse+"'";

        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);

    }
    //Supprimer une Réponse
    public void delReponse(String numR)
    {
        String req="delete from "+Contrat.Table_Reponse.Table_Reponse+" where "+Contrat.Table_Reponse.Column_num_reponse+" ='"+numR+"'";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);
    }
    //Retourner les information des Reponses
    public ArrayList<Reponse> allReponse()
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Reponse.Table_Reponse,null);
        cr.moveToFirst();
        ArrayList<Reponse> ar=new ArrayList<Reponse>();
        while (cr.isAfterLast()==false) {
            String num_reponse=cr.getString(cr.getColumnIndex(Contrat.Table_Reponse.Column_num_reponse));
            String reponse=cr.getString(cr.getColumnIndex(Contrat.Table_Reponse.Column_reponse));
            String true_false=cr.getString(cr.getColumnIndex(Contrat.Table_Reponse.Column_true_flase));
            String num_question=cr.getString(cr.getColumnIndex(Contrat.Table_Reponse.Column_num_question));
            Reponse r = new Reponse(num_reponse,reponse,true_false,num_question);
            ar.add(r);
            cr.moveToNext();
        }
        cr.close();
        return ar;
    }
    /****************************************************************************************/
     /*                                     Table Exam
     /* *************************************************************************************/
    //Insertion table Exam
    public void insExam(String num_Exam, String date_exam, String duree, String num_Pile)
    {
        String req="insert into "+Contrat.Table_Exam.Table_Exam+" values('"+num_Exam+"','"+date_exam+"','"+ duree+"','"+num_Pile+"')";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);
    }
    //Retourner les information d'un Exam
    public Exam infoExam(String numE)
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Exam.Table_Exam+
                " where "+Contrat.Table_Exam.Column_num_Exam+
                " = '"+numE
                +"'",null);
        cr.moveToFirst();
        Exam e=new Exam();
        if(cr.getCount()>0) {

            String num_Exam=cr.getString(cr.getColumnIndex(Contrat.Table_Exam.Column_num_Exam));
            String date_exam=cr.getString(cr.getColumnIndex(Contrat.Table_Exam.Column_Date_exam));
            String duree=cr.getString(cr.getColumnIndex(Contrat.Table_Exam.Column_Duree));
            String num_Pile=cr.getString(cr.getColumnIndex(Contrat.Table_Exam.Column_num_Pile));
            e = new Exam(num_Exam,date_exam,duree,num_Pile);
        }
        cr.close();
        return e;
    }
    //Modification d'un table Exam
    public void modiExam(String num_Exam, String date_exam, String duree, String num_Pile)
    {
        String req="update  "+Contrat.Table_Exam.Table_Exam+
                " set "+
                Contrat.Table_Exam.Column_Date_exam+" ='"+date_exam+"', "+
                Contrat.Table_Exam.Column_Duree+"='"+duree+"', "+
                Contrat.Table_Exam.Column_num_Pile+"='"+num_Pile+"' " +
                "where "+
                Contrat.Table_Exam.Column_num_Exam+" = '"+num_Exam+"'";

        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);

    }
    //Supprimer un Exam
    public void delExam(String numE)
    {
        String req="delete from "+Contrat.Table_Exam.Table_Exam+" where "+Contrat.Table_Exam.Column_num_Exam+" ='"+numE+"'";
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(req);
    }
    //Retourner les information d'un Exam
    public ArrayList<Exam> allExam()
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Exam.Table_Exam,null);
        cr.moveToFirst();
        ArrayList<Exam> ae=new ArrayList<Exam>();
        while (cr.isAfterLast()==false) {
            String num_Exam=cr.getString(cr.getColumnIndex(Contrat.Table_Exam.Column_num_Exam));
            String date_exam=cr.getString(cr.getColumnIndex(Contrat.Table_Exam.Column_Date_exam));
            String duree=cr.getString(cr.getColumnIndex(Contrat.Table_Exam.Column_Duree));
            String num_Pile=cr.getString(cr.getColumnIndex(Contrat.Table_Exam.Column_num_Pile));
            Exam e = new Exam(num_Exam,date_exam,duree,num_Pile);
            ae.add(e);
            cr.moveToNext();
        }
        cr.close();
        return ae;
    }
    //Retourner les num d'un Exam
    public ArrayList<String> allNumExamString()
    {

        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Exam.Table_Exam,null);
        cr.moveToFirst();
        ArrayList<String> am=new ArrayList<String>();
        while (cr.isAfterLast()==false) {
            String num = cr.getString(cr.getColumnIndex(Contrat.Table_Exam.Column_num_Exam));
            am.add(num);
            cr.moveToNext();
        }
        cr.close();
        return am;
    }
    /****************************************************************************************/
     /*                                     Autres méthode
     /* *************************************************************************************/
    //Fonction qui vérifier l'existance du login et pass
    public int verMetier(String login,String pass)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cr=db.rawQuery("select * from "+
                Contrat.Table_Etudiant.Table_Etudiant+
                " where "+Contrat.Table_Etudiant.Column_num_etudiant+
                " = '"+login
                +"' and "+Contrat.Table_Etudiant.Column_pass+" = '"+pass+"'",null);

        if(cr.getCount()>0)
        {
            cr.close();
            return 1;
        }
        cr.close();
        Cursor cr1=db.rawQuery("select * from "+
                Contrat.Table_Compte.Table_Compte+
                " where "+Contrat.Table_Compte.Column_mot_de_passe+
                " = '"+pass+"' and "+Contrat.Table_Compte.Column_num_prof+" = '"+login+"'",null);
        if(cr1.getCount()>0)
        {
            cr1.close();
            return 2;
        }
        cr1.close();
        return -1;
    }
}
