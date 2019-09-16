package com.ronomicro.exame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by AMINE on 04/05/2017.
 */

public class passage_entre extends AppCompatActivity {
    ProgressBar mProgress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passage_entre);
        setTitle("");
        mProgress = (ProgressBar) findViewById(R.id.progressBar11);

        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }
    private void doWork() {
        for (int progress=0; progress<100; progress+=1) {
            try {
                Thread.sleep(20);
                mProgress.setProgress(progress);
            } catch (Exception e) {

                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startApp() {
        Intent intent = new Intent(this, profile.class);
        Bundle e1=getIntent().getExtras();
        Bundle b=new Bundle();
        b.putString("login",e1.getString("login"));
        b.putString("pass",e1.getString("pass"));
        b.putString("pr",e1.getString("pr"));
        intent.putExtras(b);
        startActivity(intent);
    }
    }

