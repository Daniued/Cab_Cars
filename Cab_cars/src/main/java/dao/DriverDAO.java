package dao;

import Bean.Driver;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class DriverDAO {

    // Generate a random salt (16 bytes)
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Hash password with SHA-256 + Salt
    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes()); // Add salt to hashing process
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword); // Convert to readable format
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Register driver
    public static String registerDriver(Driver driver, String hashedPassword) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                return "Failed to obtain database connection.";
            }
            
            // Disable auto-commit for transaction control
            conn.setAutoCommit(false);

            // Insert into Users table
            String userSql = "INSERT INTO Users (username, password, salt, role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement userStmt = conn.prepareStatement(userSql)) {
                userStmt.setString(1, driver.getUsername());
                userStmt.setString(2, hashedPassword);
                userStmt.setString(3, driver.getSalt());
                userStmt.setString(4, "driver");
                int userRows = userStmt.executeUpdate();
                if (userRows <= 0) {
                    conn.rollback();
                    return "Failed to insert into Users table.";
                }
            }

            // Insert into Drivers table
            String driverSql = "INSERT INTO Drivers (username, name, phone, license, vehicle_type, plate) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement driverStmt = conn.prepareStatement(driverSql)) {
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

            // Commit the transaction if both inserts succeed
            conn.commit();
            return "";
        } catch (SQLException e) {
            e.printStackTrace();
            return "SQLException: " + e.getMessage();
        }
    }
}
