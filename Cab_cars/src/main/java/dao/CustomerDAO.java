package dao;

import Bean.Customer;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class CustomerDAO {

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

    /**
     * Registers a new customer by inserting records into the Users and Customers tables.
     * Returns an empty string on success, or an error message on failure.
     */
    public static String registerCustomer(Customer customer, String password) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                return "Failed to obtain database connection.";
            }
            
            // Generate salt and hash password
            String salt = generateSalt();
            String hashedPassword = hashPassword(password, salt);
            
            // Disable auto-commit for transaction control
            conn.setAutoCommit(false);

            // Insert into Users table
            String userSql = "INSERT INTO Users (username, password, salt, role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement userStmt = conn.prepareStatement(userSql)) {
                userStmt.setString(1, customer.getUsername());
                userStmt.setString(2, hashedPassword);
                userStmt.setString(3, salt);
                userStmt.setString(4, "customer");
                int userRows = userStmt.executeUpdate();
                if (userRows <= 0) {
                    conn.rollback();
                    return "Failed to insert into Users table.";
                }
            }
            System.out.println("Password Before Hashing: " + password);
            System.out.println("Salt Used: " + salt);
            System.out.println("Generated Hash: " + hashPassword(password, salt));

            // Insert into Customers table
            String customerSql = "INSERT INTO Customers (name, address, phone, email, nic) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement custStmt = conn.prepareStatement(customerSql)) {
                custStmt.setString(1, customer.getName());
                custStmt.setString(2, customer.getAddress());
                custStmt.setString(3, customer.getPhone());
                custStmt.setString(4, customer.getEmail());
                custStmt.setString(5, customer.getNic());
                int custRows = custStmt.executeUpdate();
                if (custRows <= 0) {
                    conn.rollback();
                    return "Failed to insert into Customers table.";
                }
            }

            // Commit the transaction if both inserts succeed
            conn.commit();
            System.out.println("Registration - Generated Salt: " + salt);
            System.out.println("Registration - Hashed Password: " + hashedPassword);

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

    // Validate login using hashed password
    public static boolean validateLogin(String username, String password) {
        Connection conn = DBConnection.getConnection();
        String storedHash = null;
        String storedSalt = null;

        try {
            String sql = "SELECT password, salt FROM Users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                storedHash = rs.getString("password");
                storedSalt = rs.getString("salt");
            }
            
            conn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        // Hash input password with stored salt and compare
        String hashedInput = hashPassword(password, storedSalt);
        return storedHash != null && hashedInput.equals(storedHash);
    }
}
