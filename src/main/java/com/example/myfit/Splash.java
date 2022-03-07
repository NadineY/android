package com.example.myfit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;


public class Splash extends AppCompatActivity {
    ProgressBar progressBar;
    int duree=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_spash );


        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility( View.VISIBLE);

        Thread mythread=new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(duree);
                    Intent intent=new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        mythread.start();
    }
}
