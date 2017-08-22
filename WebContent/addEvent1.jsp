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
		style="top: 10px; position: absolute; width: 100%; padding-top: 50px;">
		<div style="float: left; width: 200px">
			<span style="float: right"><h2>Create Event</h2></span>
		</div>
	</div>
<div style="top: 90px; height: 550px; margin-left: 250px;width:800px;background-color:#D3D3D3;border: solid 1px #000000;
    overflow-y: hidden; "
		class="leftArea" >
  <form >
  <span style="margin-right:5px;margin-left:20px;">Customer:</span> <select style=" margin-left: 29px;">
  <option value="volvo">Volvo</option>
  <option value="saab">Saab</option>
  <option value="mercedes">Mercedes</option>
  <option value="audi">Audi</option>
</select><br/>
  <input type="hidden" name="eventid" id="eventid" value="" />
    <span style="margin-right:4px;margin-left:20px;">Event Name:</span>
    <input type="text" id="eventname" name="eventname" placeholder="Guest name.."><br/>

  <span style="margin-right:52px;margin-left:20px;">Venue:</span>
    <input type="text" id="relationship" name="relationship" placeholder="Relationship.."><br/>
    
    <span style="margin-right:15px;margin-left:20px;">Date&Time:</span>
    <input type="text" id="relationship" name="relationship" placeholder="Relationship.."><br/>
    
     <span style="margin-right:7px;margin-left:20px;">Table size:</span> <select style=" margin-left: 29px;">
  <option value="volvo">Volvo</option>
  <option value="saab">Saab</option>
  <option value="mercedes">Mercedes</option>
  <option value="audi">Audi</option>
</select>
<span style=" margin-left: 30px;">Max People per table:</span>
    <input type="text" id="relationship" name="relationship" placeholder="Relationship..">
<br/>

 <span style="margin-left:20px;">Max Table no:</span>
    <input type="text" id="relationship" name="relationship" placeholder="Relationship..">
    <span style=" margin-left:30px;margin-top:20px;margin-right:50px;">Import Guest list: </span><button type="button">Upload </button>
    <br/>
  
    <input style="margin-top: 20px;width:90px;margin-left:250px;" type="submit" value="Add">
    <input style=" width:90px;margin-top: 20px;margin-left:105px;" type="submit" value="Cancel">
    <br/>
    
    <input style="margin-top: 20px;width:290px;margin-left:250px;" type="submit" value="Generate seating arrangement">
    <br/>
    <input style="margin-top: 20px;width:250px;" type="submit" value="Generate Guest wise report">
    <input style=" width:250px;margin-top: 20px;margin-left:0px;" type="submit" value="Generate Table wise report">
    <input style=" width:250px;margin-top: 20px;margin-left:0px;" type="submit" value="Generate name cards">
  </form>
  </div>


	<script src="js/jquery-1.7.1.min.js"></script>
	<script src="js/event.js"></script>
	<script src="js/guest.js"></script>
</body>
</html>