package net.mastrgamr.mbmaputils.demo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import net.mastrgamr.mbmaputils.R;
import net.mastrgamr.mbmaputils.demo.MapboxDevPreview.GeoJsonFragment;
import net.mastrgamr.mbmaputils.demo.MapboxDevPreview.PolyDecodeFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private final String TAG = getClass().getSimpleName();

    private final int CLUSTERING_FRAGMENT = 0;
    private final int DISTANCE_FRAGMENT   = 1;
    private final int GEOJSON_FRAGMENT    = 2;
    private final int HEATMAPS_FRAGMENT   = 3;
    private final int POLYDECODE_FRAGMENT = 4;
    private int currentFragment = 0; //could be enum buttfukitt

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        markOpt = new MarkerOptions().position(new LatLng(40.755963, -73.985585)).title("Eh");
//        marker = new Marker(markOpt);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction =
//                fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.content, PolyDecodeFragment.newInstance("Test", mbMap));
//        fragmentTransaction.commit();

//Disable for now | TODO: Change locations/geogson/etc something else
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("REMOVING");
//                mbMap.removeMarker(markOpt.getMarker());
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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
        int id = item.getItemId();

        if (id == R.id.nav_clustering) {
            if (currentFragment != CLUSTERING_FRAGMENT) {
                currentFragment = CLUSTERING_FRAGMENT;
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.content, ClusteringFragment.newInstance("Test"));
                ft.addToBackStack(null);
                ft.commit();
            }
        } else if (id == R.id.nav_distance) {
            if (currentFragment != DISTANCE_FRAGMENT) {
                currentFragment = DISTANCE_FRAGMENT;
            }
            Toast.makeText(this, "Coming Soon.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_geojson) {
            if (currentFragment != GEOJSON_FRAGMENT) {
                currentFragment = GEOJSON_FRAGMENT;
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.content, GeoJsonFragment.newInstance("Test"));
                ft.addToBackStack(null);
                ft.commit();
            }
            //Toast.makeText(this, "Coming Soon.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_heatmaps) {
            if (currentFragment != HEATMAPS_FRAGMENT) {
                currentFragment = HEATMAPS_FRAGMENT;
            }
            Toast.makeText(this, "Coming Soon.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_polydecode) {
            if (currentFragment != POLYDECODE_FRAGMENT) {
                currentFragment = POLYDECODE_FRAGMENT;
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.content, PolyDecodeFragment.newInstance("Test"));
                ft.addToBackStack(null);
                ft.commit();
            }
            //Toast.makeText(this, "Coming Soon.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        Log.d(TAG, "onMapReady: READY!");

        mapboxMap.setMyLocationEnabled(true);
//        this.mbMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.501008, -122.672824), 12f));
//
//        GeoJsonLayer layer = null;
//        try {
//            layer = new GeoJsonLayer(mbMap, R.raw.portland_line, this);
//            layer.addLayerToMap();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        mbMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.755963, -73.985585), 9));
//        mbMap.addMarker(markOpt);
    }
}
