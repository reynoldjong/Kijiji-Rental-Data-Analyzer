package assignment3;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class that handles the data in the listing table
 */
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

    /**
     * Delete all the fields in the database
     */
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

    /**
     * Insert a row in the database using title column, but not the other details
     * @param row
     */
    public void insert(String row) {

        PreparedStatement stmt;
        // SQL code for insert
        String insertSQL = "INSERT INTO LISTING(TITLE) VALUES(?)";

        try {
            connect();
            // Create SQL statement for inserting
            stmt = this.connection.prepareStatement(insertSQL);
            stmt.setString(1, row);
            stmt.executeUpdate();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Update the value of specified column of the corresponding row in the database
     * @param row
     * @param column
     * @param value
     */
    public void update(String row, String column, String value) {
        PreparedStatement stmt;
        // SQL code for update
        String updateSQL = "UPDATE LISTING SET \"" + column + "\" = ? WHERE TITLE = ?";
        try {
            connect();
            // Create SQL statement for updating
            stmt = this.connection.prepareStatement(updateSQL);
            stmt.setString(1, value);
            stmt.setString(2, row);
            stmt.executeUpdate();
            close();

        } catch (SQLException e) {

        }

    }

    /**
     * Get all the rows in the database
     */
    public HashMap<String, ArrayList<String>> getAllRows() {

        HashMap<String, ArrayList<String>> allListing = new HashMap<>();
        // These are all the headers of columns in the database, except title
        String[] headers =
                {"url", "addr", "price", "Unit Type", "Bedrooms", "Bathrooms",
                        "Parking Included", "Move-In Date", "Pet Friendly", "Size (sqft)",
                        "Furnished", "Smoking Permitted", "Hydro Included", "Heat Included",
                        "Water Included", "Cable/TV Included", "Internet Included",
                        "Landline Included", "Yard", "Balcony", "Elevator in Building"};

        // SQL code for query
        String listSQL = "SELECT * FROM listing";
        try {
            connect();
            // Create SQL statement for queries
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(listSQL);

            // Looping all the listing and get the values into arraylist
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
}
