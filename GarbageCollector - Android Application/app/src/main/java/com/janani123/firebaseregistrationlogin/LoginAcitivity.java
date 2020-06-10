package com.janani123.firebaseregistrationlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginAcitivity extends AppCompatActivity {

    private EditText txtEmailLogin;
    private EditText txtpwd;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmailLogin = findViewById(R.id.textEmailLogin);
        txtpwd = findViewById(R.id.textPasswordLogin);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void btnUserLogin_Click(View v) {
        final ProgressDialog progressDialog = ProgressDialog.show(LoginAcitivity.this, "please wait...", "Processing..", true);

        (firebaseAuth.signInWithEmailAndPassword(txtEmailLogin.getText().toString(), txtpwd.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginAcitivity.this, "Login successful", Toast.LENGTH_LONG).show();

                            String aa = firebaseAuth.getCurrentUser().getEmail();
                            {
                                System.out.println(aa);
                                if (aa.equalsIgnoreCase("janani7848@gmail.com")) {

                                    Intent i = new Intent(LoginAcitivity.this, MapsActivity.class);
                                    final Intent intent = i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                    startActivity(i);
                                }
                                else {

                                    Intent i = new Intent(LoginAcitivity.this, userActivity.class);
                                    final Intent intent = i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                    startActivity(i);


                                }
                            }

                        }

                            else {
                            Log.e("ERROR", task.getException().toString());

                            Toast.makeText(LoginAcitivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}













