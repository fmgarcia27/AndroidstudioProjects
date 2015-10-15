package com.dam.profesor.singletouch;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto = (TextView)findViewById(R.id.text);

        texto.setOnTouchListener(this);

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
    public boolean onTouch(View v, MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);
        int corx = (int)event.getX();
        int cody = (int)event.getY();

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                texto.setText("La pulsación ha sido DOWN en la posición: "+ corx + ", " + cody);
                return true;
            case (MotionEvent.ACTION_MOVE):
                texto.setText("La pulsación ha sido MOVE en la posición: "+ corx + ", " + cody);
                return true;
            case (MotionEvent.ACTION_UP):
                texto.setText("La pulsación ha sido UP en la posición: "+ corx + ", " + cody);
                return true;
            case (MotionEvent.ACTION_CANCEL):
                texto.setText("La pulsación ha sido ACTION_CANCEL en la posición: "+ corx + ", " + cody);
                return true;
            case (MotionEvent.ACTION_OUTSIDE):
                texto.setText("La pulsación ha sido ACTION_OUTSIDE en la posición: "+ corx + ", " + cody);
                return true;
            default:
                return true;
        }


    }
}
