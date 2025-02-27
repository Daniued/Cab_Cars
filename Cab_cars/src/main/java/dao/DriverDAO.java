package dao;

import Bean.Driver;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DriverDAO {

    // SQL for inserting into Users table.
    private static final String INSERT_USER_SQL = 
        "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";
    // SQL for inserting into Drivers table.
    
    private static final String INSERT_DRIVER_SQL = 
    	    "INSERT INTO Drivers (username, name, phone, license, vehicle_type, plate) VALUES (?, ?, ?, ?, ?, ?)";


    
    /**
     * Registers a new driver by inserting records into the Users and Drivers tables.
     * Returns an empty string on success, or an error message on failure.
     */
    public static String registerDriver(Driver driver, String password) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                return "Failed to obtain database connection.";
            }
            
            // Disable auto-commit for transaction control.
            conn.setAutoCommit(false);
            
            // Insert into Users table.
            try (PreparedStatement userStmt = conn.prepareStatement("INSERT INTO Users (username, password, role) VALUES (?, ?, ?)")) {
                userStmt.setString(1, driver.getUsername());
                userStmt.setString(2, password);
                userStmt.setString(3, "driver");
                int userRows = userStmt.executeUpdate();
                if (userRows <= 0) {
                    conn.rollback();
                    return "Failed to insert into Users table.";
                }
            }
            
            // Insert into Drivers table.
            try (PreparedStatement driverStmt = conn.prepareStatement(INSERT_DRIVER_SQL)) {
                driverStmt.setString(1, driver.getUsername());
                driverStmt.setString(2, driver.getName());
                driverStmt.setString(3, driver.getPhone());
                driverStmt.setString(4, driver.getLicense());
                driverStmt.setString(5, driver.getVehicleType());
                driverStmt.setString(6, driver.getPlate());
                int driverRows = driverStmt.executeUpdate();
                if (driverRows <= 0) {
                    conn.rollback();
                    return "Failed to insert into Drivers table.";
                }
            }
            
            // Commit the transaction if both inserts succeed.
            conn.commit();
            return "";
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return "SQLException: " + e.getMessage();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    }

