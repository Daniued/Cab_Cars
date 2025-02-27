<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Cab Reservation</title>
    <link rel="stylesheet" type="text/css" href="styles/style.css">
    <script>
        function toggleDriverFields() {
            var role = document.getElementById("role").value;
            var driverFields = document.getElementById("driverFields");
            
            if (role === "driver") {
                driverFields.style.display = "block";
            } else {
                driverFields.style.display = "none";
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>User Registration</h2>
        <!-- Correct form tag with proper action and method -->
        <form action="<%= request.getContextPath() %>/CustomerRegisterServlet" method="post">
            <label for="role">Select Role:</label>
            <select name="role" id="role" onchange="toggleDriverFields()" required>
                <option value="customer">Customer</option>
                <option value="driver">Driver</option>
            </select>

            <label for="username">Username:</label>
            <input type="text" name="username" id="username" placeholder="Enter Username" required>

            <label for="password">Password:</label>
            <input type="password" name="password" id="password" placeholder="Enter Password" required>

            <label for="confirm_password">Confirm Password:</label>
            <input type="password" name="confirm_password" id="confirm_password" placeholder="Confirm Password" required>

            <label for="name">Full Name:</label>
            <input type="text" name="name" id="name" placeholder="Enter Full Name" required>

            <label for="email">Email:</label>
            <input type="email" name="email" id="email" placeholder="Enter Email Address" required>

            <label for="phone">Phone Number:</label>
            <input type="text" name="phone" id="phone" placeholder="Enter Phone Number" required>

            <label for="address">Address:</label>
            <textarea name="address" id="address" placeholder="Enter Address" required></textarea>

            <label for="nic">NIC (National ID):</label>
            <input type="text" name="nic" id="nic" placeholder="Enter NIC" required>

            <!-- Driver-Specific Fields -->
            <div id="driverFields" style="display: none;">
                <label for="license">Driver License Number:</label>
                <input type="text" name="license" id="license" placeholder="Enter License Number">

                <label for="vehicle">Vehicle Type:</label>
                <select name="vehicle" id="vehicle">
                    <option value="Car">Car</option>
                    <option value="Van">Van</option>
                    <option value="Bike">Bike</option>
                </select>

                <label for="plate">Vehicle Plate Number:</label>
                <input type="text" name="plate" id="plate" placeholder="Enter Plate Number">
            </div>

            <button type="submit" class="btn">Register</button>
        </form>
        <p>Already have an account? <a href="index.jsp">Login here</a></p>
    </div>
</body>
</html>
s