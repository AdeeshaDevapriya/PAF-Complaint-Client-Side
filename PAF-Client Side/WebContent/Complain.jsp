<%@page import="com.Complain"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complain Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/complain.js"></script>

</head>
<body>

<div class="container"><div class="row"><div class="col-6">
<h1>Complain Management</h1>
<form id="formComplain" name="formComplain">
 Complainer's Full Name:
 <input id="name" name="name" type="text"
 class="form-control form-control-sm">
 <br> User Address:
 <input id="address" name="address" type="text"
 class="form-control form-control-sm">
 <br> Email:
 <input id="email" name="email" type="text"
 class="form-control form-control-sm">
 <br> Contact Number:
 <input id="contact_no" name="contact_no" type="text"
 class="form-control form-control-sm">
 <br> account_no:
 <input id="account_no" name="account_no" type="text"
 class="form-control form-control-sm">
 <br> complain:
 <input id="complain" name="complain" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Submit"
 class="btn btn-primary">
 <input type="hidden" id="hidComplainIDSave"
 name="hidComplainIDSave" value="">
 

</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divComplainsGrid">
 <%
 Complain complainObj = new Complain();
 out.print(complainObj.readComplains());
 %>
 
</div>
</div> </div> </div>
<br><br>


</body>
</html>