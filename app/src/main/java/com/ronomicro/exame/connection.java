package com.ronomicro.exame;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ronomicro.exame.database.Contrat;
import com.ronomicro.exame.database.DataBaseAide;
import com.ronomicro.exame.gestion.GestionCompte;

/**
 * Created by AMINE on 29/03/2017.
 */

public class connection extends AppCompatActivity {
    TextView t1;
    TextView t2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        this.setTitle("Connexion");
        t1=(TextView)findViewById(R.id.editText1);
        t2=(TextView)findViewById(R.id.editText2);
    }

    public void conn(View view) {
        String login=t1.getText().toString();
        String pass=t2.getText().toString();
        DataBaseAide db=new DataBaseAide(this, Contrat.Base.Basename,Contrat.Base.version);
        int valid=db.verMetier(login,pass);
        if(valid==-1){
            Toast.makeText(this,"N'existe pas", Toast.LENGTH_SHORT).show();
        }
        /**********************************************************************************/
        /*                                Cas d'un étudiant                               */
        /**********************************************************************************/
        if(valid==1){
            GestionCompte.loginCompte=login;
            GestionCompte.passCompte=pass;
            GestionCompte.typeCompte="Etudiant";
            Intent e=new Intent(this,passage_entre.class);
            Bundle b=new Bundle();
            b.putString("login",login);
            b.putString("pass",pass);
            b.putString("pr","Etudiant");
            e.putExtras(b);
            startActivity(e);
        }
        /**********************************************************************************/
        /*                                Cas d'un professeur                             */
        /**********************************************************************************/
        if(valid==2){
            GestionCompte.loginCompte=login;
            GestionCompte.passCompte=pass;
            GestionCompte.typeCompte="Professeur";
            Intent e=new Intent(this,passage_entre.class);
            Bundle b=new Bundle();
            b.putString("login",login);
            b.putString("pass",pass);
            b.putString("pr","Professeur");
            e.putExtras(b);
            startActivity(e);
        }
    }
}
