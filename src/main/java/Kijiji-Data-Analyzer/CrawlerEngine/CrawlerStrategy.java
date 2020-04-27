package assignment3.CrawlerEngine;

import assignment3.Model.RentalListing;
import org.jsoup.nodes.Document;

public interface CrawlerStrategy {
  RentalListing execute(String title, Document doc, RentalListing.RentalListingBuilder builder);
}
