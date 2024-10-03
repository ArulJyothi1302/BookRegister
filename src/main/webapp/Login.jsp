<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/bootstrap.css">
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        /* Background gradient */
        body {
            background: linear-gradient(135deg, #6dd5ed, #2193b0);
        }

        /* Smooth animation on hover for input */
        .form-control:focus {
            border-color: #28a745;
            box-shadow: 0 0 10px rgba(40, 167, 69, 0.4);
            transition: box-shadow 0.3s ease, border-color 0.3s ease;
        }

        /* Button hover effect */
        .btn-success:hover {
            background-color: #218838;
            transform: scale(1.02);
            transition: transform 0.2s ease, background-color 0.2s ease;
            box-shadow: 0 4px 10px rgba(33, 136, 56, 0.3);
        }

        /* Button press (active) effect */
        .btn-success:active {
            background-color: #1e7e34;
            transform: scale(0.98);
        }

        /* Style for the form's container */
        .form-container {
            background: rgba(255, 255, 255, 0.8);
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
        }

        /* Centering the form nicely */
        form {
           
        }

        /* Align the header and form properly */
        .header-container {
            width: 100%;
        }
    </style>
</head>
<body>

    <!-- Include the header file -->
    <%@ include file="header.jsp" %>

    <!-- Form Container -->
    <input type="hidden" id ="status" value="<%=request.getAttribute("status") %>"/>
    <div class="form-container col-6 my-2 mx-auto">
        <form class="form-group" action="Login" method="post">
            <fieldset class="d-flex flex-column align-items-center">
                <legend class="text-center text-dark mb-4">Login</legend>

                <!-- Email Input -->
                <div class="form-group w-75 mb-3">
                    <label class="form-label" for="email">Email:</label>
                    <input class="form-control" type="text" id="email" name="uemail" required />
                </div>

                <!-- Password Input -->
                <div class="form-group w-75 mb-4">
                    <label class="form-label" for="password">Password:</label>
                    <input class="form-control" type="password" id="password" name="up" required />
                </div>

                <!-- Submit Button -->
                <div class="form-group w-75 text-center">
                    <input class="btn btn-success w-100" type="submit" value="Login" />
                    
                    <p><a href="signup.jsp">Create an Account</a></p>
                </div>
            </fieldset>
        </form>
    </div>
 <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	<script type="text/javascript">
		var status =document.getElementById("status").value;
		if(status == "failed"){
			swal(" Login Failed ","failed");
		}
		
	</script>
</body>
</html>
