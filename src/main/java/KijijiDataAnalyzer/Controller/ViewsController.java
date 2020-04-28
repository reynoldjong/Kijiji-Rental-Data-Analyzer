package KijijiDataAnalyzer.Controller;

import KijijiDataAnalyzer.GoogleGeoCodingEngine.CoordinatesEngine;
import KijijiDataAnalyzer.GoogleGeoCodingEngine.GeoCoding;
import KijijiDataAnalyzer.Model.RentalListing;
import KijijiDataAnalyzer.Repository.RentalListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // This means that this class is a Controller
public class ViewsController {

    @Autowired
    private RentalListingRepository rentalListingRepository;

    private final CoordinatesEngine coordinatesEngine = new CoordinatesEngine(GeoCoding.buildMapControl());

    @GetMapping("/mapview")
    public List<Map<String, String>> mapView() {
        // This returns a JSON or XML
        Iterable<RentalListing> allRentalListing = rentalListingRepository.findAll();
        List<Map<String, String>> rentalList = new ArrayList<>();
        for (RentalListing eachRentalListing: allRentalListing) {
            Map<String, String> attributesMap = new HashMap<>();
            attributesMap.put("url", eachRentalListing.getUrl());
            attributesMap.put("address", eachRentalListing.getAddress());
            attributesMap.put("price", eachRentalListing.getPrice());
            attributesMap.putAll(coordinatesEngine.getCoordinates(eachRentalListing.getAddress()));
            rentalList.add(attributesMap);
        }
        return rentalList;
    }

    @GetMapping("/chartview")
    public Map<String, Object> chartView() {
        // This returns a JSON or XML
        Iterable<RentalListing> allRentalListing = rentalListingRepository.findAll();
        Map<String, Object> rentalMap = new HashMap<>();
        for (RentalListing eachRentalListing: allRentalListing) {
            rentalMap.put(eachRentalListing.getTitle(), eachRentalListing);
        }
        return rentalMap;
    }


}
