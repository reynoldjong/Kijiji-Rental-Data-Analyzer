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
        String updateSQL = "UPDATE LISTING SET " + column + " = ? WHERE TITLE = ?";
        try {
            connect();
            // Create SQL statement for inserting
            stmt = this.connection.prepareStatement(updateSQL);
            stmt.setString(1, value);
            stmt.setString(2, row);
            stmt.executeUpdate();
            close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public HashMap<String, ArrayList<String>> getAllRows() {

        HashMap<String, ArrayList<String>> allListing = new HashMap<String, ArrayList<String>>();

        String listSQL = "SELECT * FROM listing ORDER BY price";
        try {
            connect();
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(listSQL);

            while (result.next()) {
                ArrayList<String> listingDetails = new ArrayList<String>();
                String url = result.getString("url");
                listingDetails.add(url);
                String address = result.getString("addr");
                listingDetails.add(address);
                String price = String.valueOf(result.getFloat("price"));
                listingDetails.add(price);
                String title = result.getString("title");
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
        GeoCodingEngine geoCodingEngine = new GeoCodingEngine();
        ArrayList<String> coordinates = new ArrayList<>();
        HashMap<String, ArrayList<String>> allListing = lb.getAllRows();
        for (Map.Entry<String, ArrayList<String>> eachListing : allListing.entrySet()) {
            String address = eachListing.getValue().get(1);
            String coord = geoCodingEngine.getCoorindate(address);
            coordinates.add(coord);
        }
        System.out.println(coordinates.toString());
    }
}
