package net.mastrgamr.mbmaputils.demo.MapboxDevPreview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class GeoJsonFragment extends Fragment implements
        OnMapReadyCallback{

    private final String TAG = getClass().getSimpleName();
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;
    private MapView map;
    private MapboxMap mbMap;

    public GeoJsonFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment GeoJsonFragment.
     */
    public static GeoJsonFragment newInstance(String param1) {
        GeoJsonFragment fragment = new GeoJsonFragment();
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
        View v = inflater.inflate(R.layout.fragment_geo_json, container, false);

        map = (MapView)v.findViewById(R.id.mapview);
        map.onCreate(savedInstanceState);
        map.getMapAsync(this);
        map.setStyleUrl(Style.MAPBOX_STREETS);
        
        return v;
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {

        mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.501008, -122.672824), 11.5f));

        GeoJsonLayer layer = null;
        try {
            layer = new GeoJsonLayer(mapboxMap, R.raw.portland_line, getContext());
            layer.addLayerToMap();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
