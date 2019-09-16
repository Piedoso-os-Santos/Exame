package com.ronomicro.exame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.ronomicro.exame.ClassedeBase.Exam;
import com.ronomicro.exame.ClassedeBase.Professeur;
import com.ronomicro.exame.adapters.MadapterProfSelect;
import com.ronomicro.exame.adapters.Madapter_pass_exam;
import com.ronomicro.exame.database.Contrat;
import com.ronomicro.exame.database.DataBaseAide;

import java.util.ArrayList;

/**
 * Created by AMINE on 04/05/2017.
 */

public class exam_select extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_select);
        setTitle("Passage d'un examen");
        DataBaseAide d=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        ArrayList<Exam> ap=d.allExam();
        Madapter_pass_exam p=new Madapter_pass_exam(ap,this);
        ListView lv=(ListView)findViewById(R.id.lvExamSelect);
        lv.setAdapter(p);
    }
    }

