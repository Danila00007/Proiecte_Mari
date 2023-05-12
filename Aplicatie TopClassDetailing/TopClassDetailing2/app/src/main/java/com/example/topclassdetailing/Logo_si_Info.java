package com.example.topclassdetailing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

public class Logo_si_Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_logo_si_info);

        Button buttonLogin = findViewById(R.id.Login);

        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this,Meniu_Autentificare_Inregistrare.class);
            startActivity(intent);
        });

        Button buttonInfo = findViewById(R.id.Informatii);

        buttonInfo.setOnClickListener(v -> {
            Intent intent = new Intent(this, Video_Logo.class);
            startActivity(intent);
        });
    }
}