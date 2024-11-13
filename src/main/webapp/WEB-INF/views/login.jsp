<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="/css/welcome.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        .login-form h2 {
            margin-bottom: 20px;
            color: #333333;
        }
        .login-form .form-group {
            margin-bottom: 15px;
            text-align: left;
        }
        .login-form .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #555555;
        }
        .login-form .form-group input {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #dddddd;
            border-radius: 5px;
            transition: border-color 0.3s;
        }
        .login-form .form-group input:focus {
            border-color: #5cb85c;
            outline: none;
        }
        .login-form button {
            width: 100%;
            padding: 10px;
            background-color: #5cb85c;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .login-form button:hover {
            background-color: #4cae4c;
        }
        .signup-link, .forgot-password-link {
            margin-top: 15px;
        }
        .signup-link a, .forgot-password-link a {
            color: #007bff;
            text-decoration: none;
        }
        .signup-link a:hover, .forgot-password-link a:hover {
            text-decoration: underline;
        }
        .error-message,
                .success-message {
                    text-align: center;
                    padding: 10px;
                    border-radius: 5px;
                    margin-bottom: 15px;
                }
                .error-message {
                    background-color: #f8d7da;
                    color: #721c24;
                }
                .success-message {
                    background-color: #d4edda;
                    color: #155724;
                }
    </style>
</head>
<body>
    <div class="login-form">
         <c:if test="${not empty message}">
                    <div class="error-message">${message}</div>
                </c:if>

                <c:if test="${not empty successMessage}">
                    <div class="success-message">${successMessage}</div>
                </c:if>
        <h2>Login</h2>
        <form action="<%= request.getContextPath() %>/login" method="POST">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="aadhar">VoterCardNo:</label>
                <input type="text" id="aadhar" name="voterCardNo" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Login</button>
        </form>
        <div class="forgot-password-link">
            <p><a href="forgot">Forgot your password?</a></p>
        </div>
        <div class="signup-link">
            <p>Don't have an account? <a href="signuppage">Sign up here</a></p>
        </div>
    </div>
</body>
</html>
