package com.ronomicro.exame.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ronomicro.exame.ClassedeBase.Module;
import com.ronomicro.exame.ClassedeBase.Pile;
import com.ronomicro.exame.R;
import com.ronomicro.exame.database.Contrat;
import com.ronomicro.exame.database.DataBaseAide;
import com.ronomicro.exame.module;
import com.ronomicro.exame.pile;

import java.util.ArrayList;

/**
 * Created by AMINE on 04/05/2017.
 */

public class MadapterPile extends BaseAdapter {
    //row est une class qui représente l’objet à afficher
    ArrayList<Pile> list=new ArrayList<Pile>();
    Context context;
    public MadapterPile(ArrayList<Pile> list, Context c){
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
        View v=inflater.inflate(R.layout.item_grid_pile,null);
        final TextView tnum=(TextView)v.findViewById(R.id.titrePile);
        final int ctr=i;
        Button btn=(Button)v.findViewById(R.id.bsuppmPile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              final String n=list.get(ctr).getNumP().toString();
                try{
                    DataBaseAide db=new DataBaseAide(context, Contrat.Base.Basename,Contrat.Base.version);
                    db.delPile(n);
                    ((pile)context).actulaise();

                }catch(Exception ex)
                {
                    Toast.makeText(context, "Suppression non effectué", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tnum.setText("Pile n° "+list.get(i).getNumP() +" (M"+list.get(i).getNumModu()+") : "+list.get(i).getDescP());
        return v;
    }

}
