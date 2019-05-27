package csc.summer2019.cscc01;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) throws IOException {
    // for each input, try connecting to the website
    // ==== testing ====
    //String[] temp = new String[1];
    //temp[0] = "http://www.simplehtmlguide.com/";
 // ==== testing ====
    for (String url : args) {
      try {
        Document doc = Jsoup.connect(url).get();
        // print title, grab all the links on this webpage and print them out
        String title = doc.title();
        System.out.println(title + "\n");
        Elements links = doc.getElementsByTag("a");
        for (Element link : links) {
          String linkHref = link.attr("href");
          System.out.println("link : " + linkHref);
          String linkText = link.text();
          System.out.println("text : "+ linkText);
          // add empty line
          System.out.println("");
        }
      } catch (IOException E) {
        System.out.println("invalid URL");
      }
    }
  }
}
