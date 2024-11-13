 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup Form</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/css/welcome.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .signup-form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
            margin-top:40px;
        }
        .signup-form h2 {
            margin-bottom: 20px;
        }
        .signup-form .form-group {
            margin-bottom: 15px;
            text-align: left;
        }
        .signup-form .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .signup-form .form-group input,
        .signup-form .form-group select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .signup-form button {
            width: 100%;
            padding: 10px;
            background-color: #5cb85c;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .signup-form button:hover {
            background-color: #4cae4c;
        }
        .login-link {
            margin-top: 15px;
        }
        .login-link a {
            color: #007bff;
            text-decoration: none;
        }
        .login-link a:hover {
            text-decoration: underline;
        }
       .error-message,
                        {
                           text-align: center;
                           padding: 10px;
                           border-radius: 5px;
                           margin-bottom: 15px;
                       }
                       .error-message {
                           background-color: #f8d7da;
                           color: #721c24;
                       }

    </style>
</head>
<body>
    <div class="signup-form">
     <c:if test="${not empty message}">
                        <div class="error-message">${message}</div>
                    </c:if>

                    <c:if test="${not empty error}">
                        <div class="error-message">${error}</div>
                    </c:if>
        <h2>Signup</h2>

        <form action="register" method="POST"> 
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="votercard">VoterCard Number:</label>
                <input type="text" id="votercard" name="VoterCardNo" minlength="10" maxlength="10" required>
            </div>
            <div class="form-group">
                <label for="mobile">Mobile Number:</label>
                <input type="tel" id="mobile" name="mobile" minlength="10" maxlength="10" required>
            </div>
            <div class="form-group">
                <label for="gender">Gender:</label>
                <select id="gender" name="gender" required>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                </select>
            </div>
            <div class="form-group">
                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dob" min="1950-01-01" min="2024-12-12" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Signup</button>
        </form>
        <div class="login-link">
            <p>Already signed up? <a href="loginpage">Login here</a></p>
        </div>
    </div>
</body>
</html>
