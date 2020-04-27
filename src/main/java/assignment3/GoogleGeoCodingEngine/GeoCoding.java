package assignment3.GoogleGeoCodingEngine;

import com.google.maps.GeoApiContext;


/**
 * A singleton class that handles the Google GeoCoding API
 */
public class GeoCoding {

    private static GeoCoding geoCoding = null;
    // The context used in getting coordinates
    private GeoApiContext context;

    private GeoCoding() {
        context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDAcLK0RSSSVNdvAA_TRGAHPPHpBZnIEiw")
                .build();
    }

    /**
     * Get he GeoApiContext field
     */
    public GeoApiContext getContext() {
        return context;
    }

    /**
     * Get the instance of this class
     */
    public static GeoCoding buildMapControl() {
        if (GeoCoding.geoCoding == null) {
            GeoCoding.geoCoding = new GeoCoding();
            return GeoCoding.geoCoding;
        }
        else{
            return GeoCoding.geoCoding;
        }

    }

}
