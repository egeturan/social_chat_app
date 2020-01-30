package com.example.hugo.danismanchat2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hugo.danismanchat2.RegistrationProcesses.FaceActivity;
import com.example.hugo.danismanchat2.RegistrationProcesses.LoginActivity;
import com.example.hugo.danismanchat2.RegistrationProcesses.PhoneLoginActivity;
import com.example.hugo.danismanchat2.RegistrationProcesses.RegisterActivity;

public class MainController extends AppCompatActivity {

    private Button buttonFace, buttonPhoneNum, buttonLogin, buttonRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_controller);


        InitializeField();



        buttonFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent faceLoginIntent = new Intent(MainController.this, FaceActivity.class);
                startActivity(faceLoginIntent);
            }
        });

        buttonPhoneNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(MainController.this, PhoneLoginActivity.class);
                startActivity(mainIntent);
            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(MainController.this, LoginActivity.class);
                startActivity(mainIntent);
            }
        });


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(MainController.this, RegisterActivity.class);
                startActivity(mainIntent);
            }
        });









    }

    private void InitializeField() {
        buttonFace = (Button) findViewById(R.id.buttonC1);
        buttonPhoneNum = (Button) findViewById(R.id.buttonC2);
        buttonLogin = (Button) findViewById(R.id.buttonC3);
        buttonRegister = (Button) findViewById(R.id.buttonC4);
    }





}
