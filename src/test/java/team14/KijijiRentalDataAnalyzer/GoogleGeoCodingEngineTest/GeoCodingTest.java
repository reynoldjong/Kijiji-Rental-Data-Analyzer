package team14.KijijiRentalDataAnalyzer.GoogleGeoCodingEngineTest;

import team14.KijijiRentalDataAnalyzer.GoogleGeoCodingEngine.GeoCoding;
import com.google.maps.GeoApiContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeoCodingTest {

    @Test
    public void testGetInstance() {
        assertEquals(GeoCoding.buildMapControl().getClass(), GeoCoding.class);
    }

    @Test
    public void testGetContext() {
        GeoCoding mc = GeoCoding.buildMapControl();
        assertEquals(mc.getContext().getClass(), GeoApiContext.class);
    }

}
