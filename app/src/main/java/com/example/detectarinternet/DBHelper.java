package com.example.detectarinternet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla Cars
    String sqlCreate = "CREATE TABLE Cars (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, color TEXT)";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int prevVersion, int newVersion) {

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Cars");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}
