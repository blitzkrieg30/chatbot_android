package com.tutorial.chatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private EditText email1,password1;
    private Button btnSignup;
    private TextView textRegister1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth=FirebaseAuth.getInstance();
        email1=findViewById(R.id.signup_email);
        password1=findViewById(R.id.signup_password);
        btnSignup=findViewById(R.id.signup_button);
        textRegister1=findViewById(R.id.text_register1);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               register();
            }
        });

       textRegister1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(Register.this,login.class));
           }
       });
            
   

    }

    private void register() {

        String user=email1.getText().toString().trim();
        String pass=password1.getText().toString().trim();
        if(user.isEmpty()){
            email1.setError("Email cannot be empty.");
        }
        if(pass.isEmpty()){
            password1.setError("Password cannot be empty.");
        }
        else{
            firebaseAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Register.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this,login.class));
                    }
                    else
                    {
                        Toast.makeText(Register.this, "Registration Unsuccessful"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}