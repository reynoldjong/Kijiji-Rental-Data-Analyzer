package KijijiDataAnalyzer.CrawlerEngine;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.io.IOException;

import KijijiDataAnalyzer.CrawlerEngine.Crawler;
import KijijiDataAnalyzer.Model.RentalListing;
import KijijiDataAnalyzer.Repository.RentalListingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CrawlerTest {

  @TestConfiguration
  static class CrawlerTestContextConfiguration {
    @Bean
    public Crawler crawler() {
      return new Crawler();
    }
  }

  @MockBean
  private RentalListingRepository rentalListingRepository;

  @Autowired
  private Crawler crawler;

  @Test
  public void testCrawlFromSeedLimit5() {
    try {
      crawler.crawlFromSeed(
          "https://www.kijiji.ca/b-apartments-condos/canada/c37l0", 5);
    } catch (IOException e) {
      fail();
    }
    // check to see if 5 entries are made in the db
    verify(rentalListingRepository, times(5)).save(any(RentalListing.class));
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
    verify(rentalListingRepository, times(0)).deleteAll();
  }
}
