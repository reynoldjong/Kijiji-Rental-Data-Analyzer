package team14.KijijiRentalDataAnalyzer.GoogleGeoCodingEngineTest;

import team14.KijijiRentalDataAnalyzer.GoogleGeoCodingEngine.CoordinatesEngine;
import team14.KijijiRentalDataAnalyzer.GoogleGeoCodingEngine.GeoCoding;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CoordinatesEngineTest {

    private CoordinatesEngine ce;

    @Before
    public void setUp() {
        GeoCoding geoCoding = GeoCoding.buildMapControl();
        ce = new CoordinatesEngine(geoCoding);
    }

    @Test
    public void givenAddress_thenReturnLatAndLng() {
        String address = "1295 Military TrailScarborough, ON M1C 3A8";
        // From google
        String latitudeFound = "43.784841";
        String longitudeFound = "-79.18471559999999";
        Map<String, String> coordinates = ce.getCoordinates(address);
        assertThat(coordinates, instanceOf(HashMap.class));
        assertTrue(coordinates.containsKey("lat"));
        assertTrue(coordinates.containsKey("lng"));
        assertEquals(coordinates.get("lat"), latitudeFound);
        assertEquals(coordinates.get("lng"), longitudeFound);
    }
}
