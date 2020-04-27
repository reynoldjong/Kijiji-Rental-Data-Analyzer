package assignment3.GoogleGeoCodingEngine;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that will get the coordinates of a specific address using GeoCoding class
 */
public class CoordinatesEngine {

    private GeoApiContext context;

    public CoordinatesEngine(GeoCoding geoCoding) {
        this.context = geoCoding.getContext();
    }


    /**
     * Get the coordinates of the inputted address
     * @param address
     */
    public Map<String, String> getCoordinates(String address) {
        Map<String, String> coordinates = new HashMap<>();
        try {
            // Build the GeoCoding for getting coordinates
            GeocodingResult[] result = GeocodingApi.geocode(context, address).language("en").await();
            // Latitude of the address
            double lat = result[0].geometry.location.lat;
            // Longitude of the address
            coordinates.put("lat", Double.toString(lat));
            double lng = result[0].geometry.location.lng;
            coordinates.put("lng", Double.toString(lng));
            // Json format
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return coordinates;
    }

}
