package com.example.topclassdetailing;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Meniul_Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meniul_principal);


        Button buttonSetari = findViewById(R.id.Setari);
        buttonSetari.setOnClickListener(v -> {
            Intent intent = new Intent(this, InProgress.class);
            startActivity(intent);
        });

       Button buttonServicii = findViewById(R.id.serviciii);
        buttonServicii.setOnClickListener(v -> {
            Intent intent = new Intent(this, InProgress.class);
            startActivity(intent);
        });

        Button buttonProgramare = findViewById(R.id.programare);
        buttonProgramare.setOnClickListener(v -> {
            Intent intent = new Intent(this, InProgress.class);
            startActivity(intent);
        });

        Button buttonInformatii = findViewById(R.id.contact);
        buttonInformatii.setOnClickListener(v -> {
            Intent intent = new Intent(this, DateDeContact.class);
            startActivity(intent);
        });

        Button buttonGPS = findViewById(R.id.GPS);
        buttonGPS.setOnClickListener(v -> {
            Intent intent = new Intent(this, GPS_pentru_TopClassDetailing.class);
            startActivity(intent);
        });

        Button buttonInapoi2 = findViewById(R.id.inpaoiinmeniulautentificareinregistrare);
        buttonInapoi2.setOnClickListener(v -> {
            Intent intent = new Intent(this, Meniu_Autentificare_Inregistrare.class);
            startActivity(intent);
        });
    }
}