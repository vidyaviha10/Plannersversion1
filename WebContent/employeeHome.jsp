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
			<span style="float: right">Employee List</span>
		</div>
	</div>
	<div style="top: 140px; height: 400px; margin-left: 450px;width:430px;overflow-y: hidden;overflow-x: hidden;"
		class="leftArea">
		<span style="background-color:grey;padding-right: 102px;">
		<span style="margin-left: 50px;">Employee Name</span>
		<span style="margin-left: 40px;">Employee Id</span>

		</span><ul id="employeeList"></ul>

	</div>

	<div style="top: 520px; width: 1020px; position: absolute;">
		<div style="float: left;">
			<input id="addEmployee"
				style="margin-left: 90px;margin-top: 30px;margin-left: 460px;width: 90px;margin-right: 20px;"
				type="submit" value="Add ">
		</div>

		<div style="float: right;">
			<input id="updateEmployee" style="margin-left: 60px;margin-top: 30px;width: 90px;margin-right: 125px;"
				type="submit" value="Update">
		</div>
		
		<div style="float: right;">
			<input id="deleteEmployee" style="margin-right: 25px;margin-top: 30px;width: 90px;margin-left: 0px;"
				type="submit" value="Delete">
		</div>


	</div>


	<script src="js/jquery-1.7.1.min.js"></script>
	<script src="js/employee.js"></script>
	  			<script src="js/menu.js"></script>
</body>
</html>