package KijijiDataAnalyzer.CrawlerEngine;

import KijijiDataAnalyzer.Model.RentalListing;
import org.jsoup.nodes.Document;

public interface CrawlerStrategy {
  RentalListing execute(String title, Document doc, RentalListing.RentalListingBuilder builder);
}
