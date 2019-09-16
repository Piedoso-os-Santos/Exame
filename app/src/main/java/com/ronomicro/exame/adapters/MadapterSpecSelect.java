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

import java.util.ArrayList;

/**
 * Created by AMINE on 04/05/2017.
 */

public class MadapterSpecSelect extends BaseAdapter {
    //row est une class qui représente l’objet à afficher
    ArrayList<Specialite> list=new ArrayList<Specialite>();
    Context context;
    public MadapterSpecSelect(ArrayList<Specialite> list, Context c){
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
              final String n=list.get(ctr).getNumS().toString();
                try{
                    DataBaseAide db=new DataBaseAide(context, Contrat.Base.Basename,Contrat.Base.version);
                      db.updateSpecEtudiant(GestionCompte.loginCompte,n);
                    Toast.makeText(context, "Specialite  selectionner", Toast.LENGTH_SHORT).show();

                }catch(Exception ex)
                {
                    Toast.makeText(context, "Specialite non effectué", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tnum.setText("Specilaite : "+list.get(i).getNomS() );
        return v;
    }

}
