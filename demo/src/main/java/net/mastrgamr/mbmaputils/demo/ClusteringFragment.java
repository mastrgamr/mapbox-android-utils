package net.mastrgamr.mbmaputils.demo;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class ClusteringFragment extends Fragment implements
        OnMapReadyCallback, LocationListener {

    private final String TAG = getClass().getSimpleName();
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    private MapView map;
    private MapboxMap mbMap;

    private ClusterManager<MyItem> mClusterManager;

    public ClusteringFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment MainFragment.
     */
    public static ClusteringFragment newInstance(String param1) {
        ClusteringFragment fragment = new ClusteringFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        map = (MapView)v.findViewById(R.id.mapview);
        map.onCreate(savedInstanceState);
        map.getMapAsync(this);
        map.setStyleUrl(Style.MAPBOX_STREETS);

        return v;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        Log.d(TAG, "onMapReady (ClusteringFragment): READY!");

        this.mbMap = mapboxMap;
        mClusterManager = new ClusterManager<MyItem>(getContext(), mapboxMap);
        mapboxMap.setOnCameraChangeListener(mClusterManager);

        //51.5283064,-0.3824619
        mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.5069899,-0.1282133), 9.5f), 1500);


        try {
            readItems();
        } catch (JSONException e) {
            Toast.makeText(getContext(), "Problem reading list of markers.", Toast.LENGTH_LONG).show();
        }
    }

    private void readItems() throws JSONException {
        InputStream inputStream = getResources().openRawResource(R.raw.radar_search);
        List<MyItem> items = new MyItemReader().read(inputStream);
        mClusterManager.addItems(items);
    }
}
