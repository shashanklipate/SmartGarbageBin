package com.example.nitesh.smartgarbagetracksystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.nitesh.smartgarbagetracksystem.R.id.et_password;
import static com.example.nitesh.smartgarbagetracksystem.R.id.email;
import static com.example.nitesh.smartgarbagetracksystem.R.id.fabclose;

public class Workerregister extends AppCompatActivity {

    private FirebaseAuth auth;
    private FloatingActionButton fabclose;
    private ProgressBar progressBar;
    private Button clickonme;
    private EditText etemail,etusername,etpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workerregister);

        auth = FirebaseAuth.getInstance();
       // auth = (());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        clickonme =(Button)findViewById(R.id.bt_clickonme);
        //etemail = (EditText)findViewById(R.id.et_email);
        etusername =(EditText)findViewById(R.id.email);
        etpassword =(EditText)findViewById(R.id.et_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        fabclose = (FloatingActionButton)findViewById(R.id.fabclose);
        fabclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("android.intent.action.WORKERLOGIN");
                startActivity(i);
            }
        });


        clickonme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String email = etemail.getText().toString().trim();
                String username = etusername.getText().toString().trim();
                String password = etpassword.getText().toString().trim();
                //if (TextUtils.isEmpty(email)) {
                  //  Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_SHORT).show();
                    //return;
                //}
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getApplicationContext(), "Enter your username!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(username, password)
                        .addOnCompleteListener(Workerregister.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Workerregister.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Workerregister.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(Workerregister.this, Workerlogin.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
