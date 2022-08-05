package com.example.agriculturainteligente;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.termometro_create.TermometroJhr;

import ingenieria.jhr.bluetoothjhr.BluetoothJhr;

public class menu_sensores extends AppCompatActivity {

    Boolean initHilo = false;
    Boolean hilo = true;
    BluetoothJhr blue;
    String mensaje;
    TextView display;
    TermometroJhr termometroJhr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sensores);

        blue = new BluetoothJhr(this, Conexion.class);
        display = findViewById(R.id.display);
        termometroJhr = findViewById(R.id.termometroJhr);


        termometroJhr.tempMax(100);
        termometroJhr.tempMin(0);
        termometroJhr.setTimeAnimation(3000f);

        new Thread(new Runnable () {
            @Override
            public void run() {
                while (!initHilo){
                    delay(500L);
                }
                while (hilo){
                    blue.mTx("t");
                    delay(1000L);
                    mensaje = blue.mRx();
                    if (mensaje!=""){
                        if(hilo){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    display.setText(mensaje);
                                    termometroJhr.tempSet(Float.parseFloat(mensaje));
                                }
                            });
                        }else{
                            break;
                        }
                        blue.mensajeReset();
                    }
                    delay(1000L);
                }
                System.out.println("----------termino hilo---------");
            }
        }).start();
    }

    public void delay(Long time){
        try{
            Thread.sleep(time);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initHilo = blue.conectaBluetooth();
    }

    @Override
    protected void onPause() {
        super.onPause();
        hilo = false;
        initHilo = true;
        blue.exitConexion();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        initHilo = true;
        hilo = false;
    }
}