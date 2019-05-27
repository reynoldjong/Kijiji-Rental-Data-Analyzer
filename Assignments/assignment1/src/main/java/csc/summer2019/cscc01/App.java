package csc.summer2019.cscc01;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
  public String getLinkAndText(Element link) {
    /*
     * Method used to build the link and text string to be printed
     * has the format:
     * link : somelink
     * text : sometext
     */
    String result = "link : " + link.attr("href") + "\n";
    result += "text : " + link.text() + "\n";
    return result;
  }
  
  public String getTitle(Document doc) {
    String result = "title : " + doc.title() + "\n";
    return result;
  }
  public static void main(String[] args) throws IOException {
    App myApp = new App();
    for (String url : args) {
      try {
        Document doc = Jsoup.connect(url).get();
        // print title, grab all the links on this webpage and print them out
        String title = myApp.getTitle(doc);
        System.out.println(title);
        Elements links = doc.getElementsByTag("a");
        for (Element link : links) {
          String linkAndText = myApp.getLinkAndText(link);
          System.out.println(linkAndText);
        }
      } catch (IOException E) {
        System.out.println("invalid URL");
      }
    }
  }
}
