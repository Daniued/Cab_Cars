<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Cab Reservation</title>
    <link rel="stylesheet" href="styles/style.css"> <!-- External CSS -->
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background: #F8EDEB;
            font-family: 'Arial', sans-serif;
        }
        .container {
            display: flex;
            width: 900px;
            background: white;
            border-radius: 20px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            overflow: hidden;
        }
        .left-panel {
            flex: 1;
            background: #FDE2E4;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            padding: 40px;
            text-align: center;
        }
        .left-panel img {
            width: 80%;
            max-width: 300px;
        }
        .left-panel h2 {
            color: #8B2F3C;
            margin-top: 20px;
            font-size: 22px;
        }
        .right-panel {
            flex: 1;
            padding: 40px;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
            text-align: center;
        }
        input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        .btn {
            width: 100%;
            background: #8B2F3C;
            color: white;
            border: none;
            padding: 12px;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            transition: 0.3s;
        }
        .btn:hover {
            background: #6A1B2E;
        }
        .links {
            text-align: center;
            margin-top: 15px;
        }
        .links a {
            text-decoration: none;
            color: #8B2F3C;
            font-weight: bold;
        }
        .links a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="left-panel">
            <img src="images/login-illustration.png" alt="Illustration">
            <h2>Turn your ideas into reality.</h2>
            <p>Start for free and get exclusive offers from the community.</p>
        </div>
        <div class="right-panel">
            <h2>Login to Your Account</h2>
            <form action="LoginServlet" method="post">
                <input type="text" name="username" placeholder="Enter Username" required>
                <input type="password" name="password" placeholder="Enter Password" required>
                <button type="submit" class="btn">Login</button>
            </form>
            <div class="links">
                <p><a href="register.jsp">Create an Account</a></p>
                <p><a href="index.jsp">Back to Home</a></p>
            </div>
        </div>
    </div>
</body>
</html>
