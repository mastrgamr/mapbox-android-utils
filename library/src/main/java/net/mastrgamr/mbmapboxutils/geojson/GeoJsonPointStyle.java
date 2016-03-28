package net.mastrgamr.mbmapboxutils.geojson;


import com.mapbox.mapboxsdk.annotations.MarkerOptions;

import java.util.Arrays;
import java.util.Observable;

/**
 * A class that allows for GeoJsonPoint objects to be styled and for these styles to be translated
 * into a MarkerOptions object. {@see
 * <a href="https://developer.android.com/reference/com/google/android/gms/maps/model/MarkerOptions.html">
 * MarkerOptions docs</a> for more details about the options.}
 */
public class GeoJsonPointStyle extends Observable implements GeoJsonStyle {

    private final static String[] GEOMETRY_TYPE = {"Point", "MultiPoint", "GeometryCollection"};

    private final MarkerOptions mMarkerOptions;


    /**
     * Creates a new PointStyle object
     */
    public GeoJsonPointStyle() {
        mMarkerOptions = new MarkerOptions();
    }

    /** {@inheritDoc} */
    @Override
    public String[] getGeometryType() {
        return GEOMETRY_TYPE;
    }

    /**
     * Gets the alpha of the GeoJsonPoint. This is a value from 0 to 1, where 0 means the marker is
     * completely transparent and 1 means the marker is completely opaque.
     *
     * @return alpha of the GeoJsonPoint
     */
    public float getAlpha() {
        return 1.0f; //TODO:mMarkerOptions.getAlpha();
    }

    /**
     * Sets the alpha of the GeoJsonPoint. This is a value from 0 to 1, where 0 means the marker is
     * completely transparent and 1 means the marker is completely opaque.
     *
     * @param alpha alpha value of the GeoJsonPoint
     */
    public void setAlpha(float alpha) {
        //TODO:mMarkerOptions.alpha(alpha);
        styleChanged();
    }

    /**
     * Gets whether the GeoJsonPoint is draggable
     *
     * @return true if GeoJsonPoint is draggable, false if not draggable
     */
    public boolean isDraggable() {
        return false; //TODO: mMarkerOptions.isDraggable();
    }

    /**
     * Sets the GeoJsonPoint to be draggable
     *
     * @param draggable true if GeoJsonPoint is draggable, false if not draggable
     */
    public void setDraggable(boolean draggable) {
        //TODO: mMarkerOptions.draggable(draggable);
        styleChanged();
    }

    /**
     * Gets whether the GeoJsonPoint is flat
     *
     * @return true if GeoJsonPoint is flat, false if not flat
     */
    public boolean isFlat() {
        return true; //TODO:mMarkerOptions.isFlat();
    }

    /**
     * Sets the GeoJsonPoint to be flat
     *
     * @param flat true if GeoJsonPoint is flat, false if not flat
     */
    public void setFlat(boolean flat) {
        //TODO:mMarkerOptions.flat(flat);
        styleChanged();
    }

    /**
     * Gets a bitmap image for the GeoJsonPoint
     *
     * @return bitmap descriptor for the GeoJsonPoint
     */
//    public BitmapDescriptor getIcon() {
//        return mMarkerOptions.getIcon();
//    }

    /**
     * Sets a bitmap image for the GeoJsonPoint
     */
    public void setIcon(/*BitmapDescriptor bitmap*/) {
        //Equiv MapBox icon?
        //TODO:mMarkerOptions.icon(bitmap);
        styleChanged();
    }

    /**
     * Gets the rotation of the GeoJsonPoint in degrees clockwise about the marker's anchor point
     *
     * @return rotation of the GeoJsonPoint
     */
    public float getRotation() {
        return 0f; //TODO:mMarkerOptions.getRotation();
    }


    /**
     * Sets the rotation of the GeoJsonPoint in degrees clockwise about the marker's anchor point
     *
     * @param rotation rotation value of the GeoJsonPoint
     */
    public void setRotation(float rotation) {
        //TODO:mMarkerOptions.rotation(rotation);
        styleChanged();
    }

    /**
     * Gets the snippet of the GeoJsonPoint
     *
     * @return snippet of the GeoJsonPoint
     */
    public String getSnippet() {
        return mMarkerOptions.getSnippet();
    }

    /**
     * Sets the snippet of the GeoJsonPoint
     *
     * @param snippet sets the snippet value of the GeoJsonPoint
     */
    public void setSnippet(String snippet) {
        mMarkerOptions.snippet(snippet);
        styleChanged();
    }

    /**
     * Gets the title of the GeoJsonPoint
     *
     * @return title of the GeoJsonPoint
     */
    public String getTitle() {
        return mMarkerOptions.getTitle();
    }

    /**
     * Sets the title of the GeoJsonPoint
     *
     * @param title title value of the GeoJsonPoint
     */
    public void setTitle(String title) {
        mMarkerOptions.title(title);
        styleChanged();
    }

    /**
     * Gets whether the GeoJsonPoint is visible
     *
     * @return true if GeoJsonPoint is visible, false if not visible
     */
    @Override
    public boolean isVisible() {
        return true; //TODO:mMarkerOptions.isVisible();
    }

    /**
     * Sets whether the GeoJsonPoint is visible
     *
     * @param visible true if GeoJsonPoint is visible, false if not visible
     */
    @Override
    public void setVisible(boolean visible) {
        //TODO:mMarkerOptions.visible(visible);
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
     * Gets a new MarkerOptions object containing styles for the GeoJsonPoint
     *
     * @return new MarkerOptions object
     */
    public MarkerOptions toMarkerOptions() {
        MarkerOptions markerOptions = new MarkerOptions();
        //TODO:markerOptions.alpha(mMarkerOptions.getAlpha());
        //TODO:markerOptions.anchor(mMarkerOptions.getAnchorU(), mMarkerOptions.getAnchorV());
        //TODO:markerOptions.draggable(mMarkerOptions.isDraggable());
        //TODO:markerOptions.flat(mMarkerOptions.isFlat());
        markerOptions.icon(mMarkerOptions.getIcon());
        //TODO:markerOptions.infoWindowAnchor(mMarkerOptions.getInfoWindowAnchorU(),
        //        mMarkerOptions.getInfoWindowAnchorV());
        //TODO:markerOptions.rotation(mMarkerOptions.getRotation());
        markerOptions.snippet(mMarkerOptions.getSnippet());
        markerOptions.title(mMarkerOptions.getTitle());
        //TODO:markerOptions.visible(mMarkerOptions.isVisible());
        return markerOptions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PointStyle{");
        sb.append("\n geometry type=").append(Arrays.toString(GEOMETRY_TYPE));
        sb.append(",\n alpha=").append(getAlpha());
        //TODO:sb.append(",\n anchor U=").append(getAnchorU());
        //TODO:sb.append(",\n anchor V=").append(getAnchorV());
        sb.append(",\n draggable=").append(isDraggable());
        sb.append(",\n flat=").append(isFlat());
        //TODO:sb.append(",\n info window anchor U=").append(getInfoWindowAnchorU());
        //TODO:sb.append(",\n info window anchor V=").append(getInfoWindowAnchorV());
        sb.append(",\n rotation=").append(getRotation());
        sb.append(",\n snippet=").append(getSnippet());
        sb.append(",\n title=").append(getTitle());
        sb.append(",\n visible=").append(isVisible());
        sb.append("\n}\n");
        return sb.toString();
    }
}
