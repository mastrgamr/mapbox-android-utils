package net.mastrgamr.mbmaputils.demo.MapboxDevPreview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import net.mastrgamr.mbmaputils.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PolyDecodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PolyDecodeFragment extends Fragment implements
        OnMapReadyCallback {

    private final String TAG = getClass().getSimpleName();
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;
    private MapView map;
    private MapboxMap mbMap;


    public PolyDecodeFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment PolyDecodeFragment.
     */
    public static PolyDecodeFragment newInstance(String param1) {
        PolyDecodeFragment fragment = new PolyDecodeFragment();
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
        View v =  inflater.inflate(R.layout.fragment_poly_decode, container, false);

        map = (MapView)v.findViewById(R.id.mapview);
        map.onCreate(savedInstanceState);
        map.getMapAsync(this);
        map.setStyleUrl(Style.MAPBOX_STREETS);

        return v;
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
    }
}
