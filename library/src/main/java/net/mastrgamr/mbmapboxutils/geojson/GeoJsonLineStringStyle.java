package net.mastrgamr.mbmapboxutils.geojson;


import android.util.Log;

import com.mapbox.mapboxsdk.annotations.PolylineOptions;

import java.util.Arrays;
import java.util.Observable;

/**
 * A class that allows for GeoJsonLineString objects to be styled and for these styles to be
 * translated into a PolylineOptions object. {@see
 * <a href="https://www.mapbox.com/android-sdk/api/4.0.0-beta.2/com/mapbox/mapboxsdk/annotations/PolygonOptions.html">
 * PolylineOptions docs</a> for more details about the options.}
 */
public class GeoJsonLineStringStyle extends Observable implements GeoJsonStyle {

    private final static String[] GEOMETRY_TYPE = {"LineString", "MultiLineString",
            "GeometryCollection"};

    private final PolylineOptions mPolylineOptions;

    /**
     * Creates a new LineStringStyle object
     */
    public GeoJsonLineStringStyle() {
        mPolylineOptions = new PolylineOptions();
        Log.d("GeoJsonLineStringStyle", "Default LineString width: " + mPolylineOptions.getWidth());
        mPolylineOptions.width(2f); //FIXME: Mapbox's default 10px width is ridiculous
    }

    /** {@inheritDoc} */
    @Override
    public String[] getGeometryType() {
        return GEOMETRY_TYPE;
    }

    /**
     * Gets the color of the GeoJsonLineString as a 32-bit ARGB color
     *
     * @return color of the GeoJsonLineString
     */
    public int getColor() {
        return mPolylineOptions.getColor();
    }

    /**
     * Sets the color of the GeoJsonLineString as a 32-bit ARGB color
     *
     * @param color color value of the GeoJsonLineString
     */
    public void setColor(int color) {
        mPolylineOptions.color(color);
        styleChanged();
    }

    /**
     * Gets the width of the GeoJsonLineString in screen pixels
     *
     * @return width of the GeoJsonLineString
     */
    public float getWidth() {
        return mPolylineOptions.getWidth();
    }

    /**
     * Sets the width of the GeoJsonLineString in screen pixels
     *
     * @param width width value of the GeoJsonLineString
     */
    public void setWidth(float width) {
        mPolylineOptions.width(width);
        styleChanged();
    }

    /**
     * Gets whether the GeoJsonLineString is visible
     *
     * @return true if the GeoJsonLineString visible, false if not visible
     */
    @Override
    public boolean isVisible() {
        return true; //TODO: mPolylineOptions.isVisible();
    }

    /**
     * Sets whether the GeoJsonLineString is visible
     *
     * @param visible true if the GeoJsonLineString is visible, false if not visible
     */
    @Override
    public void setVisible(boolean visible) {
        //TODO: mPolylineOptions.visible(visible);
        styleChanged();
    }

    /**
     * Notifies the observers, GeoJsonFeature objects, that the style has changed. Indicates to the
     * GeoJsonFeature that it should check whether a redraw is needed for the feature.
     */
    private void styleChanged() {
        setChanged();
        notifyObservers();
    }

    /**
     * Gets a new PolylineOptions object containing styles for the GeoJsonLineString
     *
     * @return new PolylineOptions object
     */
    public PolylineOptions toPolylineOptions() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(mPolylineOptions.getColor());
        polylineOptions.width(mPolylineOptions.getWidth());
        return polylineOptions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LineStringStyle{");
        sb.append("\n geometry type=").append(Arrays.toString(GEOMETRY_TYPE));
        sb.append(",\n color=").append(getColor());
        sb.append(",\n visible=").append(isVisible());
        sb.append(",\n width=").append(getWidth());
        sb.append("\n}\n");
        return sb.toString();
    }
}
