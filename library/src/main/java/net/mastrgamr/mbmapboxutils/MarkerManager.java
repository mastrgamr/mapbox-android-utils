/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.mastrgamr.mbmapboxutils;

import android.view.View;

import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Keeps track of collections of markers on the map. Delegates all Marker-related events to each
 * collection's individually managed listeners.
 * <p/>
 * All marker operations (adds and removes) should occur via its collection class. That is, don't
 * add a marker via a collection, then remove it via Marker.remove()
 */
public class MarkerManager implements MapboxMap.OnInfoWindowClickListener, MapboxMap.OnMarkerClickListener, MapboxMap.InfoWindowAdapter {
    private final MapboxMap mMap;

    private final Map<String, Collection> mNamedCollections = new HashMap<String, Collection>();
    private final Map<Marker, Collection> mAllMarkers = new HashMap<Marker, Collection>();

    public MarkerManager(MapboxMap map) {
        this.mMap = map;
    }

    public Collection newCollection() {
        return new Collection();
    }

    /**
     * Create a new named collection, which can later be looked up by {@link #getCollection(String)}
     * @param id a unique id for this collection.
     */
    public Collection newCollection(String id) {
        if (mNamedCollections.get(id) != null) {
            throw new IllegalArgumentException("collection id is not unique: " + id);
        }
        Collection collection = new Collection();
        mNamedCollections.put(id, collection);
        return collection;
    }

    /**
     * Gets a named collection that was created by {@link #newCollection(String)}
     * @param id the unique id for this collection.
     */
    public Collection getCollection(String id) {
        return mNamedCollections.get(id);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        Collection collection = mAllMarkers.get(marker);
        if (collection != null && collection.mInfoWindowAdapter != null) {
            return collection.mInfoWindowAdapter.getInfoWindow(marker);
        }
        return null;
    }

    @Override
    public boolean onInfoWindowClick(Marker marker) {
        Collection collection = mAllMarkers.get(marker);
        if (collection != null && collection.mInfoWindowClickListener != null) {
            collection.mInfoWindowClickListener.onInfoWindowClick(marker);
            return true;
        }
        return false;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Collection collection = mAllMarkers.get(marker);
        if (collection != null && collection.mMarkerClickListener != null) {
            return collection.mMarkerClickListener.onMarkerClick(marker);
        }
        return false;
    }

    /*@Override
    public void onMarkerDragStart(Marker marker) {
        Collection collection = mAllMarkers.get(marker);
        if (collection != null && collection.mMarkerDragListener != null) {
            collection.mMarkerDragListener.onMarkerDragStart(marker);
        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        Collection collection = mAllMarkers.get(marker);
        if (collection != null && collection.mMarkerDragListener != null) {
            collection.mMarkerDragListener.onMarkerDrag(marker);
        }
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        Collection collection = mAllMarkers.get(marker);
        if (collection != null && collection.mMarkerDragListener != null) {
            collection.mMarkerDragListener.onMarkerDragEnd(marker);
        }
    }*/

    /**
     * Removes a marker from its collection.
     *
     * @param marker the marker to remove.
     * @return true if the marker was removed.
     */
    public boolean remove(Marker marker) {
        Collection collection = mAllMarkers.get(marker);
        return collection != null && collection.remove(marker);
    }

    public class Collection {
        private final Set<Marker> mMarkers = new HashSet<Marker>();
        private MapboxMap.OnInfoWindowClickListener mInfoWindowClickListener;
        private MapboxMap.OnMarkerClickListener mMarkerClickListener;
        //private MapboxMap.OnMarkerDragListener mMarkerDragListener;
        private MapboxMap.InfoWindowAdapter mInfoWindowAdapter;

        public Collection() {
        }

        public Marker addMarker(MarkerOptions opts) {
            Marker marker = mMap.addMarker(opts);
            mMarkers.add(marker);
            mAllMarkers.put(marker, Collection.this);
            return marker;
        }

        public boolean remove(Marker marker) {
            if (mMarkers.remove(marker)) {
                mAllMarkers.remove(marker);
                marker.remove();
                return true;
            }
            return false;
        }

        public void clear() {
            for (Marker marker : mMarkers) {
                marker.remove();
                mAllMarkers.remove(marker);
            }
            mMarkers.clear();
        }

        public java.util.Collection<Marker> getMarkers() {
            return Collections.unmodifiableCollection(mMarkers);
        }

        public void setOnInfoWindowClickListener(MapboxMap.OnInfoWindowClickListener infoWindowClickListener) {
            mInfoWindowClickListener = infoWindowClickListener;
        }

        public void setOnMarkerClickListener(MapboxMap.OnMarkerClickListener markerClickListener) {
            mMarkerClickListener = markerClickListener;
        }

//        public void setOnMarkerDragListener(MapboxMap.OnMarkerDragListener markerDragListener) {
//            mMarkerDragListener = markerDragListener;
//        }

        public void setOnInfoWindowAdapter(MapboxMap.InfoWindowAdapter infoWindowAdapter) {
            mInfoWindowAdapter = infoWindowAdapter;
        }
    }
}
