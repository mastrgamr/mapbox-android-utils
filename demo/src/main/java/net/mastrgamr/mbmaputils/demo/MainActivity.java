package net.mastrgamr.mbmaputils.demo;

import android.location.Location;
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

import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationListener;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import net.mastrgamr.mbmapboxutils.clustering.ClusterManager;
import net.mastrgamr.mbmaputils.R;
import net.mastrgamr.mbmaputils.demo.MapboxDevPreview.MyItem;
import net.mastrgamr.mbmaputils.demo.MapboxDevPreview.MyItemReader;

import org.json.JSONException;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, LocationListener {

    private final String TAG = getClass().getSimpleName();
    private MapView map;
    private MapboxMap mbMap;
    Marker marker;
    MarkerOptions markOpt;

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

        markOpt = new MarkerOptions().position(new LatLng(40.755963, -73.985585)).title("Eh");
        marker = new Marker(markOpt);

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
    protected void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        map.onLowMemory();
    }
    private ClusterManager<MyItem> mClusterManager;
    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        Log.d(TAG, "onMapReady: READY!");
        this.mbMap = mapboxMap;
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

        mapboxMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));

        mClusterManager = new ClusterManager<MyItem>(this, mapboxMap);
        mapboxMap.setOnCameraChangeListener(mClusterManager);

        try {
            readItems();
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of markers.", Toast.LENGTH_LONG).show();
        }

//        mbMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.755963, -73.985585), 9));
//        mbMap.addMarker(markOpt);
    }

    private void readItems() throws JSONException {
        InputStream inputStream = getResources().openRawResource(R.raw.radar_search);
        List<MyItem> items = new MyItemReader().read(inputStream);
        mClusterManager.addItems(items);
    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
