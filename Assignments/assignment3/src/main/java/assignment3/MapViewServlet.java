package assignment3;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A servlet that will get the data from database and sent them to the front end for map view of all listings on Kijiji
 */
@WebServlet(urlPatterns = "/mapview")
public class MapViewServlet extends HttpServlet {

    private ListingDatabase db;
    private CoordinatesEngine coordinatesEngine;

    public void init () {

        // Initialize the database and engine for getting coordinates using data
        this.db = new ListingDatabase();
        this.coordinatesEngine = new CoordinatesEngine();
    }

    /**
     * A get request that will get the data from database and send a json string to the front end using the response
     * @param request
     * @param response
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<String> rental = new ArrayList<>();
        HashMap<String, ArrayList<String>> allListing = this.db.getAllRows();
        for (Map.Entry<String, ArrayList<String>> eachListing : allListing.entrySet()) {
            // The corresponding field in the arraylist
            String url = eachListing.getValue().get(0);
            String address = eachListing.getValue().get(1);
            String coordinates = this.coordinatesEngine.getCoordinates(address);
            String price = eachListing.getValue().get(2);
            // Json format for all the details of the listing, including url, address, price, lat and lng
            String data = "{\"url\":\"" + url + "\",\"address\":\"" + address +
                    "\",\"price\":\"" + price + "\", " + coordinates + "}";
            rental.add(data);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.format("{\"rental\": %s }", rental.toString()));

    }

}
