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
import com.ronomicro.exame.adapters.Madapter;
import com.ronomicro.exame.adapters.MadapterModule;
import com.ronomicro.exame.database.Contrat;
import com.ronomicro.exame.database.DataBaseAide;

import java.util.ArrayList;

/**
 * Created by AMINE on 05/05/2017.
 */

public class module extends AppCompatActivity {
    EditText num;
    EditText nom;
    EditText numM;
    EditText nomM;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.transition.left_in, R.transition.left_out);
        setContentView(R.layout.activity_module);
        setTitle("Modules");
        /***************************************************************************/
        /*                       Initialisation des views                          */
        /***************************************************************************/
        num=(EditText)findViewById(R.id.editTextNum);
        nom=(EditText)findViewById(R.id.editTextNom);
        numM=(EditText)findViewById(R.id.editTextNumMod);
        nomM=(EditText)findViewById(R.id.editTextNomMod);
        /***************************************************************************/
        /*                       Configuration du TabHost                          */
        /***************************************************************************/
        TabHost th=(TabHost)findViewById(R.id.th1);
        th.setup();
        //Tab 1 :
        TabHost.TabSpec tc=th.newTabSpec("tag1");
        tc.setContent(R.id.tab1);
        tc.setIndicator("Ajouter");
        th.addTab(tc);
        //Tab 2 :
        TabHost.TabSpec tc1=th.newTabSpec("tag2");
        tc1.setContent(R.id.tab2);
        tc1.setIndicator("Modifier");
        th.addTab(tc1);
        //Tab 3 :
        TabHost.TabSpec tc2=th.newTabSpec("tag3");
        tc2.setContent(R.id.tab3);
        tc2.setIndicator("Supprimer");
        th.addTab(tc2);

        /***************************************************************************/
        /*                  Remplissage des élément du list view                   */
        /***************************************************************************/
        th.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if(s.equals("tag3"))
                {
                    Context c = module.this;
                    DataBaseAide db=new DataBaseAide(c, Contrat.Base.Basename,Contrat.Base.version);
                    ArrayList<Module> mo=db.allModule();
                    MadapterModule ma=new MadapterModule(mo,c);
                    ListView lv=(ListView)findViewById(R.id.lvmodule);
                    lv.setAdapter(ma);
                }

            }
        });

    }
    /***************************************************************************/
    /*                  Actualisation des élément du list view                 */
    /***************************************************************************/
    public  void  actulaise()
    {
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        ArrayList<Module> mo=db.allModule();
        MadapterModule ma=new MadapterModule(mo,this);
        ListView lv=(ListView)findViewById(R.id.lvmodule);
        lv.setAdapter(ma);
    }
    /***************************************************************************/
    /*                 Insertion des élément dans la base                      */
    /***************************************************************************/
    public void insertModule(View view) {
        if(!num.getText().toString().equals(""))
        {
            DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
            db.insModule(num.getText().toString(),nom.getText().toString());
            Toast.makeText(this, "Insertion effectué", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Le champ numero est vide", Toast.LENGTH_SHORT).show();
        }
    }
    /***************************************************************************/
    /*                 Modification des élément dans la base                   */
    /***************************************************************************/
    public void modifierMod(View view) {
        Module m=new Module();
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        m=db.infoModule(numM.getText().toString());
        if(!m.getNumM().toString().equals(""))
        {
            db.modiModule(numM.getText().toString(),nomM.getText().toString());
            Toast.makeText(this,"Modification  effectué", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Modification non effectué", Toast.LENGTH_SHORT).show();
        }
    }
    /***************************************************************************/
    /*                   Recherche d'un élément dans la base                   */
    /***************************************************************************/
    public void Chercher(View view) {
        Module m=new Module();
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        m=db.infoModule(numM.getText().toString());
        if(!m.getNumM().toString().equals(""))
        {
            nomM.setText(m.getNomM().toString());
        }
        else
        {
            Toast.makeText(this,"Aucun module trouvé", Toast.LENGTH_SHORT).show();
        }
    }
}
