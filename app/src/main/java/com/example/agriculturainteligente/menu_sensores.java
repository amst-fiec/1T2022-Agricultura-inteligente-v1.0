package com.example.agriculturainteligente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menu_sensores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sensores);
    }

    public void Atras(View view){
        Intent menuPrincipal = new Intent(this, menu_principal.class);
        startActivity(menuPrincipal);
    }
}