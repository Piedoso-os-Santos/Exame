package com.ronomicro.exame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.ronomicro.exame.ClassedeBase.Professeur;
import com.ronomicro.exame.ClassedeBase.Specialite;
import com.ronomicro.exame.adapters.MadapterProfSelect;
import com.ronomicro.exame.adapters.MadapterSpecSelect;
import com.ronomicro.exame.database.Contrat;
import com.ronomicro.exame.database.DataBaseAide;

import java.util.ArrayList;

/**
 * Created by AMINE on 04/05/2017.
 */

public class spec_select extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spec_select);
        setTitle("Choix du Spécalité");
        DataBaseAide d=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        ArrayList<Specialite> ap=d.allSpec();
        MadapterSpecSelect p=new MadapterSpecSelect(ap,this);
        ListView lv=(ListView)findViewById(R.id.lvSpecSelect);
        lv.setAdapter(p);
    }
    }

