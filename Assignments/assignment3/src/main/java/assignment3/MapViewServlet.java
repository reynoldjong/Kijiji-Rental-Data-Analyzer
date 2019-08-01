package assignment3;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/mapview")
public class MapViewServlet extends HttpServlet {

    private ListingDatabase db;
    private GeoCodingEngine geoCodingEngine;

    public void init () {

        this.db = new ListingDatabase();
        this.geoCodingEngine = new GeoCodingEngine();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<String> rental = new ArrayList<>();
        HashMap<String, ArrayList<String>> allListing = this.db.getAllRows();
        for (Map.Entry<String, ArrayList<String>> eachListing : allListing.entrySet()) {
            String url = eachListing.getValue().get(0);
            String address = eachListing.getValue().get(1);
            String coordinates = this.geoCodingEngine.getCoorindate(address);
            String price = eachListing.getValue().get(2);
            String data = "{\"url\":\"" + url + "\",\"address\":\"" + address +
                    "\",\"price\":\"" + price + "\", " + coordinates + "}";
            rental.add(data);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.format("{\"rental\": %s }", rental.toString()));

    }

}
