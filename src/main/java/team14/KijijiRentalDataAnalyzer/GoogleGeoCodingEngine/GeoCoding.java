package team14.KijijiRentalDataAnalyzer.GoogleGeoCodingEngine;

import com.google.maps.GeoApiContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;


/**
 * A singleton class that handles the Google GeoCoding API
 */
public class GeoCoding {

    private static GeoCoding geoCoding = null;
    // The context used in getting coordinates
    private GeoApiContext context;
    private String apiKey;

    private GeoCoding() {
        getApiKey();
        context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
        System.out.println(apiKey);
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
        } else {
            return GeoCoding.geoCoding;
        }

    }

    private void getApiKey() {
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("frontend/src/config/secret.json"));
            JSONObject jsonObject = (JSONObject) obj;
            apiKey = (String) jsonObject.get("GeoAPI");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }
}
