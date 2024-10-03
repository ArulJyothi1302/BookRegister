<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration Form</title>
    <!-- Bootstrap CSS -->
	<link rel="stylesheet" href="css/bootstrap.css">

    <style>
        body {
            background-color: #f7f9fc;
        }
        .registration-form {
            margin: 50px auto;
            padding: 30px;
            max-width: 500px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        .form-control:focus {
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>
<input id="status" type="hidden" value="<%= request.getAttribute("status") %>"/>
    <div class="container">
        <div class="registration-form">
            <h2 class="text-center mb-4">User Registration</h2>
            <form action="ureg" method="POST" onsubmit="return validateForm()">
                <!-- Username -->
                <div class="mb-3">
                    <label for="uname" class="form-label">Username</label>
                    <input type="text" class="form-control" id="uname" name="rname" required>
                </div>

                <!-- Email -->
                <div class="mb-3">
                    <label for="uemail" class="form-label">Email</label>
                    <input type="email" class="form-control" id="uemail" name="remail" required>
                </div>

                <!-- Password -->
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="rpwd" required>
                </div>

                <!-- Confirm Password -->
                <div class="mb-3">
                    <label for="confirm_password" class="form-label">Confirm Password</label>
                    <input type="password" class="form-control" id="confirm_password" name="rcpwd" required>
                </div>

                <!-- Mobile Number -->
                <div class="mb-3">
                    <label for="mobile" class="form-label">Mobile Number</label>
                    <input type="tel" class="form-control" id="mobile" name="rmob" required pattern="[0-9]{10}" placeholder="1234567890">
                </div>

                <!-- Submit Button -->
                <button type="submit" class="btn btn-primary w-100">Register</button>
                <div class="d-flex justify-content-center py-2">
                <p><a href="Login.jsp">Already a user</a></p>
            	</div>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Client-side Form Validation -->
    <script>
        function validateForm() {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirm_password").value;
            if (password !== confirmPassword) {
                alert("Passwords do not match!");
                return false;
            }
            return true;
        }
    </script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	<script type="text/javascript">
		var status =document.getElementById("status").value;
		if(status == "success"){
			swal("Account Created Successfully","success");
		}
		
	</script>
</body>
</html>
