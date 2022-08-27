package com.example.agriculturainteligente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class menu_principal extends AppCompatActivity {

    TextView txt_id, txt_name, txt_email;
    ImageView imv_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_menu_principal);

        Intent intent = getIntent();
        HashMap<String, String> info_user = (HashMap<String, String>)intent.getSerializableExtra("info_user");


        txt_name = findViewById(R.id.txt_nombre);
        //txt_email = findViewById(R.id.txt_correo);
        //imv_photo = findViewById(R.id.imv_foto);

        //txt_id.setText(info_user.get("user_id"));
        txt_name.setText("Bienvenido "+info_user.get("user_name"));
        //txt_email.setText(info_user.get("user_email"));
        //String photo = info_user.get("user_photo");
        //Picasso.with(getApplicationContext()).load(photo).into(imv_photo);

    }

    public void cardSensor(View view){
        Intent menuSensores = new Intent(this, menu_sensores.class);
        startActivity(menuSensores);
    }

    public void cerrarSesion(View view){
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("msg", "cerrarSesion");
        startActivity(intent);
    }


}