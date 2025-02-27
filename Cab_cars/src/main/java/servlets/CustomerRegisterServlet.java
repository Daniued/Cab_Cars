package servlets;

import Bean.Customer;
import dao.CustomerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CustomerRegisterServlet")
public class CustomerRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve parameters from the registration form
        String role = request.getParameter("role");
        
        // Ensure we're processing customer registration
        if (!"customer".equalsIgnoreCase(role)) {
            response.getWriter().println("Error: Invalid role for customer registration.");
            return;
        }
        
        // Get common fields
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        if (!password.equals(confirmPassword)) {
            response.getWriter().println("Error: Passwords do not match.");
            return;
        }
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");
        
        // (Optional) Print values to console for debugging
        System.out.println("Received registration data:");
        System.out.println("Username: " + username);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        System.out.println("NIC: " + nic);
        
        // Create Customer bean
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setNic(nic);
        
        // Register customer using DAO and capture error message if any
        String errorMessage = CustomerDAO.registerCustomer(customer, password);
        if (errorMessage.isEmpty()) {
            // Redirect to login page (adjust URL as needed)
            response.sendRedirect("index.jsp?message=Registration successful, please login!");
        } else {
            // Display detailed error message
            response.getWriter().println("Registration failed: " + errorMessage);
        }
    }
}
