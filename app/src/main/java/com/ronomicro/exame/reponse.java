package com.ronomicro.exame;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import com.ronomicro.exame.ClassedeBase.Pile;
import com.ronomicro.exame.ClassedeBase.Reponse;
import com.ronomicro.exame.adapters.MadapterPile;
import com.ronomicro.exame.adapters.MadapterReponse;
import com.ronomicro.exame.database.Contrat;
import com.ronomicro.exame.database.DataBaseAide;

import java.util.ArrayList;

/**
 * Created by AMINE on 05/05/2017.
 */

public class reponse extends AppCompatActivity {
    EditText numreponse;
    EditText reponse;
    Spinner numeroquestion;
    EditText numreponseM;
    EditText reponseM;
    EditText numeroquestionM;
    RadioButton rrv;
    RadioButton rrvf;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reponse);
        setTitle("Réponses");
         numreponse=(EditText)findViewById(R.id.editTextNumReponse);
         reponse=(EditText)findViewById(R.id.editTextReponse);
         numeroquestion=(Spinner)findViewById(R.id.sreponse);
         numreponseM=(EditText)findViewById(R.id.editTextNumReponseM);
         reponseM=(EditText)findViewById(R.id.editTextReponseM);
         numeroquestionM=(EditText)findViewById(R.id.editTextNumQuesionM1);
        rrv=(RadioButton)findViewById(R.id.radioreponsevraiM);
        rrvf=(RadioButton)findViewById(R.id.radioreponsevrai);
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        ArrayList<String> l=db.allNumQuestionString();
        ArrayAdapter<String> aasp=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, l);
        aasp.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        numeroquestion.setAdapter(aasp);

        TabHost th=(TabHost)findViewById(R.id.threponse);
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
                    Context c = reponse.this;
                    DataBaseAide db=new DataBaseAide(c, Contrat.Base.Basename,Contrat.Base.version);
                    ArrayList<Reponse> mo=db.allReponse();
                    MadapterReponse ma=new MadapterReponse(mo,c);
                    ListView lv=(ListView)findViewById(R.id.lvReponse);
                    lv.setAdapter(ma);
                }

            }
        });

    }
    public  void  actulaise()
    {
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        ArrayList<Reponse> mo=db.allReponse();
        MadapterReponse ma=new MadapterReponse(mo,this);
        ListView lv=(ListView)findViewById(R.id.lvReponse);
        lv.setAdapter(ma);;
    }

    public void insertReponse(View view) {
        if(!numreponse.getText().toString().equals(""))
        {
            DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
            String rep="false";
            boolean r=rrvf.isChecked();
            if(r==true)
                rep="true";
            db.insReponse(numreponse.getText().toString(),reponse.getText().toString(),rep,numeroquestion.getSelectedItem().toString());
            Toast.makeText(this, "Insertion effectué", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Le champ numero est vide", Toast.LENGTH_SHORT).show();
        }

    }

    public void modifierReponse(View view) {
        Reponse m=new Reponse();
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        m=db.infoReponse(numreponseM.getText().toString());
        if(!m.getNum_reponse().toString().equals(""))
        {
            String rep="false";
            if(rrv.isChecked())
                rep="true";
            db.modiReponse(numreponseM.getText().toString(),reponseM.getText().toString(),rep,numeroquestionM.getText().toString());
            Toast.makeText(this,"Modification  effectué", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Modification non effectué", Toast.LENGTH_SHORT).show();
        }
    }

    public void ChercherReponse(View view) {
        Reponse m=new Reponse();
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        m=db.infoReponse(numreponseM.getText().toString());
        if(!m.getNum_reponse().toString().equals(""))
        {
            reponseM.setText(m.getReponse().toString());
            numeroquestionM.setText(m.getNum_question().toString());
            if(m.getTrue_false().equals("true"))
                rrv.setChecked(true);
            else
                rrv.setChecked(false);
        }
        else
        {
            Toast.makeText(this,"Aucun module trouvé", Toast.LENGTH_SHORT).show();
        }
    }
}
