package net.mastrgamr.mbmapboxutils.extentions;

import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.mapbox.mapboxsdk.annotations.BaseMarkerOptions;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.geometry.ILatLng;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Projection;

/**
 * Project: MBMapUtils
 * Written by: mastrgamr
 * Date: 3/28/16
 */
public class DraggableMarker extends Marker {

    private static final String TAG = "DraggableMarker";

    private boolean mIsDragged;
    private static final RectF mTempRect = new RectF();
    private static final PointF mTempPoint = new PointF();
    private float mDx, mDy;


    public DraggableMarker(BaseMarkerOptions baseMarkerOptions) {
        super(baseMarkerOptions);
    }

    public boolean drag(MapboxMap mbMap, MotionEvent event) {
        final int action = event.getActionMasked();
        if(action == MotionEvent.ACTION_DOWN) {
            Projection pj = mbMap.getProjection();
            //RectF bound = getDrawingBounds(pj, mTempRect);
            if(pj.getVisibleRegion().latLngBounds.contains(new LatLng(event.getX(), event.getY()))) {
                mIsDragged = true;
                PointF p = pj.toScreenLocation(new LatLng(event.getX(), event.getY()));
                mDx = p.x - event.getX();
                mDy = p.y - event.getY();
            }
        }
        if(mIsDragged) {
            if((action == MotionEvent.ACTION_CANCEL) ||
                    (action == MotionEvent.ACTION_UP)) {
                mIsDragged = false;
            } else {
                Projection pj = mbMap.getProjection();
                ILatLng pos = pj.fromScreenLocation(new PointF(event.getX() + mDx, event.getY() + mDy));
                setPosition(new LatLng(pos.getLatitude(), pos.getLongitude()));
            }
        }

        return mIsDragged;
    }

    public interface OnMarkerDragListener {
        void onMarkerDragStart(Marker var1);

        void onMarkerDrag(Marker var1);

        void onMarkerDragEnd(Marker var1);
    }
}
