package team14.KijijiRentalDataAnalyzer;

import team14.KijijiRentalDataAnalyzer.CrawlerEngine.Crawler;
import team14.KijijiRentalDataAnalyzer.Model.RentalListing;
import team14.KijijiRentalDataAnalyzer.Repository.RentalListingRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class CrawlerLoading {

    @Bean
    public CommandLineRunner commandLineRunner(Crawler myCrawler, RentalListingRepository rentalListingRepository) {
        return args -> {
            if (args.length >= 2) {
                myCrawler.crawlFromSeed(args[0], Integer.parseInt(args[1]));
            }

        };
    }
}
