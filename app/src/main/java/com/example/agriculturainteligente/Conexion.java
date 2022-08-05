package com.example.agriculturainteligente;


import android.bluetooth.BluetoothA2dp;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import ingenieria.jhr.bluetoothjhr.BluetoothJhr;

public class Conexion extends AppCompatActivity {

    ListView dispositivos;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion);

        dispositivos = findViewById(R.id.dispositivos);

        final BluetoothJhr blue= new BluetoothJhr(this, dispositivos, menu_sensores.class);

        blue.onBluetooth();

        dispositivos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>adapterView, View view, int i, long l){
                blue.bluetoothSeleccion(i);
            }
        });
    }

}
