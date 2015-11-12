package com.dam.profesor.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Profesor on 06/11/2015.
 */
public class BDAlumnos extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Alumnos (codigo INTEGER, nombre TEXT)";
    String sqlCreate2 = "CREATE TABLE Alumnos (codigo INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, direccion TEXT)";

    public BDAlumnos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Si no existe la base de datos la crea y ejecuta los siguientes comandos
        db.execSQL(sqlCreate);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Alumnos");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate2);

    }


}
