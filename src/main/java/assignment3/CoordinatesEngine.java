package assignment3;

import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import java.io.IOException;

/**
 * A class that will get the coordinates of a specific address using GeoCoding class
 */
public class CoordinatesEngine {

    /**
     * Get the coordinates of the inputted address
     * @param address
     */
    public String getCoordinates(String address) {
        String coordinateJson = "";
        try {
            // Build the GeoCoding for getting coordinates
            GeoCoding geoCoding = GeoCoding.buildMapControl();
            GeocodingResult[] result = GeocodingApi.geocode(geoCoding.getContext(), address).language("en").await();
            // Latitude of the address
            double lat = result[0].geometry.location.lat;
            // Longitude of the address
            double lng = result[0].geometry.location.lng;
            // Json format
            coordinateJson = "\"lat\":\"" + lat + "\",\"lng\":\"" + lng + "\"";

        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return coordinateJson;
    }

}
