package com.janani123.firebaseregistrationlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnRegistration_click(View v){
        Intent i = new Intent(MainActivity.this,RegistrationActivity.class);
        startActivity(i);
    }

    public void btnLogin_click(View v){
        Intent i = new Intent(MainActivity.this,LoginAcitivity.class);
        startActivity(i);

    }
}
