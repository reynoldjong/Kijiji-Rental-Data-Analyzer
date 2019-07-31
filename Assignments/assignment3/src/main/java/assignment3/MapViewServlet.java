package assignment3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
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
        ArrayList<String> coordinates = new ArrayList<>();
        HashMap<String, ArrayList<String>> allListing = this.db.getAllRows();
        for (Map.Entry<String, ArrayList<String>> eachListing : allListing.entrySet()) {
            String address = eachListing.getValue().get(1);
            String coord = this.geoCodingEngine.getCoorindate(address);
            coordinates.add(coord);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.format("{\"coordinates\": %s }", coordinates.toString()));

    }

}
