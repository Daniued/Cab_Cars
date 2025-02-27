package dao;

import Bean.Customer;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {

    // SQL statements using table names and fields from your schema
    private static final String INSERT_USER_SQL = 
        "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";
    private static final String INSERT_CUSTOMER_SQL = 
        "INSERT INTO Customers (name, address, phone, email, nic) VALUES (?, ?, ?, ?, ?)";
    
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
            
            // Disable auto-commit for transaction control.
            conn.setAutoCommit(false);
            
            // Insert into Users table.
            try (PreparedStatement userStmt = conn.prepareStatement(INSERT_USER_SQL)) {
                userStmt.setString(1, customer.getUsername());
                userStmt.setString(2, password); // Consider hashing for production use.
                userStmt.setString(3, "customer");
                int userRows = userStmt.executeUpdate();
                System.out.println("User rows inserted: " + userRows);
                if (userRows <= 0) {
                    conn.rollback();
                    return "Failed to insert into Users table.";
                }
            }
            
            // Insert into Customers table.
            try (PreparedStatement custStmt = conn.prepareStatement(INSERT_CUSTOMER_SQL)) {
                custStmt.setString(1, customer.getName());
                custStmt.setString(2, customer.getAddress());
                custStmt.setString(3, customer.getPhone());
                custStmt.setString(4, customer.getEmail());
                custStmt.setString(5, customer.getNic());
                int custRows = custStmt.executeUpdate();
                System.out.println("Customer rows inserted: " + custRows);
                if (custRows <= 0) {
                    conn.rollback();
                    return "Failed to insert into Customers table.";
                }
            }
            
            // Commit the transaction if both inserts succeed.
            conn.commit();
            return "";  // Success
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
