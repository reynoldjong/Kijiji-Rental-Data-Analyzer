package assignment3.Models;

import assignment3.Model.RentalListing;
import assignment3.Repository.RentalListingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class RentalListingTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RentalListingRepository rentalListingRepository;

    @Test
    public void whenFindById_thenReturnRentalListing() {
        // given
        RentalListing rentalListing = new RentalListing.RentalListingBuilder("title1", "www.test.com", "123 fake street", "$1000")
                .unitType("House")
                .bedrooms("3")
                .bathrooms("2")
                .parkingIncluded("1")
                .moveInDate("Apr 1, 2020")
                .petFriendly("Yes")
                .sizeSqft("1200")
                .furnished("Yes")
                .smokingPermitted("Outdoor Only")
                .hydroIncluded("Yes")
                .heatIncluded("Yes")
                .waterIncluded("Yes")
                .cableTvIncluded("Yes")
                .internetIncluded("Yes")
                .landlineIncluded("No")
                .yardIncluded("No")
                .balconyIncluded("No")
                .elevatorInBuildingIncluded("No")
                .build();
        assertThat(rentalListing).isInstanceOf(RentalListing.class);
        entityManager.persist(rentalListing);
        entityManager.flush();

        // when
        Optional<RentalListing> found = rentalListingRepository.findById(rentalListing.getId());

        // then
        assertThat(found.isPresent()).isEqualTo(true);
        assertThat(found.get().getTitle()).isEqualTo(rentalListing.getTitle());
        assertThat(found.get().getUrl()).isEqualTo(rentalListing.getUrl());
        assertThat(found.get().getAddress()).isEqualTo(rentalListing.getAddress());
        assertThat(found.get().getPrice()).isEqualTo(rentalListing.getPrice());
        assertThat(found.get().getUnitType()).isEqualTo(rentalListing.getUnitType());
        assertThat(found.get().getBedrooms()).isEqualTo(rentalListing.getBedrooms());
        assertThat(found.get().getBathrooms()).isEqualTo(rentalListing.getBathrooms());
        assertThat(found.get().getParkingIncluded()).isEqualTo(rentalListing.getParkingIncluded());
        assertThat(found.get().getMoveInDate()).isEqualTo(rentalListing.getMoveInDate());
        assertThat(found.get().getPetFriendly()).isEqualTo(rentalListing.getPetFriendly());
        assertThat(found.get().getSizeSqft()).isEqualTo(rentalListing.getSizeSqft());
        assertThat(found.get().getFurnished()).isEqualTo(rentalListing.getFurnished());
        assertThat(found.get().getSmokingPermitted()).isEqualTo(rentalListing.getSmokingPermitted());
        assertThat(found.get().getHydroIncluded()).isEqualTo(rentalListing.getHydroIncluded());
        assertThat(found.get().getHeatIncluded()).isEqualTo(rentalListing.getHeatIncluded());
        assertThat(found.get().getWaterIncluded()).isEqualTo(rentalListing.getWaterIncluded());
        assertThat(found.get().getCableTvIncluded()).isEqualTo(rentalListing.getCableTvIncluded());
        assertThat(found.get().getInternetIncluded()).isEqualTo(rentalListing.getInternetIncluded());
        assertThat(found.get().getLandlineIncluded()).isEqualTo(rentalListing.getLandlineIncluded());
        assertThat(found.get().getYardIncluded()).isEqualTo(rentalListing.getYardIncluded());
        assertThat(found.get().getBalconyIncluded()).isEqualTo(rentalListing.getBalconyIncluded());
        assertThat(found.get().getElevatorInBuildingIncluded()).isEqualTo(rentalListing.getElevatorInBuildingIncluded());
    }
}
