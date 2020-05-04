package team14.KijijiRentalDataAnalyzer.Repository;

import team14.KijijiRentalDataAnalyzer.Model.RentalListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalListingRepository extends JpaRepository<RentalListing, Long> {
}
