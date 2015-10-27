package com.dam.profesor.navigationdrawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentCamera.OnFragmentInteractionListener,
        FragmentGaleria.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean FragmentTransaction = false;
        Fragment fragment = null;


        if (id == R.id.nav_camara) {
            // Handle the camera action
            fragment = new FragmentCamera();
            FragmentTransaction = true;
        } else if (id == R.id.nav_gallery) {
            fragment = new FragmentGaleria();
            FragmentTransaction = true;

        } else if (id == R.id.nav_slideshow) {
            Log.i("NavigationDrawer","Entro en opción slideshow");
            getSupportActionBar().setTitle("Slide Show");

        } else if (id == R.id.nav_manage) {
            Log.i("NavigationDrawer","Entro en opción manage");

        } else if (id == R.id.nav_share) {

            SharedPreferences pref =
                    PreferenceManager.getDefaultSharedPreferences(this);

            Log.i("NavigationDrawer", "Opción 1: " + pref.getBoolean("opcion1", false));
            Log.i("NavigationDrawer", "Opción 2: " + pref.getString("opcion2", ""));
            Log.i("NavigationDrawer", "Opción 3: " + pref.getString("opcion3", ""));


            //Log.i("NavigationDrawer","Entro en opción share");

        } else if (id == R.id.preferencias) {

            startActivity(new Intent(this,Preferencias.class));
            Log.i("NavigationDrawer","Entro en opción preferencias");

        }

        if(FragmentTransaction) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main, fragment)
                    .commit();

            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
