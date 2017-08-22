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
		<div style="float: left; width: 240px">
			<span style="float: right">Event List</span>
		</div>
	</div>
	<div style="top: 140px; height: 400px;; margin-left: 250px;width:800px;"
		class="leftArea">
		<span style="background-color:grey;padding-right: 3px;">
		<span style="margin-left: 50px;">Name</span>
		<span style="margin-left: 100px;">Venue</span>
		<span style="margin-left: 100px;">Date & Time</span>
		<span style="margin-left: 100px;margin-right:88px;">Customer Name</span>

		</span><ul id="eventList"></ul>

	</div>

	<div style="top: 520px; width: 1020px; position: absolute;">
		<div style="float: left;">
			<input id="addEvent"
				style="margin-left: 60px; margin-top: 30px; margin-left: 300px;"
				type="submit" value="Add Event">
		</div>

		<div style="float: right;">
			<input id="updateEvent" style="margin-left: 60px; margin-top: 30px;"
				type="submit" value="Update Event">
		</div>
		
		<div style="float: right;">
			<input id="deleteEvent" style="margin-right: 40px; margin-top: 30px;"
				type="submit" value="Delete Event">
		</div>


	</div>


	<script src="js/jquery-1.7.1.min.js"></script>
	<script src="js/event.js"></script>

	
			<script src="js/menu.js"></script>
</body>
</html>