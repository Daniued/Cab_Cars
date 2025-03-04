package test;

import java.sql.Connection;
import db.DBConnection;

public class TestConnection {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("Database Connection Test Successful!");
        } else {
            System.out.println("Database Connection Test Failed!");
        }
    }
}
