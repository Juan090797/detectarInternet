package com.example.detectarinternet;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BDAction {
    //private DBHelper dbHelp;
    //private SQLiteDatabase db;

    //Abrimos la base de datos 'DBTest1' en modo escritura
    /*DBHelper  dbHelp = new DBHelper(this, "DBTest1", null, 1);
    SQLiteDatabase db = dbHelp.getWritableDatabase();

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
        }*/
}
