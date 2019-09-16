package com.ronomicro.exame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }
public void con(View view){
    Intent a=new Intent(this,connection.class);
    startActivity(a);
}
    public void ins(View view){
        Intent b=new Intent(this,inscription.class);
        startActivity(b);
    }
}
