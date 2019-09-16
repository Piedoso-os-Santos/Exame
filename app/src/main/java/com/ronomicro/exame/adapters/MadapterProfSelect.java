package com.ronomicro.exame.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ronomicro.exame.ClassedeBase.Professeur;
import com.ronomicro.exame.ClassedeBase.Specialite;
import com.ronomicro.exame.R;
import com.ronomicro.exame.database.Contrat;
import com.ronomicro.exame.database.DataBaseAide;
import com.ronomicro.exame.gestion.GestionCompte;
import com.ronomicro.exame.specialite;

import java.util.ArrayList;

/**
 * Created by AMINE on 04/05/2017.
 */

public class MadapterProfSelect extends BaseAdapter {
    //row est une class qui représente l’objet à afficher
    ArrayList<Professeur> list=new ArrayList<Professeur>();
    Context context;
    public MadapterProfSelect(ArrayList<Professeur> list, Context c){
        this.list=list;
        this.context=c;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.item_grid_prof_select,null);
        final TextView tnum=(TextView)v.findViewById(R.id.titreselect);
        final int ctr=i;
        Button btn=(Button)v.findViewById(R.id.btnSelect);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              final String n=list.get(ctr).getNum_prof().toString();
                try{
                    DataBaseAide db=new DataBaseAide(context, Contrat.Base.Basename,Contrat.Base.version);
                      db.updateProfEtudiant(GestionCompte.loginCompte,n);
                    Toast.makeText(context, "Professeur selectionner", Toast.LENGTH_SHORT).show();

                }catch(Exception ex)
                {
                    Toast.makeText(context, "Suppression non effectué", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tnum.setText("Professeur  "+list.get(i).getNom_prof() +"  "+list.get(i).getPrenom_prof());
        return v;
    }

}
