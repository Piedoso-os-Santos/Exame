package com.ronomicro.exame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by AMINE on 01/05/2017.
 */

public class demmarage extends AppCompatActivity {


    private final int SPLASH_DISPLAY_LENGTH = 3000;
    ProgressBar mProgress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dammarage);/*
        ImageView iv=(ImageView)findViewById(R.id.imageView2);
        iv.setImageResource(R.drawable.logo);*/
         mProgress = (ProgressBar) findViewById(R.id.progressBar);
        /*new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity.
                Intent mainIntent = new Intent(demmarage.this,welcome.class);
                demmarage.this.startActivity(mainIntent);
                demmarage.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH); */

        // Start lengthy operation in a background thread
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
            Intent intent = new Intent(demmarage.this, welcome.class);
            startActivity(intent);
        }

    }


