package com.ronomicro.exame;

import android.content.Context;
import android.database.Cursor;
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

import com.ronomicro.exame.ClassedeBase.Module;
import com.ronomicro.exame.ClassedeBase.Pile;
import com.ronomicro.exame.adapters.MadapterModule;
import com.ronomicro.exame.adapters.MadapterPile;
import com.ronomicro.exame.database.Contrat;
import com.ronomicro.exame.database.DataBaseAide;

import java.util.ArrayList;

/**
 * Created by AMINE on 05/05/2017.
 */

public class pile extends AppCompatActivity {
    EditText num;
    EditText desc;
    EditText numM;
    EditText  descM;
    EditText numModM;
    Spinner s;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pile);
        setTitle("Piles");
        num=(EditText)findViewById(R.id.editTextNumPile);
        desc=(EditText)findViewById(R.id.editTextDescPile);
        numM=(EditText)findViewById(R.id.editTextNumPileM);
        descM=(EditText)findViewById(R.id.editTextDescPileM);
        numModM=(EditText)findViewById(R.id.editTextNumModuleM);

        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        s=(Spinner)findViewById(R.id.s1);
        ArrayList<String> l=db.allNumModuleString();
        ArrayAdapter<String> aasp=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, l);
        aasp.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        s.setAdapter(aasp);

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
                    Context c = pile.this;
                    DataBaseAide db=new DataBaseAide(c, Contrat.Base.Basename,Contrat.Base.version);
                    ArrayList<Pile> mo=db.allPile();
                    MadapterPile ma=new MadapterPile(mo,c);
                    ListView lv=(ListView)findViewById(R.id.lvPile);
                    lv.setAdapter(ma);
                }

            }
        });

    }
    public  void  actulaise()
    {
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        ArrayList<Pile> mo=db.allPile();
        MadapterPile ma=new MadapterPile(mo,this);
        ListView lv=(ListView)findViewById(R.id.lvPile);
        lv.setAdapter(ma);;
    }

    public void insertPile(View view) {
        if(!num.getText().toString().equals(""))
        {
            DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
            db.insPile(num.getText().toString(),desc.getText().toString(),s.getSelectedItem().toString());
            Toast.makeText(this, "Insertion effectué", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Le champ numero est vide", Toast.LENGTH_SHORT).show();
        }

    }

    public void modifierPile(View view) {
        Pile m=new Pile();
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        m=db.infoPile(numM.getText().toString());
        if(!m.getNumP().toString().equals(""))
        {
            db.modiPile(numM.getText().toString(),descM.getText().toString(),numModM.getText().toString());
            Toast.makeText(this,"Modification  effectué", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Modification non effectué", Toast.LENGTH_SHORT).show();
        }
    }

    public void ChercherPile(View view) {
        Pile m=new Pile();
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        m=db.infoPile(numM.getText().toString());
        if(!m.getNumModu().toString().equals(""))
        {
            descM.setText(m.getDescP().toString());
            numModM.setText(m.getNumModu().toString());
        }
        else
        {
            Toast.makeText(this,"Aucun module trouvé", Toast.LENGTH_SHORT).show();
        }
    }
}
