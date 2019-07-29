package assignment3;

import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
  private Database db;
  public Crawler(Database db) {
    //connect to db
    this.db = db;
    db.connect();
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
      String listingUrl = link.attr("abs:data-vip-url");
      System.out.println(listingUrl);
      crawlEachListing(listingUrl);
    }
  }
  
  public void crawlEachListing(String url) throws IOException {
    Document doc = Jsoup.connect(url).get();
    String title = getTitle(doc);
    System.out.println(title);
    db.insert(title);
    db.update(title, "url", url);
    String addr = getAddress(doc);
    System.out.println(addr);
    db.update(title, "addr", addr);
    String price = getPrice(doc);
    System.out.println(price);
    db.update(title, "price", price);
    getOverview(title, doc);
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
  private void getOverview(String title, Document doc) {
    Elements attr = doc.select("li[class*=realEstateAttribute]");
    Elements attrGroup = doc.select("li[class*=attributeGroupContainer]");
    for (Element a : attr) {
      System.out.println(a.select("h4[class*=realEstateLabel]").text());
      String header = a.select("h4[class*=realEstateLabel]").text();
      List<String> textList = a.select("div").eachText();
      String value = textList.get(textList.size() - 1);
      System.out.println(textList.get(textList.size() - 1));
      db.update(title, header, value);
    }
    for (Element b : attrGroup) {
      // System.out.println(b);
      // System.out.println(b.select("h4[class*=attributeGroupTitle]").text());
      Elements yesNoList = b.select("ul[class*=list]").select("svg[aria-label]");
      for (Element extra : yesNoList) {
        // System.out.println(extra.attr("aria-label"));
        String yesNo = extra.attr("aria-label");
        if (yesNo.subSequence(0,2).equals("No")) {
          db.update(title, yesNo.substring(4), "No");
          System.out.println(yesNo.substring(4));
        }
        // not sure if there will be another result currently
        else if (yesNo.subSequence(0, 3).equals("Yes")) {
          db.update(title, yesNo.substring(5), "Yes");
          System.out.println(yesNo.substring(5));
        }
        // System.out.println(extra);
      }
    }
  }
  
  public static void main(String[] args) throws IOException {
    Database mockDB = new MockDB();
    Crawler myCrawler = new Crawler(mockDB);
    // myCrawler.crawlListingPage("https://www.kijiji.ca/b-apartments-condos/canada/c37l0", 100);
    myCrawler.crawlEachListing("https://www.kijiji.ca/v-apartments-condos/st-johns/newly-renovated-main-flr-apt-3-bdrm-rec-rm-at-airport-heights/1447361095");
    // System.out.println("1234567890".subSequence(0, 2));
  }
}
