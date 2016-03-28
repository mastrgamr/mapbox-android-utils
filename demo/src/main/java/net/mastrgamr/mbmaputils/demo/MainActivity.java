package net.mastrgamr.mbmaputils.demo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import net.mastrgamr.mbmapboxutils.geojson.GeoJsonLayer;
import net.mastrgamr.mbmaputils.R;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private final String TAG = getClass().getSimpleName();
    private MapView map;
    private MapboxMap mbMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        map = (MapView)findViewById(R.id.mapview);
        map.onCreate(savedInstanceState);
        map.getMapAsync(this);
        map.setStyleUrl(Style.MAPBOX_STREETS);

//Disable for now | TODO: Change locations/geogson/etc something else
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_geojson) {
            // Handle the action
        } else if (id == R.id.nav_distance) {
            Toast.makeText(this, "Coming Soon.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_heatmaps) {
            Toast.makeText(this, "Coming Soon.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_polydecode) {
            Toast.makeText(this, "Coming Soon.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_clustering) {
            Toast.makeText(this, "Coming Soon.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        map.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        map.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        map.onLowMemory();
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        Log.d(TAG, "onMapReady: READY!");
        this.mbMap = mapboxMap;
        this.mbMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.501008, -122.672824), 12f));

        GeoJsonLayer layer = null;
        try {
            layer = new GeoJsonLayer(mbMap, R.raw.portland_line, this);
            layer.addLayerToMap();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
