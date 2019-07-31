package assignment3;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CrawlerMain {
  /*
   * Use the following maven commands to run crawler
   * start seed: https://www.kijiji.ca/b-apartments-condos/canada/c37l0
   * crawl limit: 5
   * 
   * PowerShell:
   * mvn exec:java "-Dexec.mainClass=assignment3.CrawlerMain" "-Dexec.args=https://www.kijiji.ca/b-apartments-condos/canada/c37l0 5"
   * 
   * Linux / Command Prompt
   * mvn exec:java -Dexec.mainClass="assignment3.CrawlerMain" -Dexec.args="https://www.kijiji.ca/b-apartments-condos/canada/c37l0 5"
   */
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Usage: \"url\" \"limit\"");
      System.exit(-1);
    }
    // get crawler limit
    int limit = 0;
    try {
      limit = Integer.parseInt(args[1]);
    } 
    catch (NumberFormatException e) {
      System.out.println("Usage: \"url\" \"limit\"");
      System.out.println("limit must be a number");
      System.exit(-1);
    }
    // get seed url
    try {
      URL url = new URL(args[0]);
    } 
    catch (MalformedURLException me) {
      System.out.println("Usage: \"url\" \"limit\"");
      System.out.println("invalid seed url");
      System.exit(-1);
    }
    
    // db could be another arg too, but I think that is out of scope for A3
    Database db = new MockDB(); // TODO: fix this later when DB is done
    Crawler crawler = new Crawler(db);
    try {
      System.out.println("Starting crawler from seed: " + args[0]);
      System.out.println("With crawl limit: " + args[1]);
      crawler.crawlFromSeed(args[0], limit);
    } catch (IOException e) {
      System.out.println("something went wrong with the crawler...");
      System.exit(-1);
    }
    System.out.println("Crawler has finished crawling!");
  }
}
