package com.example.topclassdetailing;

import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
public class Autentificare extends AppCompatActivity {
    EditText username,password;
    Button btnlogin,inapoi;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autentificare);
        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        inapoi = (Button) findViewById(R.id.InapoiInMeniu) ;
        DB = new DBHelper(this);
        btnlogin.setOnClickListener(v->{
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if (user.equals("")||pass.equals(""))
                Toast.makeText(Autentificare.this, "Completati toate campurile", Toast.LENGTH_SHORT).show();
            else{
                Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                if(checkuserpass==true){
                    Toast.makeText(Autentificare.this, "Autentificare cu succes", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this ,Meniul_Principal.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Autentificare.this, "Numele si Parola invalide", Toast.LENGTH_SHORT).show();
                }
            }
        });
        inapoi.setOnClickListener(v->{
            Intent intent = new Intent(this ,Meniu_Autentificare_Inregistrare.class);
            startActivity(intent);
        });
    }
}