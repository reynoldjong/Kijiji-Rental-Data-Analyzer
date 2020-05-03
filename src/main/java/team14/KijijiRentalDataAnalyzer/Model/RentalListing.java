package team14.KijijiRentalDataAnalyzer.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Data
@Entity // This tells Hibernate to make a table out of this class
public class RentalListing {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private String title;
    private String url;
    private String address;
    private String price;
    private String unitType;
    private String bedrooms;
    private String bathrooms;
    private String parkingIncluded;
    private String moveInDate;
    private String petFriendly;
    private String sizeSqft;
    private String furnished;
    private String smokingPermitted;
    private String hydroIncluded;
    private String heatIncluded;
    private String waterIncluded;
    private String cableTvIncluded;
    private String internetIncluded;
    private String landlineIncluded;
    private String yardIncluded;
    private String balconyIncluded;
    private String elevatorInBuildingIncluded;

    private RentalListing(RentalListingBuilder builder) {
        this.title = builder.title;
        this.url = builder.url;
        this.address = builder.address;
        this.price = builder.price;
        this.unitType = builder.unitType;
        this.bedrooms = builder.bedrooms;
        this.bathrooms = builder.bathrooms;
        this.parkingIncluded = builder.parkingIncluded;
        this.moveInDate = builder.moveInDate;
        this.petFriendly = builder.petFriendly;
        this.sizeSqft = builder.sizeSqft;
        this.furnished = builder.furnished;
        this.smokingPermitted = builder.smokingPermitted;
        this.hydroIncluded = builder.hydroIncluded;
        this.heatIncluded = builder.heatIncluded;
        this.waterIncluded = builder.waterIncluded;
        this.cableTvIncluded = builder.cableTvIncluded;
        this.internetIncluded = builder.internetIncluded;
        this.landlineIncluded = builder.landlineIncluded;
        this.yardIncluded = builder.yardIncluded;
        this.balconyIncluded = builder.balconyIncluded;
        this.elevatorInBuildingIncluded = builder.elevatorInBuildingIncluded;

    }

    public RentalListing() {}

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    public static class RentalListingBuilder {
        private String title;
        private String url;
        private String address;
        private String price;
        private String unitType;
        private String bedrooms;
        private String bathrooms;
        private String parkingIncluded;
        private String moveInDate;
        private String petFriendly;
        private String sizeSqft;
        private String furnished;
        private String smokingPermitted;
        private String hydroIncluded = "No";
        private String heatIncluded = "No";
        private String waterIncluded = "No";
        private String cableTvIncluded = "No";
        private String internetIncluded = "No";
        private String landlineIncluded = "No";
        private String yardIncluded = "No";
        private String balconyIncluded = "No";
        private String elevatorInBuildingIncluded = "No Information";

        public RentalListingBuilder(String title, String url, String address, String price) {
            this.title = title;
            this.url = url;
            this.address = address;
            this.price = price;
        }

        public RentalListingBuilder unitType(String unitType) {
            this.unitType = unitType;
            return this;
        }

        public RentalListingBuilder bedrooms(String bedrooms) {
            this.bedrooms = bedrooms;
            return this;
        }
        
        public RentalListingBuilder bathrooms(String bathrooms) {
            this.bathrooms = bathrooms;
            return this;
        }
        
        public RentalListingBuilder parkingIncluded(String parkingIncluded) {
            this.parkingIncluded = parkingIncluded;
            return this;
        }
        
        public RentalListingBuilder moveInDate(String moveInDate) {
            this.moveInDate = moveInDate;
            return this;
        }
        
        public RentalListingBuilder petFriendly(String petFriendly) {
            this.petFriendly = petFriendly;
            return this;
        }
        
        public RentalListingBuilder sizeSqft(String sizeSqft) {
            this.sizeSqft = sizeSqft;
            return this;
        }
        
        public RentalListingBuilder furnished(String furnished) {
            this.furnished = furnished;
            return this;
        }

        public RentalListingBuilder smokingPermitted(String smokingPermitted) {
            this.smokingPermitted = smokingPermitted;
            return this;
        }

        public RentalListingBuilder hydroIncluded(String hydroIncluded) {
            this.hydroIncluded = hydroIncluded;
            return this;
        }

        public RentalListingBuilder heatIncluded(String heatIncluded) {
            this.heatIncluded = heatIncluded;
            return this;
        }
        
        public RentalListingBuilder waterIncluded(String waterIncluded) {
            this.waterIncluded = waterIncluded;
            return this;
        }

        public RentalListingBuilder cableTvIncluded(String cableTvIncluded) {
            this.cableTvIncluded = cableTvIncluded;
            return this;
        }

        public RentalListingBuilder internetIncluded(String internetIncluded) {
            this.internetIncluded = internetIncluded;
            return this;
        }

        public RentalListingBuilder landlineIncluded(String landlineIncluded) {
            this.landlineIncluded = landlineIncluded;
            return this;
        }

        public RentalListingBuilder yardIncluded(String yardIncluded) {
            this.yardIncluded = yardIncluded;
            return this;
        }

        public RentalListingBuilder balconyIncluded(String balconyIncluded) {
            this.balconyIncluded = balconyIncluded;
            return this;
        }

        public RentalListingBuilder elevatorInBuildingIncluded(String elevatorInBuildingIncluded) {
            this.elevatorInBuildingIncluded = elevatorInBuildingIncluded;
            return this;
        }


        public RentalListing build() {
            return new RentalListing(this);
        }
        
    }

}
