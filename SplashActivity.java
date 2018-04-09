package com.example.nitesh.smartgarbagetracksystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(){
            public void run(){
                try{
                    sleep(4500);
                    Intent i=new Intent("android.intent.action.LOGINAS");
                    startActivity(i);
                }
                catch(InterruptedException e){
                    System.out.println("Error" +e);
                }

            }

        }.start();
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
