<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Planners</title>
<link rel="stylesheet" href="css/styles.css" />
</head>

<body>

<div class="header">
	<input type="text" id="searchKey"/>
	<button id="btnSearch">Search</button>
	<button id="btnAdd">Add Guest</button>
</div>


<div class="leftArea">
<ul id="guestList"></ul>
</div>

<form id="wineForm">
	
<div class="mainArea">

<label>Id:</label>
<input id="guestId" name="id" type="text" disabled />

<label>Name:</label>
<input type="text" id="name" name="name" required>

<label>Email:</label>
<input type="text" id="phone" name="phone"/>

<label>Phone:</label>
<input type="text" id="email" name="email"/>

<label>Weight:</label>
<input type="text" id="weight" name="weight"/>


<button id="btnSave">Save</button>
<button id="btnDelete">Delete</button>

</div>

<div class="rightArea">

<img id="pic" height="300"/>

<label>Notes:</label>
<textarea id="description" name="description"></textarea>
</div>

</form>

<script src="js/jquery-1.7.1.min.js"></script>
<script src="js/main.js"></script>

</body>
</html>