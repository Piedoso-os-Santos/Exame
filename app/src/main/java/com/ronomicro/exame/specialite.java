package com.ronomicro.exame;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.ronomicro.exame.ClassedeBase.Module;
import com.ronomicro.exame.ClassedeBase.Specialite;
import com.ronomicro.exame.adapters.MadapterModule;
import com.ronomicro.exame.adapters.MadapterSpec;
import com.ronomicro.exame.database.Contrat;
import com.ronomicro.exame.database.DataBaseAide;

import java.util.ArrayList;

/**
 * Created by AMINE on 05/05/2017.
 */

public class specialite extends AppCompatActivity {
    EditText num;
    EditText nom;
    EditText numS;
    EditText nomS;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.transition.left_in, R.transition.left_out);
        setContentView(R.layout.activity_specialite);
        setTitle("Spécilaités");
        num=(EditText)findViewById(R.id.editTextNSpec);
        nom=(EditText)findViewById(R.id.editTextNmSpec);
        numS=(EditText)findViewById(R.id.editTextNumSpec);
        nomS=(EditText)findViewById(R.id.editTextNomSpec);
        TabHost th=(TabHost)findViewById(R.id.th2);
        th.setup();

        TabHost.TabSpec tc=th.newTabSpec("tag1");
        tc.setContent(R.id.tab1S);
        tc.setIndicator("Ajouter");
        th.addTab(tc);

        TabHost.TabSpec tc1=th.newTabSpec("tag2");
        tc1.setContent(R.id.tab2S);
        tc1.setIndicator("Modifier");
        th.addTab(tc1);

        TabHost.TabSpec tc2=th.newTabSpec("tag3");
        tc2.setContent(R.id.tab3S);
        tc2.setIndicator("Supprimer");
        th.addTab(tc2);
        th.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if(s.equals("tag3"))
                {
                    Context c = specialite.this;
                    DataBaseAide db=new DataBaseAide(c, Contrat.Base.Basename,Contrat.Base.version);
                    ArrayList<Specialite> mo=db.allSpec();
                    MadapterSpec ma=new MadapterSpec(mo,c);
                    ListView lv=(ListView)findViewById(R.id.lvspec);
                    lv.setAdapter(ma);
                }

            }
        });

    }
    public  void  actulaise()
    {
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        ArrayList<Specialite> mo=db.allSpec();
        MadapterSpec ma=new MadapterSpec(mo,this);
        ListView lv=(ListView)findViewById(R.id.lvspec);
        lv.setAdapter(ma);
    }

    public void insertSpec(View view) {
        if(!num.getText().toString().equals(""))
        {
            DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
            db.insSpec(num.getText().toString(),nom.getText().toString());
            Toast.makeText(this, "Insertion effectué", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Le champ numero est vide", Toast.LENGTH_SHORT).show();
        }

    }

    public void modifierSpec(View view) {
        Specialite s=new Specialite();
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        s=db.infoSpec(numS.getText().toString());
        if(!s.getNumS().toString().equals(""))
        {
            db.modiSpec(numS.getText().toString(),nomS.getText().toString());
            Toast.makeText(this,"Modification  effectué", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Modification non effectué", Toast.LENGTH_SHORT).show();
        }
    }

    public void ChercherSpec(View view) {
        Specialite s=new Specialite();
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        s=db.infoSpec(numS.getText().toString());
        if(!s.getNumS().toString().equals(""))
        {
            nomS.setText(s.getNomS().toString());
        }
        else
        {
            Toast.makeText(this,"Aucun module trouvé", Toast.LENGTH_SHORT).show();
        }
    }
}
