package com.ronomicro.exame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ronomicro.exame.database.Contrat;
import com.ronomicro.exame.database.DataBaseAide;

/**
 * Created by AMINE on 01/04/2017.
 */

public class inscription extends AppCompatActivity {
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;
    private RadioButton r1;
    private RadioButton r2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        this.setTitle("Inscription");
        t1=(TextView)findViewById(R.id.editText1);
        t2=(TextView)findViewById(R.id.editText2);
        t3=(TextView)findViewById(R.id.editText3);
        t4=(TextView)findViewById(R.id.editText4);
        t5=(TextView)findViewById(R.id.editText5);
        r1=(RadioButton)findViewById(R.id.radioButton1);
        r2=(RadioButton)findViewById(R.id.radioButton2);
    }

    public void insc(View view) {
        String nom=t1.getText().toString();
        String prenom=t2.getText().toString();
        String login=t3.getText().toString();
        String pass=t4.getText().toString();
        String pass1=t5.getText().toString();
        boolean etu=r1.isChecked();
        if(etu)
        {
            DataBaseAide da=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
            da.InsertEtudiant(login,nom,prenom,pass);
            Intent ie=new Intent(this,connection.class);
            startActivity(ie);
        }
        else
        {
            DataBaseAide da=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
            da.InsertProf(login,nom,prenom,pass);
            Intent ie=new Intent(this,connection.class);
            startActivity(ie);
        }

    }
}
