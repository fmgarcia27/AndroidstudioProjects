package com.dam.profesor.layout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button boton1;
    Button boton2;
    Button boton3;
    Button boton4;
    Button boton5;
    TextView etiqueta;
    EditText cuadro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton1 = (Button)findViewById(R.id.button);
        boton2 = (Button)findViewById(R.id.button2);
        boton3 = (Button)findViewById(R.id.button3);
        boton4 = (Button)findViewById(R.id.button4);
        boton5 = (Button)findViewById(R.id.button5);
        etiqueta = (TextView)findViewById(R.id.textView);
        cuadro = (EditText)findViewById(R.id.editText);

        //INLINE
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Context context = getApplicationContext();
                CharSequence text = "Botón 1 pulsado!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

            //DELEGATE
        boton2.setOnClickListener(this);
        boton4.setOnClickListener(this);
        boton5.setOnClickListener(this);


    }

    public void onClick(View v){
        Context context = getApplicationContext();
        Toast toast;
        CharSequence text;
        String texto;

        int duration = Toast.LENGTH_SHORT;
        switch(v.getId())
        {
            case(R.id.button2):
                text = "Botón 2 pulsado!";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                break;
            case(R.id.button3):
                text = "Botón 3 pulsado!";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                break;
            case(R.id.button4):
                text = "Botón 4 pulsado!";
                texto = etiqueta.getText().toString();
                texto = texto + " Usuario";
                etiqueta.setText(texto);
                break;
            case(R.id.button5):
                text = "Botón 4 pulsado!";
                texto = cuadro.getText().toString();
                texto = texto + "ABC";
                cuadro.setText(texto);
                break;

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
}
