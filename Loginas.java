package com.example.nitesh.smartgarbagetracksystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

/**
 * Created by Nitesh on 21-Jan-18.
 */

public class Loginas extends AppCompatActivity {

    private CardView workerId,UserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginas);
        workerId=(CardView)findViewById(R.id.workerId);
        UserId=(CardView)findViewById(R.id.userId);

        workerId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 =new Intent("android.intent.action.WORKERLOGIN");
                startActivity(i1);
            }
        });
        UserId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 =new Intent("android.intent.action.WORKERDASHBOARD");
                startActivity(i2);
            }
        });
    }


}
