package com.dam.profesor.accesoficheros;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button leermeminterna;
    Button escribirmeminterna;
    Button leersd;
    Button escribirsd;
    Button leerprograma;
    TextView textView;
    boolean sdDisponible = false;
    boolean sdAccesoEscritura = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        leermeminterna=(Button)findViewById(R.id.leermeminterna);
        escribirmeminterna=(Button)findViewById(R.id.escribirmeminterna);
        leersd=(Button)findViewById(R.id.leersd);
        escribirsd=(Button)findViewById(R.id.escribirsd);
        leerprograma=(Button)findViewById(R.id.leerprograma);

        leermeminterna.setOnClickListener(this);
        escribirmeminterna.setOnClickListener(this);
        leersd.setOnClickListener(this);
        escribirsd.setOnClickListener(this);
        leerprograma.setOnClickListener(this);
        textView = (TextView)findViewById(R.id.textView);

        // Código que me comprueba si existe SD y si puedo escribir o no
        String estado = Environment.getExternalStorageState();

        if (estado.equals(Environment.MEDIA_MOUNTED))
        {
            sdDisponible = true;
            sdAccesoEscritura = true;
        }
        else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
        {
            sdDisponible = true;
            sdAccesoEscritura = false;
        }
        else
        {
            sdDisponible = false;
            sdAccesoEscritura = false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case(R.id.leermeminterna):

                try
                {
                    BufferedReader fin =
                            new BufferedReader(
                                    new InputStreamReader(
                                            openFileInput("meminterna.txt")));

                    String texto = fin.readLine();
                    textView.setText(texto);
                    fin.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al leer fichero desde la memoria interna");
                }

                break;
            case(R.id.escribirmeminterna):

                try
                {
                    OutputStreamWriter fout=
                            new OutputStreamWriter(
                                    openFileOutput("meminterna.txt", Context.MODE_PRIVATE));

                    fout.write("Contenido del fichero de memoria interna.");
                    fout.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
                }

                break;
            case(R.id.leersd):

                if(sdDisponible){

                    try
                    {
                        File ruta_sd = Environment.getExternalStorageDirectory();

                        File f = new File(ruta_sd.getAbsolutePath(), "ficherosd.txt");

                        BufferedReader fin =
                                new BufferedReader(
                                        new InputStreamReader(
                                                new FileInputStream(f)));

                        String texto = fin.readLine();
                        textView.setText(texto);
                        fin.close();
                    }
                    catch (Exception ex)
                    {
                        Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
                    }

                }

                break;
            case(R.id.escribirsd):

                if(sdAccesoEscritura && sdDisponible){
                    try
                    {
                        File ruta_sd = Environment.getExternalStorageDirectory();

                        File f = new File(ruta_sd.getAbsolutePath(), "ficherosd.txt");

                        OutputStreamWriter fout =
                                new OutputStreamWriter(
                                        new FileOutputStream(f));

                        fout.write("Contenido del fichero de la SD");
                        fout.close();
                    }
                    catch (Exception ex)
                    {
                        Log.e("Ficheros", "Error al escribir fichero en la tarjeta SD");
                    }
                }

                break;
            case(R.id.leerprograma):

                try
                {
                    InputStream fraw =
                            getResources().openRawResource(R.raw.ficheroraw);

                    BufferedReader brin =
                            new BufferedReader(new InputStreamReader(fraw));

                    String linea = brin.readLine();
                    textView.setText(linea);

                    fraw.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al leer fichero desde recurso raw");
                }

                break;
            default:
                break;


        }

    }
}
