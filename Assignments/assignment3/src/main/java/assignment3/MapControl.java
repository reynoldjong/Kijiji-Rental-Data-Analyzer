package assignment3;

import com.google.maps.GaeRequestHandler;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import java.io.IOException;

public class MapControl {

    private static MapControl mapControl = null;
    private static GeoApiContext context;

    private MapControl() {
        context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDAcLK0RSSSVNdvAA_TRGAHPPHpBZnIEiw")
                .build();
    }

    public static GeoApiContext getContext() {
        return context;
    }

    public static MapControl buildMapControl() {
        if (MapControl.mapControl == null) {
            MapControl.mapControl = new MapControl();
            return MapControl.mapControl;
        }
        else{
            return MapControl.mapControl;
        }

    }

}
