package com.ronomicro.exame.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ronomicro.exame.R;
import com.ronomicro.exame.profile;

import java.util.ArrayList;

/**
 * Created by AMINE on 04/05/2017.
 */

public class Madapter extends BaseAdapter {
    //row est une class qui représente l’objet à afficher
    ArrayList<row> list=new ArrayList<row>();
    Context context;
    public Madapter(ArrayList<row> list,Context c){
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
        View v=inflater.inflate(R.layout.item_grid,null);
        final TextView tn=(TextView)v.findViewById(R.id.titre);
        ImageButton img=(ImageButton)v.findViewById(R.id.imageButton);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tn.getText().toString().equals("Modules"))
                {
                    ((profile)context).module();
                }
                if(tn.getText().toString().equals("Specialités"))
                {
                    ((profile)context).specs();
                }
                if(tn.getText().toString().equals("Piles"))
                {
                    ((profile)context).pile();
                }
                if(tn.getText().toString().equals("Questions"))
                {
                    ((profile)context).question();
                }
                if(tn.getText().toString().equals("Réponses"))
                {
                    ((profile)context).reponse();
                }
                if(tn.getText().toString().equals("Examens"))
                {
                    ((profile)context).exam();
                }
                if(tn.getText().toString().equals("Professeur"))
                {
                    ((profile)context).prof();
                }
                if(tn.getText().toString().equals("Spécialité"))
                {
                    ((profile)context).spec();
                }
                if(tn.getText().toString().equals("Exam"))
                {
                    ((profile)context).passExam();
                }
            }
        });
        tn.setText(list.get(i).getTitre());
        img.setImageResource(list.get(i).getImg());
        return v;
    }

}
