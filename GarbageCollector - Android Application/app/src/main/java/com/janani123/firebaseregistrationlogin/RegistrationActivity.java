package com.janani123.firebaseregistrationlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class RegistrationActivity extends AppCompatActivity {

    private EditText txtEmailAddress;
    private EditText txtPassword;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        txtEmailAddress = findViewById(R.id.textEmailRegistration);
        txtPassword = findViewById(R.id.textPasswordRegistration);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void btnRegistrationUser_Click(View v){
       final ProgressDialog progressDialog = ProgressDialog.show(RegistrationActivity.this, "Please wait...", "Processing...", true);
        (firebaseAuth.createUserWithEmailAndPassword(txtEmailAddress.getText().toString(), txtPassword.getText().toString()))
                 .addOnCompleteListener(new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                          progressDialog.dismiss();

                          if (task.isSuccessful()) {
                              Toast.makeText(RegistrationActivity.this, "Registration Successfull", Toast.LENGTH_LONG).show();
                              Intent i = new Intent(RegistrationActivity.this, LoginAcitivity.class);
                              startActivity(i);
                          }

                          else {
                              Log.e("ERROR", task.getException().toString());
                              Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                          }
                     }
                 });
        }
    }

