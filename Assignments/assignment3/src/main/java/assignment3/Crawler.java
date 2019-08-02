package assignment3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
  private Database db;
  private CrawlerStrategy basicStrategy;
  private CrawlerStrategy detailedOverviewStrategy;
  private ArrayList<String> listings;
  
  public Crawler(Database db) {
    //connect to db
    this.db = db;
    this.basicStrategy = new CrawlerBasicStrategy(db);
    this.detailedOverviewStrategy = new CrawlerOverviewStrategy(db);
    this.listings = new ArrayList<>();
  }
  
  public void crawlFromSeed(String seed, int limit) throws IOException {
    db.connect();
    if (!seed.contains("https://www.kijiji.ca")) {
      // not crawling anything other than kijiji
      System.out.println("Crawler only support Kijiji pages");
      return;
    }
    String url = seed;
    // we may end up with list size >= limit after loop, but we can trim extras
    while (listings.size() < limit) {
      //System.out.println(url);
      Document doc = Jsoup.connect(url).get();
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
    db.deleteAll();
    while (i < limit && i < listings.size()) {
      crawlEachListing(listings.get(i++));
    }
    db.close();
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
    Document doc = Jsoup.connect(url).get();
    String title = getTitle(doc);
    //System.out.println(title);
    db.insert(title);
    db.update(title, "url", url);
    String addr = getAddress(doc);
    //System.out.println(addr);
    db.update(title, "addr", addr);
    String price = getPrice(doc);
    //System.out.println(price);
    db.update(title, "price", price);
    // test to see if it is going to be a detailed list
    if (doc.select("li[class*=realEstateAttribute]").size() == 0)
      basicStrategy.execute(title, doc);
    else
      detailedOverviewStrategy.execute(title, doc);
    // getOverviewTypeI(title, doc);
    // getOverviewTypeII(title, doc);
  }
  
  // get the title of the posting
  private String getTitle(Document doc) {
    String title = "";
    Elements titleHeader = doc.select("h1[class*=title]");
    for (Element s : titleHeader) {
      title = s.text();
      title.replaceAll("[^\\w\\s]","");
    }
    return title;
  }
  
  // get the address of the posting
  private String getAddress(Document doc) {
    String address = "";
    Elements addrSpan = doc.select("span[itemprop=\"address\"]");
    for (Element s : addrSpan) {
      address = s.text();
      address.replaceAll("[^\\w\\s]","");
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
  
  public static void main(String[] args) throws IOException {
    Database mockDB = new MockDB();
    Crawler myCrawler = new Crawler(mockDB);
    myCrawler.crawlFromSeed("https://www.kijiji.ca/b-apartments-condos/canada/c37l0", 5);
    //myCrawler.crawlEachListing("https://www.kijiji.ca/v-apartments-condos/st-johns/newly-renovated-main-flr-apt-3-bdrm-rec-rm-at-airport-heights/1447361095");
    // myCrawler.crawlEachListing("https://www.kijiji.ca/v-house-for-sale/markham-york-region/40-swennen-3-bed-fin-bsmt-backsplit-brampton/1443579943");
    // System.out.println("1234567890".subSequence(0, 2));
  }
}
