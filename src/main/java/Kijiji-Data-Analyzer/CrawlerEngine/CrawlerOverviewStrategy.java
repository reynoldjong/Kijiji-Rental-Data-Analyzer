package assignment3.CrawlerEngine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import assignment3.Model.RentalListing;
import org.apache.commons.text.CaseUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerOverviewStrategy implements CrawlerStrategy {


  public RentalListing execute(String title, Document doc, RentalListing.RentalListingBuilder builder) {
    Elements attr = doc.select("li[class*=twoLinesAttribute]");
//    System.out.println(doc);
    Elements attrGroup = doc.select("li[class*=attributeGroupContainer]");
    for (Element a : attr) {
      String header = a.select("dt[class*=twoLinesLabel]").text();
      List<String> textList = a.select("dd[class*=twoLinesValue]").eachText();
      String value = textList.get(textList.size() - 1);
      header = CaseUtils.toCamelCase(header, false, new char[] {' ', '-', '(', ')'});
      try {
        Method m = RentalListing.RentalListingBuilder.class.getMethod(header, String.class);
        m.invoke(builder, value);
      } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {}
    }
    for (Element b : attrGroup) {
      String header = b.select("h4[class*=attributeGroupTitle]").text();
      if (header.equals("Utilities Included")) {
        Elements yesNoList =
                b.select("ul[class*=list]").select("svg[aria-label]");
        for (Element extra : yesNoList) {
          String yesNo = extra.attr("aria-label");
          if (yesNo.subSequence(0, 3).equals("Yes")) {
            try {
              String label = CaseUtils.toCamelCase(yesNo.substring(5) + " Included", false, new char[]{' ', '-', '(', ')', '/'});
              Method m = RentalListing.RentalListingBuilder.class.getMethod(label, String.class);
              m.invoke(builder, "Yes");
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {}
          }
          // not sure if there will be another result currently
        }
      } else {
        Elements wholeList = b.select("ul[class*=list]").select("li[class*=groupItem]");
        for (Element each: wholeList) {
          if (! each.text().equals("")) {
            String label = CaseUtils.toCamelCase(each.text() + " Included", false, new char[]{' ', '-', '(', ')', '/'});
            try {
              Method m = RentalListing.RentalListingBuilder.class.getMethod(label, String.class);
              m.invoke(builder, "Yes");
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {}
          }
        }
      }
    }

    /* As JSoup did not support JavaScript loaded HTML page, there might be a chance that JavaScript not completely
    loaded when JSoup connects with the url. Therefore this is for the missing content if JavaScript not loaded
    Variables are: Unit Type, Bedrooms, Bathrooms
    */
    Elements forNoJavaScriptLoaded = doc.select("li[class*=noLabelAttribute]");
    for (Element z : forNoJavaScriptLoaded) {
      String noLabelValue = z.select("span[class*=noLabelValue]").text();
      String header;
      if (noLabelValue.contains("Bed") || noLabelValue.contains("Bath")) {
        List<String> headerValue = Arrays.asList(noLabelValue.split(": ", 2));
        header = CaseUtils.toCamelCase(headerValue.get(0), false, new char[] {' ', '-', '(', ')'});
        noLabelValue = headerValue.get(1);
      } else {
        header = "unitType";
      }
      try {
        Method m = RentalListing.RentalListingBuilder.class.getMethod(header, String.class);
        m.invoke(builder, noLabelValue);
      } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {}
    }

    return builder.build();
  }
}
