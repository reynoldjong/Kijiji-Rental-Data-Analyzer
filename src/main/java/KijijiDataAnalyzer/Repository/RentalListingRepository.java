package KijijiDataAnalyzer.Repository;

import KijijiDataAnalyzer.Model.RentalListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalListingRepository extends JpaRepository<RentalListing, Long> {
}
