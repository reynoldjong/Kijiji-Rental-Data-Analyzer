package assignment3;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerBasicStrategy implements CrawlerStrategy {
  private Database db;
  
  public CrawlerBasicStrategy(Database db) {
    this.db = db;
  }
  
  public void execute(String title, Document doc) {
    Elements attr = doc.select("div[class*=attributeListWrapper]").select("dl[class*=itemAttribute]");
    for (Element entry : attr) {
      String label = entry.select("dt[class*=attributeLabel]").text();
      String value = entry.select("dd[class*=attributeValue]").text();
      db.update(title, label, value);
      System.out.println(entry.select("dt[class*=attributeLabel]").text());
      System.out.println(entry.select("dd[class*=attributeValue]").text());
      System.out.println("======");
    }
  }
  
}
