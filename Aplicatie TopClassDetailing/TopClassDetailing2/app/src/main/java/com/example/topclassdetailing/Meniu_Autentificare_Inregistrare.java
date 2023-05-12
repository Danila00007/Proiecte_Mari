package com.example.topclassdetailing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.widget.Button;

public class Meniu_Autentificare_Inregistrare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meniu_autentificare_inregistrare);


        Button buttonAutentificare = findViewById(R.id.autentificaremeniu);
        buttonAutentificare.setOnClickListener(v -> {
            Intent intent = new Intent(this, Autentificare.class);
            startActivity(intent);
        });

        Button buttonInregistrare = findViewById(R.id.inregistraremeniu);
        buttonInregistrare.setOnClickListener(v -> {
            Intent intent = new Intent(this, inregistrare.class);
            startActivity(intent);
        });

        Button buttonInapoi = findViewById(R.id.inapoi_in_Logo_si_Info);
        buttonInapoi.setOnClickListener(v -> {
            Intent intent = new Intent(this, Logo_si_Info.class);
            startActivity(intent);
        });
    }
}