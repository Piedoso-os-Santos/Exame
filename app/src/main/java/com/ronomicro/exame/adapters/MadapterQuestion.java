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
import com.ronomicro.exame.ClassedeBase.Question;
import com.ronomicro.exame.R;
import com.ronomicro.exame.database.Contrat;
import com.ronomicro.exame.database.DataBaseAide;
import com.ronomicro.exame.module;
import com.ronomicro.exame.question;

import java.util.ArrayList;

/**
 * Created by AMINE on 04/05/2017.
 */

public class MadapterQuestion extends BaseAdapter {
    //row est une class qui représente l’objet à afficher
    ArrayList<Question> list=new ArrayList<Question>();
    Context context;
    public MadapterQuestion(ArrayList<Question> list, Context c){
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
        View v=inflater.inflate(R.layout.item_grid_ques,null);
        final TextView tnum=(TextView)v.findViewById(R.id.titreQues);
        final int ctr=i;
        Button btn=(Button)v.findViewById(R.id.bsuppmQues);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              final String n=list.get(ctr).getNum_Question().toString();
                try{
                    DataBaseAide db=new DataBaseAide(context, Contrat.Base.Basename,Contrat.Base.version);
                    db.delQuestion(n);
                    ((question)context).actulaise();
                    Toast.makeText(context, "Suppression  effectué", Toast.LENGTH_SHORT).show();

                }catch(Exception ex)
                {
                    Toast.makeText(context, "Suppression non effectué", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tnum.setText("Question n° "+list.get(i).getNum_Question() +" dans le pile n° "+list.get(i).getNum_Pile()+" : "+list.get(i).getText_Question());
        return v;
    }

}
