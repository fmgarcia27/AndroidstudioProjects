package com.dam.profesor.basedatos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button actualizar;
    Button borrar;
    Button insertar;
    EditText editText;
    SQLiteDatabase db;
    Button consultar;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actualizar = (Button)findViewById(R.id.actualizar);
        borrar = (Button)findViewById(R.id.borrar);
        insertar = (Button)findViewById(R.id.insertar);
        editText = (EditText)findViewById(R.id.editText);
        consultar = (Button)findViewById(R.id.consultar);
        textView = (TextView)findViewById(R.id.resultado);

        actualizar.setOnClickListener(this);
        borrar.setOnClickListener(this);
        insertar.setOnClickListener(this);
        consultar.setOnClickListener(this);


        //Abrimos la base de datos 'BDAlumnos' en modo escritura
        BDAlumnos alumnos =
                new BDAlumnos(this, "BDAlumnos", null, 2);

        db = alumnos.getWritableDatabase();

        //Comrpuebo que la BD se abre correctamente
        if(db != null)
        {

            for(int i=1; i<=10; i++)
            {
                //Generar valores
                int codigo = i;
                String nombreAlumno = "Alumno" + i;

                //Datos de prueba
                db.execSQL("INSERT INTO Alumnos (nombre) " +
                        "VALUES ('" + nombreAlumno + "')");
            }

            //Cerrar BD
            //db.close();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.actualizar:
                //Actualizar un registro
                db.execSQL("UPDATE Alumnos SET nombre='" + editText.getText() + "' WHERE codigo=5" );
                break;
            case R.id.borrar:
                //Eliminar un registro
                db.execSQL("DELETE FROM Alumnos WHERE nombre='" + editText.getText() + "'");
                break;

            case R.id.insertar:
                //Insertar un registro
                db.execSQL("INSERT INTO Alumnos (nombre,direccion) VALUES ('" + editText.getText() + "','direccion de prueba') ");
                break;

            case R.id.consultar:
                String[] args = new String[] {editText.getText().toString()};
                Cursor c = db.rawQuery(" SELECT * FROM Alumnos WHERE nombre=? ", args);
                if (c.moveToFirst()) {
                    textView.setText(""); // Vacio el textview
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        Integer codigo= c.getInt(0);
                        String nombre = c.getString(1);
                        String direccion = c.getString(2);
                        textView.append("Codigo: " + codigo + " Nombre: " + nombre + " Dirección: " + direccion + "\n");
                    } while(c.moveToNext());
                }

                break;

            default:

                break;
        }
    }
}
