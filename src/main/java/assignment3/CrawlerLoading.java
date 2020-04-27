package assignment3;

import assignment3.CrawlerEngine.Crawler;
import assignment3.Model.RentalListing;
import assignment3.Repository.RentalListingRepository;
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
                for (RentalListing rl : rentalListingRepository.findAll()) {
                    System.out.println(rl.toString());
                }
            }

        };
    }
}
