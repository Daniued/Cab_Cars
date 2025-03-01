package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.util.Base64;

public class LoginDAO {

    // Hash password with SHA-256 + Salt
    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Validate user login
    public static String validateLogin(String username, String password) {
        Connection conn = DBConnection.getConnection();
        String storedHash = null;
        String storedSalt = null;
        String role = null;

        try {
            String sql = "SELECT password, salt, role FROM Users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                storedHash = rs.getString("password");
                storedSalt = rs.getString("salt");
                role = rs.getString("role");
            }
            conn.close();

            // Debugging logs
            System.out.println("Stored Hash: " + storedHash);
            System.out.println("Generated Hash: " + hashPassword(password, storedSalt));
            System.out.println("Salt Used: " + storedSalt);
            System.out.println("Role from DB: " + role);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        // Hash input password with stored salt and compare
        if (storedHash != null && storedSalt != null) {
            String hashedInput = hashPassword(password, storedSalt);
            if (hashedInput.equals(storedHash)) {
                return role; // Return role if password is correct
            } else {
                System.out.println("Hashes do not match!");
            }
        } else {
            System.out.println("User not found or incomplete data in the database.");
        }
        return null; // Invalid login
    }
}
