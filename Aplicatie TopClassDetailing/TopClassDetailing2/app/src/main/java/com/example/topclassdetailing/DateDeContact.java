package com.example.topclassdetailing;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class DateDeContact extends AppCompatActivity {

    Button btninapoi;
    VideoView video2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_de_contact);

        video2 = (VideoView) findViewById(R.id.video2);
        btninapoi = (Button) findViewById(R.id.inapaoiinmeniulprincipal);

        Button buttonInapoipentruMeniulPrincipal = findViewById(R.id.inapaoiinmeniulprincipal);
        buttonInapoipentruMeniulPrincipal.setOnClickListener(v -> {
            Intent intent = new Intent(this,Meniul_Principal.class);
            startActivity(intent);
        });

        String path = "android.resource://com.example.topclassdetailing/"+R.raw.video_prezentare_topclassdetailing;
        Uri u = Uri.parse(path);
        video2.setVideoURI(u);
        video2.start();

        video2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    @Override
    protected void onResume(){
        video2.resume();
        super.onResume();
    }

    @Override
    protected void onPause(){
        video2.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy(){
        video2.stopPlayback();
        super.onDestroy();
    }
}