package com.ronomicro.exame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ronomicro.exame.ClassedeBase.Professeur;
import com.ronomicro.exame.adapters.MadapterProfSelect;
import com.ronomicro.exame.database.Contrat;
import com.ronomicro.exame.database.DataBaseAide;

import java.util.ArrayList;

/**
 * Created by AMINE on 04/05/2017.
 */

public class professeur_select extends AppCompatActivity {
    ProgressBar mProgress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professeur_select);
        setTitle("Choix du professeur");
        DataBaseAide d=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        ArrayList<Professeur> ap=d.allProf();
        MadapterProfSelect p=new MadapterProfSelect(ap,this);
        ListView lv=(ListView)findViewById(R.id.lvProfSelect);
        lv.setAdapter(p);
    }
    }

