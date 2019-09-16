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

import com.ronomicro.exame.ClassedeBase.Module;
import com.ronomicro.exame.ClassedeBase.Question;
import com.ronomicro.exame.adapters.MadapterModule;
import com.ronomicro.exame.adapters.MadapterQuestion;
import com.ronomicro.exame.database.Contrat;
import com.ronomicro.exame.database.DataBaseAide;

import java.util.ArrayList;

/**
 * Created by AMINE on 05/05/2017.
 */

public class question extends AppCompatActivity {
    EditText num;
    EditText ques;
    Spinner numPile;
    EditText numMo;
    EditText questionMo;
    EditText numPileMo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.transition.left_in, R.transition.left_out);
        setContentView(R.layout.activity_question);
        setTitle("Questions");
        /***************************************************************************/
        /*                       Initialisation des views                          */
        /***************************************************************************/
        num=(EditText)findViewById(R.id.editTextNumQ);
        ques=(EditText)findViewById(R.id.editTextDescQ);
        numPile=(Spinner) findViewById(R.id.s1q);
        numMo=(EditText)findViewById(R.id.editTextNumQM);
        questionMo=(EditText)findViewById(R.id.editTextDescQM);
        numPileMo=(EditText)findViewById(R.id.editTextNumPileQuM);
        /***************************************************************************/
        /*                       Remplissage du spinner                            */
        /***************************************************************************/
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        ArrayList<String> l=db.allNumPileString();
        ArrayAdapter<String> aasp=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, l);
        aasp.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        numPile.setAdapter(aasp);

        /***************************************************************************/
        /*                       Configuration du TabHost                          */
        /***************************************************************************/
        TabHost th=(TabHost)findViewById(R.id.tabquestion);
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
                    Context c = question.this;
                    DataBaseAide db=new DataBaseAide(c, Contrat.Base.Basename,Contrat.Base.version);
                    ArrayList<Question> mo=db.allQuestion();
                    MadapterQuestion ma=new MadapterQuestion(mo,c);
                    ListView lv=(ListView)findViewById(R.id.lvQuestion);
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
        ArrayList<Question> mo=db.allQuestion();
        MadapterQuestion ma=new MadapterQuestion(mo,this);
        ListView lv=(ListView)findViewById(R.id.lvQuestion);
        lv.setAdapter(ma);
    }
    /***************************************************************************/
    /*                 Insertion des élément dans la base                      */
    /***************************************************************************/
    public void insertQuestion(View view) {
        if(!num.getText().toString().equals(""))
        {
            DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
            db.insQuestion(num.getText().toString(),ques.getText().toString(),numPile.getSelectedItem().toString());
            Toast.makeText(this, "Insertion effectué", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Le champ numero est vide", Toast.LENGTH_SHORT).show();
        }
    }
    /***************************************************************************/
    /*                 Modification des éléments dans la base                   */
    /***************************************************************************/
    public void modifierQuestion(View view) {
        Question m=new Question();
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        m=db.infoQuestion(numMo.getText().toString());
        if(!m.getNum_Question().toString().equals(""))
        {
            db.modiQuestion(numMo.getText().toString(),questionMo.getText().toString(),numPileMo.getText().toString());
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
    public void ChercherQuestion(View view) {
        Question m=new Question();
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        m=db.infoQuestion(numMo.getText().toString());;
        if(!m.getNum_Question().toString().equals(""))
        {
            questionMo.setText(m.getText_Question().toString());
            numPileMo.setText(m.getNum_Pile().toString());
        }
        else
        {
            Toast.makeText(this,"Aucun module trouvé", Toast.LENGTH_SHORT).show();
        }
    }
}
