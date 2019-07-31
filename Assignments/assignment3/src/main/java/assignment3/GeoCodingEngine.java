package assignment3;

import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import java.io.IOException;

public class GeoCodingEngine {

    private MapControl mapControl;

    public String getCoorindate(String address) {
        String coordinateJson = "";
        try {
            mapControl = MapControl.buildMapControl();
            GeocodingResult[] geocoding = GeocodingApi.geocode(MapControl.getContext(), address).language("en").await();
            double lat = geocoding[0].geometry.location.lat;
            double lng = geocoding[0].geometry.location.lng;
            coordinateJson = "{\"lat\":\"" + lat + "\",\"lng\":\"" + lng + "\"}";

        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return coordinateJson;
    }

}
