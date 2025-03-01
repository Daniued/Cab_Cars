package servlets;

import dao.LoginDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve user input
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate login using DAO
        String role = LoginDAO.validateLogin(username, password);

        if (role != null) {
            // Create session and store user info
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);

            // Redirect based on role
            switch (role.toLowerCase()) {
                case "customer":
                    response.sendRedirect("customerDashboard.jsp");
                    break;
                case "driver":
                    response.sendRedirect("driverDashboard.jsp");
                    break;
                case "admin":
                    response.sendRedirect("adminDashboard.jsp");
                    break;
                default:
                    response.sendRedirect("login.jsp?error=12");
            }
        } else {
            // Redirect to login page with error message
            response.sendRedirect("login.jsp?error=usernot fouud");
        }
    }
}
