<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<link rel="stylesheet" href="css/styles.css" />
</head>
<body>

	<div class="navbar">

		<%@include file="menu.jsp"%>

	</div>

	<div
		style="top: 70px; position: absolute; width: 100%; padding-top: 50px;">
		<div style="float: left; width: 440px">
			<span style="float: right">Customer List</span>
		</div>
	</div>
	<div style="top: 140px; height: 400px; margin-left: 450px;width:330px;overflow-y: scroll;overflow-x: scroll;"
		class="leftArea">
		<span style="background-color:grey;padding-right: 150px;">
		<span style="margin-left: 50px;">Customer Name</span>
		

		</span><ul id="customerList"></ul>

	</div>

	<div style="top: 520px; width: 1020px; position: absolute;">
		<div style="float: left;">
			<input id="addCustomer"
				style="margin-left: 90px;margin-top: 30px;margin-left: 460px;width: 90px;margin-right: 20px;"
				type="submit" value="Add ">
		</div>

		<div style="float: right;">
			<input id="updateCustomer" style="margin-left: 60px;margin-top: 30px;width: 90px;margin-right: 125px;"
				type="submit" value="Update">
		</div>
		
		<div style="float: right;">
			<input id="deleteCustomer" style="margin-right: 25px;margin-top: 30px;width: 90px;margin-left: 0px;"
				type="submit" value="Delete">
		</div>


	</div>


	<script src="js/jquery-1.7.1.min.js"></script>
	<script src="js/customer.js"></script>
	  			<script src="js/menu.js"></script>
</body>
</html>