package assignment3.Controller;

import assignment3.Application;
import assignment3.Model.RentalListing;
import assignment3.Repository.RentalListingRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ViewsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RentalListingRepository rentalListingRepository;

    @After
    public void resetDb() {
        rentalListingRepository.deleteAll();
    }

    @Test
    public void whenGetChartView_thenReturnAllInformationNeededForChartView() throws Exception {
        RentalListing rentalListingOne = new RentalListing.RentalListingBuilder("title1", "www.test.com", "123 fake street", "$1000")
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
        RentalListing rentalListingTwo = new RentalListing.RentalListingBuilder("title2", "www.fakehouse.com", "321 real road ave", "$9001")
                .unitType("Condo")
                .bedrooms("3")
                .bathrooms("2")
                .parkingIncluded("1")
                .moveInDate("May 1, 2020")
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
        rentalListingRepository.saveAndFlush(rentalListingOne);
        rentalListingRepository.saveAndFlush(rentalListingTwo);


        mvc.perform(get("/chartview")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title1.url", is(rentalListingOne.getUrl())))
                .andExpect(jsonPath("$.title1.address", is(rentalListingOne.getAddress())))
                .andExpect(jsonPath("$.title1.price", is(rentalListingOne.getPrice())))
                .andExpect(jsonPath("$.title1.unitType", is(rentalListingOne.getUnitType())))
                .andExpect(jsonPath("$.title1.bedrooms", is(rentalListingOne.getBedrooms())))
                .andExpect(jsonPath("$.title1.bathrooms", is(rentalListingOne.getBathrooms())))
                .andExpect(jsonPath("$.title1.parkingIncluded", is(rentalListingOne.getParkingIncluded())))              
                .andExpect(jsonPath("$.title1.petFriendly", is(rentalListingOne.getPetFriendly())))
                .andExpect(jsonPath("$.title1.sizeSqft", is(rentalListingOne.getSizeSqft())))
                .andExpect(jsonPath("$.title1.furnished", is(rentalListingOne.getFurnished())))
                .andExpect(jsonPath("$.title1.smokingPermitted", is(rentalListingOne.getSmokingPermitted())))
                .andExpect(jsonPath("$.title1.hydroIncluded", is(rentalListingOne.getHydroIncluded())))                
                .andExpect(jsonPath("$.title1.heatIncluded", is(rentalListingOne.getHeatIncluded())))
                .andExpect(jsonPath("$.title1.waterIncluded", is(rentalListingOne.getWaterIncluded())))
                .andExpect(jsonPath("$.title1.cableTvIncluded", is(rentalListingOne.getCableTvIncluded())))
                .andExpect(jsonPath("$.title1.internetIncluded", is(rentalListingOne.getInternetIncluded())))
                .andExpect(jsonPath("$.title1.landlineIncluded", is(rentalListingOne.getLandlineIncluded())))
                .andExpect(jsonPath("$.title1.yardIncluded", is(rentalListingOne.getYardIncluded())))
                .andExpect(jsonPath("$.title1.balconyIncluded", is(rentalListingOne.getBalconyIncluded())))
                .andExpect(jsonPath("$.title1.elevatorInBuildingIncluded", is(rentalListingOne.getElevatorInBuildingIncluded())))
                .andExpect(jsonPath("$.title2.url", is(rentalListingTwo.getUrl())))
                .andExpect(jsonPath("$.title2.address", is(rentalListingTwo.getAddress())))
                .andExpect(jsonPath("$.title2.price", is(rentalListingTwo.getPrice())))
                .andExpect(jsonPath("$.title2.unitType", is(rentalListingTwo.getUnitType())))
                .andExpect(jsonPath("$.title2.bedrooms", is(rentalListingTwo.getBedrooms())))
                .andExpect(jsonPath("$.title2.bathrooms", is(rentalListingTwo.getBathrooms())))
                .andExpect(jsonPath("$.title2.parkingIncluded", is(rentalListingTwo.getParkingIncluded())))
                .andExpect(jsonPath("$.title2.petFriendly", is(rentalListingTwo.getPetFriendly())))
                .andExpect(jsonPath("$.title2.sizeSqft", is(rentalListingTwo.getSizeSqft())))
                .andExpect(jsonPath("$.title2.furnished", is(rentalListingTwo.getFurnished())))
                .andExpect(jsonPath("$.title2.smokingPermitted", is(rentalListingTwo.getSmokingPermitted())))
                .andExpect(jsonPath("$.title2.hydroIncluded", is(rentalListingTwo.getHydroIncluded())))
                .andExpect(jsonPath("$.title2.heatIncluded", is(rentalListingTwo.getHeatIncluded())))
                .andExpect(jsonPath("$.title2.waterIncluded", is(rentalListingTwo.getWaterIncluded())))
                .andExpect(jsonPath("$.title2.cableTvIncluded", is(rentalListingTwo.getCableTvIncluded())))
                .andExpect(jsonPath("$.title2.internetIncluded", is(rentalListingTwo.getInternetIncluded())))
                .andExpect(jsonPath("$.title2.landlineIncluded", is(rentalListingTwo.getLandlineIncluded())))
                .andExpect(jsonPath("$.title2.yardIncluded", is(rentalListingTwo.getYardIncluded())))
                .andExpect(jsonPath("$.title2.balconyIncluded", is(rentalListingTwo.getBalconyIncluded())))
                .andExpect(jsonPath("$.title2.elevatorInBuildingIncluded", is(rentalListingTwo.getElevatorInBuildingIncluded())));
    }

    @Test
    public void whenGetMapView_thenReturnAllInformationNeededForChartView() throws Exception {
        RentalListing rentalListingOne = new RentalListing.RentalListingBuilder("title1", "www.test.com", "40 St George St, Toronto, ON M5S 2E4", "$1000")
                .build();
        RentalListing rentalListingTwo = new RentalListing.RentalListingBuilder("title2", "www.fakehouse.com", "1295 Military TrailScarborough, ON M1C 3A8", "$9001")
                .build();
        rentalListingRepository.saveAndFlush(rentalListingOne);
        rentalListingRepository.saveAndFlush(rentalListingTwo);

        // From google
        String latitudeOfListingOne = "43.6596184";
        String longitudeOfListingOne = "-79.39692079999999";
        String latitudeOfListingTwo = "43.784841";
        String longitudeOfListingTwo = "-79.18471559999999";

        mvc.perform(get("/mapview")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].address", is(rentalListingOne.getAddress())))
                .andExpect(jsonPath("$[0].price", is(rentalListingOne.getPrice())))
                .andExpect(jsonPath("$[0].lat", is(latitudeOfListingOne)))
                .andExpect(jsonPath("$[0].lng", is(longitudeOfListingOne)))
                .andExpect(jsonPath("$[1].address", is(rentalListingTwo.getAddress())))
                .andExpect(jsonPath("$[1].price", is(rentalListingTwo.getPrice())))
                .andExpect(jsonPath("$[1].lat", is(latitudeOfListingTwo)))
                .andExpect(jsonPath("$[1].lng", is(longitudeOfListingTwo)));

    }
}
