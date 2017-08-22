<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>


<link rel="stylesheet" href="css/styles.css" />
	<link type="text/css" href="css/jquery.simple-dtpicker.css" rel="stylesheet" />
</head>
<body>

	<div class="navbar">

		<%@include file="menu.jsp"%>

	</div>
	<div
		style="top: 50px; position: absolute; width: 100%; padding-top: 0px;">
		<div style="float: left; width: 200px">
			<span style="float: right"><h2>Update Event</h2></span>
		</div>
	</div>
<div style="top: 90px; height: 550px; margin-left: 250px;width:800px;background-color:#D3D3D3;border: solid 1px #000000;
    overflow-y: hidden; "
		class="leftArea" >
  <form >
  <span style="margin-right:5px;margin-left:20px;">Customer:</span> <select id="customerss" style=" margin-left: 29px;">
 
</select><br/>
    <input type="hidden" name="eventid" id="eventid" value="" />
    <span style="margin-right:4px;margin-left:20px;">Event Name:</span>
    <input type="text" id="eventname" name="eventname" placeholder="Event name.."><br/>

  <span style="margin-right:52px;margin-left:20px;">Venue:</span>
    <input type="text" id="venue"  name="venue" placeholder="Venue.."><br/>
    
    <span style="margin-right:15px;margin-left:20px;">Date&Time:</span>
    <input  style="
    width: 192px;
"  type="text" id="date" name="date" placeholder="Date & Time.."><br/>
    
     <span style="margin-right:7px;margin-left:20px;">Table size:</span> <select id="tablesize" style=" margin-left: 29px;">
  <option value="5">5</option>
  <option value="7">7</option>
  <option value="10">10</option>
</select>
<span style=" margin-left: 30px;">Max People per table:</span>
    <input type="text" id="maxperson"  name="maxperson" placeholder="Max People..">
<br/>

 <span style="margin-left:20px;">Max Table no:</span>
    <input type="text" id="totaltables" name="totaltables" placeholder="Max Table no..">
   
    <br/>
  
    <input id="update_Event" style="margin-top: 20px;width:90px;margin-left:250px;" type="submit" value="Update">
    <input style=" width:90px;margin-top:  20px;margin-left:105px;" type="submit" id="cancel" value="Cancel">
    <br/>
    
<input id="generateSeatingArrangement" name="generateSeatingArrangement" style="margin-top: 20px;width:290px;margin-left:250px;" type="submit" value="Generate seating arrangement">
    <br/>
    <input id="guestReport" style="margin-top: 20px;width:250px;" type="submit" value="Generate Guest wise report">
    <input id="tableReport" style=" width:250px;margin-top: 20px;margin-left:0px;" type="submit" value="Generate Table wise report">
    <input id="nameReport" style=" width:250px;margin-top: 20px;margin-left:0px;" type="submit" value="Generate name cards">
  </form>
  </div>
  <script src="js/jquery-1.7.1.min.js"></script>
    	<script src="js/jquery.simple-dtpicker.js"></script>
	<script src="js/event.js"></script>
	<script src="js/guest.js"></script>
	<script>
$.ajax({
    type: "GET",
    url:"http://localhost:8080/Plannersversion1/rest/customers",
    dataType: "json",
    success: function (data) {
    	
    	var list = data == null ? [] : (data instanceof Array ? data : [data]);
    	$.each(list, function(index, customer) {
    		var div_data="<option value="+customer.id+">"+customer.name+"</option>";
           
            $(div_data).appendTo('#customerss');
    	}); 
        },
		error: function(jqXHR, textStatus, errorThrown){
			
			console.log(errorThrown);
  	        console.log("error " + textStatus);
  	        console.log("incoming Text " + jqXHR.responseText);
		}
        
  });
  
  var updateId = <%=request.getParameter("updateid")  %>
  
$.ajax({
    type: "GET",
    url:"http://localhost:8080/Plannersversion1/rest/events"+ '/' + updateId,
    dataType: "json",
    success: function (data) {renderUpdateEventDetails(data)
        },
		error: function(jqXHR, textStatus, errorThrown){
			
			console.log(errorThrown);
  	        console.log("error " + textStatus);
  	        console.log("incoming Text " + jqXHR.responseText);
		}
        
  });
  
$('#update_Event').click(function() {
	//alert("updateId: "+updateId);
	var url = "http://localhost:8080/Plannersversion1/rest/events" + '/' + updateId;
	//alert("url: "+url);
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: url,
		dataType: "json",
		data: eventformToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Event updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			console.log(errorThrown);
  	        console.log("error " + textStatus);
  	        console.log("incoming Text " + jqXHR.responseText);
		}
	});
  
	return false;
});


var updateId = <%=request.getSession().getAttribute("eventId")  %>

function generateSeating() {
	console.log('generate seating plan');
	//alert(updateId);
	alert($('#eventid').val());
	
	var idd= $('#eventid').val();
	$.ajax({
		type: 'GET',
		url: rootURLEvent+ '/generateSeating/' + idd,
		dataType: "json", // data type of response
		success:  function(data, textStatus, jqXHR){
			alert('Seatinggenerated');

		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addGuest error: ' + textStatus);
			console.log(errorThrown);
  	        console.log("error " + textStatus);
  	        console.log("incoming Text " + jqXHR.responseText);
		}
	});
}

$('#guestReport').click(function() {
	window.open("http://localhost:8080/Plannersversion1/guestWiseReport.jsp");	
	return false;
});

$('#tableReport').click(function() {
	window.open("http://localhost:8080/Plannersversion1/tableWiseReport.jsp");	
	return false;
});

$('#nameReport').click(function() {
	window.open("http://localhost:8080/Plannersversion1/generateNamecards.jsp");	
	return false;
});
</script>
  			<script src="js/menu.js"></script>
  						<script type="text/javascript">
		$(function(){
			$('*[name=date]').appendDtpicker();
			$('*[name=date]').val('');
			
		});
	</script>
</body>
</html>