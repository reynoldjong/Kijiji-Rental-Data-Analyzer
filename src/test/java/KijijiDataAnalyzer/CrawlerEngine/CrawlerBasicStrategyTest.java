package KijijiDataAnalyzer.CrawlerEngine;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;

import KijijiDataAnalyzer.CrawlerEngine.CrawlerBasicStrategy;
import KijijiDataAnalyzer.CrawlerEngine.CrawlerStrategy;
import KijijiDataAnalyzer.Model.RentalListing;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

public class CrawlerBasicStrategyTest {

  private RentalListing.RentalListingBuilder mockBuilder;
  private RentalListing mockListing;
  private CrawlerStrategy strat;
  private Document doc;

  @Before
  public void setUp() {
    mockBuilder = mock(RentalListing.RentalListingBuilder.class);
    mockListing = mock(RentalListing.class);
    when(mockBuilder.build()).thenReturn(mockListing);
    this.strat = new CrawlerBasicStrategy();
  }

  @Test
  public void testCrawlDetailedPage() {
    try {
      doc = Jsoup.connect(
          "https://www.kijiji.ca/v-apartments-condos/mississauga-peel-region/one-bedroom-in-condo-for-rent/1490761380")
          .get();
      RentalListing rentalListing = strat.execute("mock", doc, mockBuilder);
      assertThat(rentalListing, instanceOf(RentalListing.class));
    } catch (IOException e) {
      // page got taken down if we get here
      e.printStackTrace();
    }
  }
}
