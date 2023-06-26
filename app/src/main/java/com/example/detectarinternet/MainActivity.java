package com.example.detectarinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonValidar;
    private Button buttonOff;
    private SQLiteDatabase db;
    private DBHelper dbHelp;
    private static final String TAG = "hola";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonValidar = (Button) findViewById(R.id.buttonValidar);
        buttonOff = (Button) findViewById(R.id.buttonOff);

        buttonValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InternetService.class);
                startService(intent);
                Toast.makeText(MainActivity.this, "SERVICIO ON", Toast.LENGTH_SHORT).show();
            }
        });

        buttonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InternetService.class);
                stopService(intent);
                Toast.makeText(MainActivity.this, "SERVICIO OFF", Toast.LENGTH_SHORT).show();

            }
        });

        //Abrimos la base de datos 'DBTest1' en modo escritura
        //dbHelp = new DBHelper(MainActivity.this, "DBTest1", null, 1);
        //db = dbHelp.getWritableDatabase();

    }

    public void create() {
        //Abrimos la base de datos 'DBTest1' en modo escritura
        dbHelp = new DBHelper(MainActivity.this, "DBTest1", null, 1);
        db = dbHelp.getWritableDatabase();
        Log.d(TAG,"entro 1");
        Log.d(TAG,""+db);
        //Toast.makeText(MainActivity.this, "entro 1", Toast.LENGTH_SHORT).show();
        //Si hemos abierto correctamente la base de datos
        if (db != null) {
            Log.d(TAG,"entro 2");
            //Creamos el registro a insertar como objeto ContentValues
            ContentValues nuevoRegistro = new ContentValues();
            // El ID es auto incrementable como declaramos en nuestro CarsSQLiteHelper
            nuevoRegistro.put("name", "Seat");
            nuevoRegistro.put("color", "Black");

            //Insertamos el registro en la base de datos
            db.insert("Cars", null, nuevoRegistro);
        }
    }

    public void removeAll() {
        //Abrimos la base de datos 'DBTest1' en modo escritura
        dbHelp = new DBHelper(MainActivity.this, "DBTest1", null, 1);
        db = dbHelp.getWritableDatabase();
        db.delete("Cars", "", null);
    }
}