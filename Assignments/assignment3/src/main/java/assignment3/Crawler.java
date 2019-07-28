package assignment3;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
  
  public Crawler() {
    //connect to db
  }
  
  public void crawlFromSeed(String seed, int limit) {
    if (!seed.contains("https://www.kijiji.ca")) {
      // not crawling anything other than kijiji
      return;
    }
  }
  
  public void crawlListingPage(String url, int limit) throws IOException {
    Document doc = Jsoup.connect(url).get();
    Elements links = doc.select("div[data-vip-url]");
    for (Element link : links) {
      System.out.println(link.attr("abs:data-vip-url"));
      crawlEachListing(link.attr("abs:data-vip-url"));
    }
  }
  
  public void crawlEachListing(String url) throws IOException {
    Document doc = Jsoup.connect(url).get();
    String addr = getAddress(doc);
    System.out.println(addr);
    String price = getPrice(doc);
    System.out.println(price);
    getOverview(doc);
  }
  
  private String getAddress(Document doc) {
    String address = "";
    Elements addrSpan = doc.select("span[itemprop=\"address\"]");
    for (Element s : addrSpan) {
      address = s.text();
    }
    return address;
  }
  
  private String getPrice(Document doc) {
    String price = "";
    Elements priceContainer = doc.select("span[content]");
    for (Element e : priceContainer) {
      price = e.text();
    }
    return price;
  }
  
  private void getOverview(Document doc) {
    Elements attrCard = doc.select("div[attributeCard-766768531]");
    for (Element e : attrCard) {
      System.out.println(e.text());
    }
  }
  
  public static void main(String[] args) throws IOException {
    Crawler myCrawler = new Crawler();
    // myCrawler.crawlListingPage("https://www.kijiji.ca/b-apartments-condos/canada/c37l0", 100);
    myCrawler.crawlEachListing("https://www.kijiji.ca/v-apartments-condos/st-johns/newly-renovated-main-flr-apt-3-bdrm-rec-rm-at-airport-heights/1447361095");
    
  }
}
