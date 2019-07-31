package assignment3;

import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerOverviewStrategy implements CrawlerStrategy {
  private Database db;

  public CrawlerOverviewStrategy(Database db) {
    this.db = db;
  }

  public void execute(String title, Document doc) {
    Elements attr = doc.select("li[class*=realEstateAttribute]");
    Elements attrGroup = doc.select("li[class*=attributeGroupContainer]");
    for (Element a : attr) {
      //System.out.println(a.select("h4[class*=realEstateLabel]").text());
      String header = a.select("h4[class*=realEstateLabel]").text();
      List<String> textList = a.select("div").eachText();
      String value = textList.get(textList.size() - 1);
      //System.out.println(textList.get(textList.size() - 1));
      db.update(title, header, value);
    }
    for (Element b : attrGroup) {
      Elements yesNoList =
          b.select("ul[class*=list]").select("svg[aria-label]");
      for (Element extra : yesNoList) {
        //System.out.println(extra.attr("aria-label"));
        String yesNo = extra.attr("aria-label");
        if (yesNo.subSequence(0, 2).equals("No")) {
          db.update(title, yesNo.substring(4), "No");
          //System.out.println(yesNo);
        }
        else if (yesNo.subSequence(0, 3).equals("Yes")) {
          db.update(title, yesNo.substring(5), "Yes");
          //System.out.println(yesNo);
        }
        // not sure if there will be another result currently
      }

    }
  }
}
