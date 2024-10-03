<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class=" bg-info ">
<% if(session.getAttribute("user")==null){
	response.sendRedirect("Login.jsp");
} %>
<%@ include file="header.jsp" %>
<div class="container-fluid card my-4" style="width:40rem">
	<h1 class="bg-danger text-white text-center rounded ">Book Registration</h1>
	<form action="register"  method="post" class=""  >
		<table class="table table-hover">
			<tr>
			<td> Book Name</td>
				<td>
				<input type='text' name='bookName'>
				</td>
				
			</tr>
			<tr>
			<td> Book Edition</td>
				<td>
				<input type='text' name='bookEdition'>
				</td>
				
			</tr>
			<tr>
			<td> Book Price</td>
				<td>
				<input type='text' name='bookPrice'>
				</td>
				
			</tr>
			
		</table>
		<hr>
		<div class="d-flex justify-content-between m-4">
		<div>
		<input class="btn btn-primary text-white" type="submit" value="Register">
		</div>
		<div>
		<a class="btn btn-warning mx-4" href="booklist">Book List</a>
		</div>
		<div>
		<input class="btn btn-danger texr-white float-right" type='reset' value='cancel'>
		</div>
		
		</div>
	</form>
	
	</div>
</body>
</html>