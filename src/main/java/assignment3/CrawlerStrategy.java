package assignment3;

import org.jsoup.nodes.Document;

public interface CrawlerStrategy {
  public void execute(String title, Document doc);
}
