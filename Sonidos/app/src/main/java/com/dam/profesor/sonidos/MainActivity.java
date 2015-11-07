package com.dam.profesor.sonidos;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button boton1;
    Button boton2;
    Button boton3;
    MediaPlayer mediaPlayer;
    SoundPool soundPool;
    int carga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boton1 = (Button)findViewById(R.id.button);
        boton2 = (Button)findViewById(R.id.button2);
        boton3 = (Button)findViewById(R.id.button3);

        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);

        soundPool = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        carga = soundPool.load(this,R.raw.sound37,1);

        mediaPlayer = new MediaPlayer();

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

        switch (v.getId()){

            case R.id.button:
                play_mp();
                //mediaPlayer.start();
                break;
            case R.id.button2:
                stop_mp();
                //mediaPlayer.stop();
                break;
            case R.id.button3:
                soundPool.play(carga,1,1,0,0,1);
                break;
            default:

                break;



        }
    }
        private void play_mp() {
            Thread playThread = new Thread() {
                public void run()
                {
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.musica);
                    mediaPlayer.start();
                }
            };

            playThread.start();
        }
        private void stop_mp() {
// TODO Auto-generated method stub
            mediaPlayer.stop();
        }




}
