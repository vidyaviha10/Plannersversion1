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
		<div style="float: left; width: 420px">
			<span style="float: right">Event List</span>
		</div>
		<div style="float: left; width: 340px">
			<span style="float: right">Guest List</span>
		</div>

		<div id="addGuestlabel"
			style="float: left; width:400px; display: none;">
			<span style="float: right">Add Guest</span>
		</div>
		<div id="addEventlabel"
			style="float: left; width:400px; display: none;">
			<span style="float: right">Add Event</span>
		</div>
		<div id="updateGuestlabel"
			style="float: left; width:400px; display: none;">
			<span style="float: right">Update Guest</span>
		</div>
		<div id="updateEventlabel"
			style="float: left; width:400px; display: none;">
			<span style="float: right">Update Event</span>
		</div>
	</div>
	<div style="top: 140px; height: 400px;; margin-left: 250px;"
		class="leftArea">
		Name&nbsp;&nbsp;&nbsp;&nbsp;Name
		<ul id="eventList"></ul>

	</div>

	<div
		style="top: 140px; height: 400px; border: solid 1px #CCCCCC;; margin-left: 300px; width: 260px;"
		class="mainArea">
		<ul id="guestList"></ul>

	</div>

	<div class="rightArea"
		style="border-radius: 5px; background-color: #f2f2f2; top: 140px; height: 400px; padding-left: 20px;">
		<%@include file="addEvent.jsp"%>
	
		<%@include file="updateEvent.jsp"%>
		<%@include file="updateGuest.jsp"%>
	</div>
	<div style="top: 520px; width: 820px; position: absolute;">
		<div style="float: left;">
			<input id="addEvent"
				style="margin-left: 60px; margin-top: 30px; margin-left: 300px;"
				type="submit" value="Add Event">
		</div>

		<div style="float: right;">
			<input id="addGuest" style="margin-left: 60px; margin-top: 30px;"
				type="submit" value="Add Guest">
		</div>


	</div>


	<script src="js/jquery-1.7.1.min.js"></script>
	<script src="js/event.js"></script>
	<script src="js/guest.js"></script>
</body>
</html>