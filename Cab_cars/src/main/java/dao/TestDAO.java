package dao;

import Bean.TestBean;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDAO {

    /**
     * Inserts a record into the test table.
     * @param testBean The bean containing the value to insert.
     * @return true if insertion was successful, false otherwise.
     */
    public static boolean insertTest(TestBean testBean) {
        String sql = "INSERT INTO test (value) VALUES (?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, testBean.getValue());
            int rows = stmt.executeUpdate();
            System.out.println("Rows inserted: " + rows);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
