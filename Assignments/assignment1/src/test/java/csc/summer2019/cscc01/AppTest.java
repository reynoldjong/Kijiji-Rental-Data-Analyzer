package csc.summer2019.cscc01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private App myApp;
    
    @Before
    public void setUp() {
      myApp = new App();
    }
    
    @Test
    public void getTitleTest()
    {
        String url = "http://www.simplehtmlguide.com/";
        try {
          Document doc = Jsoup.connect(url).get();
          String result = myApp.getTitle(doc);
          String expected = "title : A Simple Guide to HTML - Welcome\n";
          assertEquals(result, expected);
        }
        catch (IOException E) {
          fail();
        }
    }
    
    @Test
    public void getLinkOne()
    {
        String url = "http://www.simplehtmlguide.com/";
        try {
          Document doc = Jsoup.connect(url).get();
          Elements links = doc.getElementsByTag("a");
          // elements is a list
          Element firstElement = links.first();
          String result = myApp.getLinkAndText(firstElement);
          String expected = "link : /\ntext : home\n";
          assertEquals(result, expected);
        }
        catch (IOException E) {
          fail();
        }
    }
    
    @Test
    public void getLinkTwo()
    {
        String url = "http://www.simplehtmlguide.com/";
        try {
          Document doc = Jsoup.connect(url).get();
          Elements links = doc.getElementsByTag("a");
          // elements is a list
          Element secondElement = links.get(1);
          String result = myApp.getLinkAndText(secondElement);
          String expected = "link : essential.php\ntext : html guide\n";
          assertEquals(result, expected);
        }
        catch (IOException E) {
          fail();
        }
    }
}
