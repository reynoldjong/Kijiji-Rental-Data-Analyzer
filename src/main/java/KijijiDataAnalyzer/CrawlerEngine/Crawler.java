package KijijiDataAnalyzer.CrawlerEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import KijijiDataAnalyzer.Model.RentalListing;
import KijijiDataAnalyzer.Repository.RentalListingRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Crawler {

  @Autowired
  private RentalListingRepository rentalListingRepository;

  private CrawlerStrategy basicStrategy = new CrawlerBasicStrategy();
  private CrawlerStrategy detailedOverviewStrategy = new CrawlerOverviewStrategy();
  private List<String> listings = new ArrayList<>();
  
  public void crawlFromSeed(String seed, int limit) throws IOException {
    if (!seed.contains("https://www.kijiji.ca")) {
      // not crawling anything other than kijiji
      System.out.println("Crawler only support Kijiji pages");
      return;
    }
    String url = seed;
    // we may end up with list size >= limit after loop, but we can trim extras
    while (listings.size() < limit) {
      Document doc = Jsoup.connect(url + "?siteLocale=en_CA").get();
      // build a list of url for each listing
      populateListings(doc);
      Elements nextElements = doc.select("div[class*=bottom-bar]").select("a[title=Next]");
      if (nextElements.size() != 0) {
        Element next = nextElements.get(0);
        url = next.attr("abs:href");
        // special case if there is no more "next"
        if (url.equals(""))
          break;
      }
    }
    
    int i = 0;
    // clear all entries in database
    rentalListingRepository.deleteAll();
    while (i < limit && i < listings.size()) {
      crawlEachListing(listings.get(i++));
    }
  }
  
  // helper that builds a list of URL for each listing
  private void populateListings(Document doc) throws IOException {
    Elements links = doc.select("div[data-vip-url]");
    for (Element link : links) {
      String listingUrl = link.attr("abs:data-vip-url");
      // avoid repeats
      if (!listings.contains(listingUrl)) {
        listings.add(listingUrl);
      }
    }
  }
  
  public void crawlEachListing(String url) throws IOException {
    Document doc = Jsoup.connect(url + "?siteLocale=en_CA").get();
    String title = getTitle(doc);
    String addr = getAddress(doc);
    String price = getPrice(doc);
    RentalListing.RentalListingBuilder builder = new RentalListing.RentalListingBuilder(title, url, addr, price);
    // test to see if it is going to be a detailed list
    if (doc.select("li[class*=twoLinesAttribute]").size() == 0)
      rentalListingRepository.save(basicStrategy.execute(title, doc, builder));
    else {
      rentalListingRepository.save(detailedOverviewStrategy.execute(title, doc, builder));
    }
  }
  
  // get the title of the posting
  private String getTitle(Document doc) {
    String title = "";
    Elements titleHeader = doc.select("h1[class*=title]");
    for (Element s : titleHeader) {
      title = s.text();
    }
    return title;
  }
  
  // get the address of the posting
  private String getAddress(Document doc) {
    String address = "";
    Elements addrSpan = doc.select("span[itemprop=\"address\"]");
    for (Element s : addrSpan) {
      address = s.text();
    }
    return address;
  }
  
  // get the price of the posting (as a string)
  private String getPrice(Document doc) {
    String price = "";
    Elements priceContainer = doc.select("span[content]");
    for (Element e : priceContainer) {
      price = e.text();
    }
    return price;
  }

  /*
  public static void main(String[] args) throws IOException {
    Crawler myCrawler = new Crawler();
    myCrawler.crawlFromSeed("https://www.kijiji.ca/b-apartments-condos/canada/c37l0", 5);
    myCrawler.crawlEachListing("https://www.kijiji.ca/v-apartments-condos/st-johns/spacious-one-bedroom-apartment-available-for-may-1st/1497327681?undefined");
    myCrawler.crawlEachListing("https://www.kijiji.ca/v-apartments-condos/mississauga-peel-region/2-bedroom-2-full-washroom-luxury-condo-near-sq-one-2500-month/1497524613");
    System.out.println("1234567890".subSequence(0, 2));
  }
  */
}
