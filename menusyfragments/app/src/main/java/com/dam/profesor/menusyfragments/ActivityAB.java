package com.dam.profesor.menusyfragments;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ActivityAB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_ab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_ab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Context context = getApplicationContext();
        CharSequence text = null;
        int duration = Toast.LENGTH_SHORT;

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            text="Opcion Settings";
            //return true;
        }
        else if(id==R.id.opcion2){
            text="Opcion2";
            //return true;
        }
        else if(id==R.id.opcion3){
            text="Opcion3";
            //return true;
        }
        else if(id==R.id.opcion4){
            text="Opcion4";
            //return true;
        }
        else if(id==R.id.opcion5){
            text="Opcion5";
            //return true;
        }

        Toast toast = Toast.makeText(context,text,duration);
        toast.show();

        return super.onOptionsItemSelected(item);
    }
}
