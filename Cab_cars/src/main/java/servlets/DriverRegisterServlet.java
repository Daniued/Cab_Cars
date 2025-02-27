package servlets;

import Bean.Driver;
import dao.DriverDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DriverRegisterServlet")
public class DriverRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve parameters from the registration form
        String role = request.getParameter("role");
        if (!"driver".equalsIgnoreCase(role)) {
            response.getWriter().println("Error: Invalid role for driver registration.");
            return;
        }
        
        // Get common fields for login
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        if (!password.equals(confirmPassword)) {
            response.getWriter().println("Error: Passwords do not match.");
            return;
        }
        
        // Retrieve driver-specific fields
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");  // <-- Now included
        String license = request.getParameter("license");
        String vehicle = request.getParameter("vehicle");
        String plate = request.getParameter("plate");
        
        // Debug prints (optional)
        System.out.println("Driver registration data:");
        System.out.println("Username: " + username);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("License: " + license);
        System.out.println("Vehicle Type: " + vehicle);
        System.out.println("Plate: " + plate);
        
        // Create Driver bean
        Driver driver = new Driver(username, name, phone, license, vehicle, plate);
        
        // Register driver using DAO and capture error message if any
        String errorMessage = DriverDAO.registerDriver(driver, password);
        if (errorMessage.isEmpty()) {
            response.sendRedirect("login.jsp?message=Registration successful, please login!");
        } else {
            response.getWriter().println("Registration failed: " + errorMessage);
        }
    }
}
