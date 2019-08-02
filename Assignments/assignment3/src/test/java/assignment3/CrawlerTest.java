package assignment3;

import java.io.IOException;
import java.lang.reflect.Field;
import javax.servlet.ServletException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class CrawlerTest {
  private Crawler crawler;
  private Database db;

  @Before
  public void setUp() {
    this.db = mock(Database.class);
    when(db.connect()).thenReturn(true);
    when(db.close()).thenReturn(true);
    this.crawler = new Crawler(db);
  }

  @Test
  public void testCrawlFromSeedLimit5() {
    try {
      crawler.crawlFromSeed("https://www.kijiji.ca/b-apartments-condos/canada/c37l0", 5);
    } catch (IOException e) {
      fail();
    }
    // check to see if 5 entries are made in the db
    verify(db, times(5)).insert(anyString());
  }
  
  @Test
  public void testBadSeed() {
    String url = "https://www.google.com/";
    try {
      crawler.crawlFromSeed(url, 5);
    } catch (IOException e) {
      fail();
    }
    // should terminate because of bad seed before db is deleted
    verify(db, times(0)).deleteAll();
  }
}
