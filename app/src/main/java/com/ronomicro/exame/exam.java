package com.ronomicro.exame;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import com.ronomicro.exame.ClassedeBase.Exam;
import com.ronomicro.exame.ClassedeBase.Pile;
import com.ronomicro.exame.adapters.MadapterExam;
import com.ronomicro.exame.adapters.MadapterPile;
import com.ronomicro.exame.database.Contrat;
import com.ronomicro.exame.database.DataBaseAide;

import java.util.ArrayList;

/**
 * Created by AMINE on 05/05/2017.
 */

public class exam extends AppCompatActivity {
    EditText num_Exam;
    EditText date_exam;
    EditText duree;
    Spinner num_Pile;

    EditText num_ExamMod;
    EditText date_examMod;
    EditText dureeMod;
    EditText num_PileMod;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        setTitle("Exams");


       num_Exam=(EditText)findViewById(R.id.editTextNumExam);
       date_exam=(EditText)findViewById(R.id.editTextDateExam);
       duree=(EditText)findViewById(R.id.editTextDureeExam);
       num_Pile=(Spinner)findViewById(R.id.spinnerExamPile);

        num_ExamMod=(EditText)findViewById(R.id.editTextNumExamModif);
        date_examMod=(EditText)findViewById(R.id.editTextDateExamModif);
        dureeMod=(EditText)findViewById(R.id.editTextDureeExamModif);
        num_PileMod=(EditText)findViewById(R.id.editTextNumExamPile);

        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        ArrayList<String> l=db.allNumPileString();
        ArrayAdapter<String> aasp=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, l);
        aasp.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        num_Pile.setAdapter(aasp);

        TabHost th=(TabHost)findViewById(R.id.th3);
        th.setup();

        TabHost.TabSpec tc=th.newTabSpec("tag1");
        tc.setContent(R.id.tab1);
        tc.setIndicator("Ajouter");
        th.addTab(tc);

        TabHost.TabSpec tc1=th.newTabSpec("tag2");
        tc1.setContent(R.id.tab2);
        tc1.setIndicator("Modifier");
        th.addTab(tc1);

        TabHost.TabSpec tc2=th.newTabSpec("tag3");
        tc2.setContent(R.id.tab3);
        tc2.setIndicator("Supprimer");
        th.addTab(tc2);
        th.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if(s.equals("tag3"))
                {
                    Context c = exam.this;
                    DataBaseAide db=new DataBaseAide(c, Contrat.Base.Basename,Contrat.Base.version);
                    ArrayList<Exam> mo=db.allExam();
                    MadapterExam ma=new MadapterExam(mo,c);
                    ListView lv=(ListView)findViewById(R.id.lvExam);
                    lv.setAdapter(ma);
                }
            }
        });

    }
    public  void  actulaise()
    {
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        ArrayList<Exam> mo=db.allExam();
        MadapterExam ma=new MadapterExam(mo,this);
        ListView lv=(ListView)findViewById(R.id.lvExam);
        lv.setAdapter(ma);
    }

    public void insertExam(View view) {
        if(!num_Exam.getText().toString().equals(""))
        {
            DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
            db.insExam(num_Exam.getText().toString(),date_exam.getText().toString(),duree.getText().toString(),num_Pile.getSelectedItem().toString());
            Toast.makeText(this,"Insertion effectué",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Le champ numero est vide", Toast.LENGTH_SHORT).show();
        }
    }

    public void modifierExam(View view) {
        Exam e=new Exam();
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        e=db.infoExam(num_Exam.getText().toString());
        if(!e.getNum_Exam().toString().equals(""))
        {
            db.modiExam(num_ExamMod.getText().toString(),date_examMod.getText().toString(),dureeMod.getText().toString(),num_PileMod.getText().toString());
            Toast.makeText(this,"Modification  effectué", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Modification non effectué", Toast.LENGTH_SHORT).show();
        }
    }

    public void ChercherExam(View view) {
        Exam m=new Exam();
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        m=db.infoExam(num_ExamMod.getText().toString());
        if(!m.getNum_Exam().toString().equals(""))
        {
            date_examMod.setText(m.getDate_exam().toString());
            dureeMod.setText(m.getDuree().toString());
            num_PileMod.setText(m.getNum_Pile().toString());
        }
        else
        {
            Toast.makeText(this,"Aucun module trouvé", Toast.LENGTH_SHORT).show();
        }
    }
}
