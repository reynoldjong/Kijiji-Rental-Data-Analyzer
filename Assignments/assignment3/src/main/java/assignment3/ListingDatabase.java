package assignment3;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListingDatabase implements Database {

    public Connection connection;

    /**
     * Make a connection to database
     */
    public boolean connect() {

        // Connect to Rental.db at project folder and return true if it is successful.
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:Rental.db");
            return true;

        } catch (SQLException e) {

            System.out.println("Can't connect to database");
            return false;
        }
    }

    /**
     * Close a database connection if it is connected
     */
    public boolean close() {
        if (this.connection != null) {
            try {

                this.connection.close();

                return true;

            } catch (SQLException e) {

                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public void deleteAll() {
        PreparedStatement stmt;

        // SQL code for delete
        String deleteSQL = "DELETE FROM LISTING";

        try {
            connect();
            // Create SQL statement for deleting
            stmt = this.connection.prepareStatement(deleteSQL);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }

    public void insert(String row) {

        PreparedStatement stmt;
        String insertSQL = "INSERT INTO LISTING(TITLE) VALUES(?)";

        try {
            connect();
            stmt = this.connection.prepareStatement(insertSQL);
            stmt.setString(1, row);
            stmt.executeUpdate();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(String row, String column, String value) {
        PreparedStatement stmt;
        String updateSQL = "UPDATE LISTING SET \"" + column + "\" = ? WHERE TITLE = ?";
        try {
            connect();
            // Create SQL statement for inserting
            stmt = this.connection.prepareStatement(updateSQL);
            stmt.setString(1, value);
            stmt.setString(2, row);
            stmt.executeUpdate();
            close();

        } catch (SQLException e) {

        }

    }

    public HashMap<String, ArrayList<String>> getAllRows() {

        HashMap<String, ArrayList<String>> allListing = new HashMap<String, ArrayList<String>>();
        String[] headers =
                {"url", "addr", "price", "Unit Type", "Bedrooms", "Bathrooms",
                        "Parking Included", "Move-In Date", "Pet Friendly", "Size (sqft)",
                        "Furnished", "Smoking Permitted", "Hydro Included", "Heat Included",
                        "Water Included", "Cable/TV Included", "Internet Included",
                        "Landline Included", "Yard", "Balcony", "Elevator in Building"};

        String listSQL = "SELECT * FROM listing";
        try {
            connect();
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(listSQL);

            while (result.next()) {
                String title = result.getString("title");
                ArrayList<String> listingDetails = new ArrayList<>();
                for (String s : headers) {
                    listingDetails.add(result.getString(s));
                }
                allListing.put(title, listingDetails);
            }
            close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allListing;

    }

    public static void main (String args[]) {
        ListingDatabase lb = new ListingDatabase();
        lb.insert("test");
        lb.insert("test2");
        lb.insert("test3");
        lb.update("test", "addr", "2525 Hamilton Road, Trenton, ON, K8V 6N6");
        lb.update("test2", "addr", "341 Flora Street, Ottawa, ON, K1R 5S2");
        lb.update("test3", "addr", "160 Chapel St. Ottawa, ON K1N 8P5, Ottawa, ON, K1N 8P5");
    }
}
