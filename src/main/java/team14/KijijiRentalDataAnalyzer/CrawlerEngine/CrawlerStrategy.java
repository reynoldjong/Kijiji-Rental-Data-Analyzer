package team14.KijijiRentalDataAnalyzer.CrawlerEngine;

import team14.KijijiRentalDataAnalyzer.Model.RentalListing;
import org.jsoup.nodes.Document;

public interface CrawlerStrategy {
  RentalListing execute(String title, Document doc, RentalListing.RentalListingBuilder builder);
}
