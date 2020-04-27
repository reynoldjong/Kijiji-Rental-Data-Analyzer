package assignment3.Repository;

import assignment3.Model.RentalListing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalListingRepository extends JpaRepository<RentalListing, Long> {
}
