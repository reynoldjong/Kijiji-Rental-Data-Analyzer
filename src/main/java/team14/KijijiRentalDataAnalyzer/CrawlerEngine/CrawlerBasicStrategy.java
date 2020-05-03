package team14.KijijiRentalDataAnalyzer.CrawlerEngine;

import team14.KijijiRentalDataAnalyzer.Model.RentalListing;
import org.apache.commons.text.CaseUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CrawlerBasicStrategy implements CrawlerStrategy {

  public RentalListing execute(String title, Document doc, RentalListing.RentalListingBuilder builder) {
    Elements attr = doc.select("div[class*=attributeListWrapper]").select("dl[class*=itemAttribute]");
    for (Element entry : attr) {
      String label = entry.select("dt[class*=attributeLabel]").text();
      String value = entry.select("dd[class*=attributeValue]").text();
      label = CaseUtils.toCamelCase(label, false, new char[] {' ', '-', '(', ')'});
      try {
        Method m = RentalListing.RentalListingBuilder.class.getMethod(label, String.class);
        m.invoke(builder, value);
      } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
      }
      //System.out.println(entry.select("dt[class*=attributeLabel]").text());
      //System.out.println(entry.select("dd[class*=attributeValue]").text());
      //System.out.println("======");
    }
    return builder.build();

  }
  
}
