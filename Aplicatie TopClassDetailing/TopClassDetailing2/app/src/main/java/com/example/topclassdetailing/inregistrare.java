package com.example.topclassdetailing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class inregistrare extends AppCompatActivity  {

    EditText username, password, repassword;
    Button singup, singin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inregistrare);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        singup = (Button) findViewById(R.id.btnsignup);
        singin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        singup.setOnClickListener(v-> {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(inregistrare.this, "Completati toate campurile", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(inregistrare.this, "Inregistrare cu succes", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(this , Meniul_Principal.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(inregistrare.this, "Inregistrare esuata", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(inregistrare.this, "Numele exista deja! Va rugam Autentificativa", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(inregistrare.this, "Parola nu se potriveste", Toast.LENGTH_SHORT).show();
                    }
                }
        });




        singin.setOnClickListener(v->{
            Intent intent = new Intent(this , Autentificare.class);
            startActivity(intent);
        });
    }}