package assignment3;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

public class CrawlerOverviewStrategyTest {
  private Database db;
  private CrawlerStrategy strat;
  private Document doc;

  @Before
  public void setUp() {
    this.db = mock(Database.class);
    when(db.connect()).thenReturn(true);
    when(db.close()).thenReturn(true);
    this.strat = new CrawlerOverviewStrategy(db);
  }

  @Test
  public void testCrawlDetailedPage() {
    try {
      doc = Jsoup.connect(
          "https://www.kijiji.ca/v-apartments-condos/ottawa/stylish-20th-floor-furnished-condo-great-view-of-river-rapids/1450531157")
          .get();
      strat.execute("mock", doc);
      verify(db, times(18)).update(anyString(), anyString(), anyString());
    } catch (IOException e) {
      // page got taken down if we get here
      e.printStackTrace();
    }
  }
}
