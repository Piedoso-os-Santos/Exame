package com.ronomicro.exame;


import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ronomicro.exame.ClassedeBase.Etudiant;
import com.ronomicro.exame.ClassedeBase.Professeur;
import com.ronomicro.exame.adapters.Madapter;
import com.ronomicro.exame.adapters.row;
import com.ronomicro.exame.database.Contrat;
import com.ronomicro.exame.database.DataBaseAide;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by AMINE on 30/04/2017.
 */

public class profile extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Profile");
        setContentView(R.layout.activity_profile);
        Bundle b=getIntent().getExtras();
        TextView t1=(TextView)findViewById(R.id.nomprofile);
        TextView t2=(TextView)findViewById(R.id.prprofile);
        ArrayList<row> l=new ArrayList<row>();

        row r1=new row();
        row r2=new row();
        row r3=new row();
        row r4=new row();
        row r5=new row();
        row r6=new row();

        String pr=b.get("pr").toString();
        /**********************************************************************************/
        /*                                Cas d'un étudiant                               */
        /**********************************************************************************/

        if(pr.equals("Etudiant"))
        {
            r1=new row("Spécialité",R.drawable.anchor3);
            r2=new row("Professeur",R.drawable.e1);
            r3=new row("Résultat",R.drawable.e2);
            r4=new row("Planning",R.drawable.e3);
            r5=new row("Exam",R.drawable.e4);
            r6=new row("Instruction",R.drawable.e5);
            DataBaseAide d=new DataBaseAide(this,Contrat.Base.Basename,Contrat.Base.version);
            Etudiant e=d.infoetudiant(b.getString("login"),b.getString("pass"));
            t1.setText(e.getNom_etudiant()+" "+e.getPrenom_etudiant());
            t2.setText(b.getString("pr"));
        }
        /**********************************************************************************/
        /*                                Cas d'un Professeur                               */
        /**********************************************************************************/
        if(pr.equals("Professeur"))
        {
            ImageView im=(ImageView)findViewById(R.id.imageViewProfile);
            im.setBackgroundResource(R.drawable.cover1);
             r1=new row("Modules",R.drawable.anchor);
             r2=new row("Piles",R.drawable.anchor0);
             r3=new row("Questions",R.drawable.anchor1);
            r4=new row("Examens",R.drawable.anchor2);
             r5=new row("Specialités",R.drawable.anchor3);
             r6=new row("Réponses",R.drawable.anchor4);

            DataBaseAide d=new DataBaseAide(this,Contrat.Base.Basename,Contrat.Base.version);
           Professeur p=d.infoprofesseur(b.getString("login"),b.getString("pass"));
            t1.setText(p.getNom_prof()+" "+p.getPrenom_prof());
            t2.setText(b.getString("pr"));
        }
        l.add(r1);
        l.add(r2);
        l.add(r3);
        l.add(r4);
        l.add(r5);
        l.add(r6);
        Madapter a=new Madapter(l,this);
        GridView fv=(GridView)findViewById(R.id.gv1);
        fv.setVerticalScrollBarEnabled(false);
        fv.setAdapter(a);

    }
    public  void module()
    {
        Intent e=new Intent(this,module.class);
        startActivity(e);

    }
    public  void specs()
    {
        Intent e=new Intent(this,specialite.class);
        startActivity(e);

    }
    public  void spec()
    {
        Intent e=new Intent(this,spec_select.class);
        startActivity(e);

    }
    public  void prof()
    {
        Intent e=new Intent(this,professeur_select.class);
        startActivity(e);

    }
    public  void pile()
    {
        Intent e=new Intent(this,pile.class);
        startActivity(e);

    }
    public  void question()
    {
        Intent e=new Intent(this,question.class);
        startActivity(e);

    }
    public  void reponse()
    {
        Intent e=new Intent(this,reponse.class);
        startActivity(e);

    }
    public  void exam()
    {
        Intent e=new Intent(this,exam.class);
        startActivity(e);

    }
    public void passExam() {
        Intent e=new Intent(this,exam_select.class);
        startActivity(e);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuactivity, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.decmeny:
                Intent e=new Intent(this,connection.class);
                startActivity(e);
                finish();
                return true;
            case R.id.profile:
                Intent a=new Intent(this,editprofile.class);
                startActivity(a);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
