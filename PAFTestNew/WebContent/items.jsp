<%@ page import="model.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>User Registration </h1>
<form id="formItem" name="formItem">
 Name : 
 <input id="name" name="name" type="text" 
 class="form-control form-control-sm">
 <br> Address :
 <input id="userAddress" name="userAddress" type="text" 
 class="form-control form-control-sm">
 <br> Phone Number: 
 <input id="phoneNo" name="phoneNo" type="text" 
 class="form-control form-control-sm">
 <br> E-Mail: 
 <input id="email" name="email" type="text" 
 class="form-control form-control-sm">
 <br> User Type: 
 <input id="userType" name="userType" type="text" 
 class="form-control form-control-sm">
 <br> User Name: 
 <input id="userName" name="userName" type="text" 
 class="form-control form-control-sm">
<br> Password: 
 <input id="password" name="password" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 User userObj = new User(); 
	 out.print(userObj.readUser()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
