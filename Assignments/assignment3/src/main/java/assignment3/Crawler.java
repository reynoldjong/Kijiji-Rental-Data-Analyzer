package assignment3;

import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class Crawler {
  private Database db;
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
    String title = getTitle(doc);
    System.out.println(title);
    String addr = getAddress(doc);
    System.out.println(addr);
    String price = getPrice(doc);
    System.out.println(price);
    getOverview(doc);
  }
  
  private String getTitle(Document doc) {
    String title = "";
    Elements titleHeader = doc.select("h1[class*=title]");
    for (Element s : titleHeader) {
      title = s.text();
    }
    return title;
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
  
  // commented out lines are the values we want to insert to DB
  private void getOverview(Document doc) {
    Elements attr = doc.select("li[class*=realEstateAttribute]");
    Elements attrGroup = doc.select("li[class*=attributeGroupContainer]");
    for (Element a : attr) {
      System.out.println(a.select("h4[class*=realEstateLabel]").text());
      List<String> textList = a.select("div").eachText();
      System.out.println(textList.get(textList.size() - 1));
      
    }
    for (Element b : attrGroup) {
      // System.out.println(b);
      System.out.println(b.select("h4[class*=attributeGroupTitle]").text());
      Elements yesNoList = b.select("ul[class*=list]").select("svg[aria-label]");
      for (Element extra : yesNoList) {
        System.out.println(extra.attr("aria-label"));
        // System.out.println(extra);
      }
    }
  }
  
  public static void main(String[] args) throws IOException {
    Crawler myCrawler = new Crawler();
    // myCrawler.crawlListingPage("https://www.kijiji.ca/b-apartments-condos/canada/c37l0", 100);
    myCrawler.crawlEachListing("https://www.kijiji.ca/v-apartments-condos/st-johns/newly-renovated-main-flr-apt-3-bdrm-rec-rm-at-airport-heights/1447361095");
    
  }
}
