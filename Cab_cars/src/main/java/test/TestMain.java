package test;

import Bean.TestBean;
import dao.TestDAO;

public class TestMain {
    public static void main(String[] args) {
        // Create a test bean with a sample value
        TestBean testBean = new TestBean("Hello, Database!");
        
        // Insert into the test table
        boolean result = TestDAO.insertTest(testBean);
        
        if (result) {
            System.out.println("Data inserted successfully!");
        } else {
            System.out.println("Data insertion failed.");
        }
    }
}
