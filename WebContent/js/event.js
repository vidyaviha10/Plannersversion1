var rootURLEvent = "http://localhost:8080/Plannersversion1/rest/events";

var currentEvent;

findEvents();

function findEvents() {
	console.log('find Events');
	$.ajax({
		type: 'GET',
		url: rootURLEvent,
		dataType: "json", // data type of response
		success: renderEventList,
		error: function(jqXHR, textStatus, errorThrown){
			alert('addGuest error: ' + textStatus);
			console.log(errorThrown);
  	        console.log("error " + textStatus);
  	        console.log("incoming Text " + jqXHR.responseText);
		}
	});
}

function findByEventId(id) {
	console.log('findByEventId: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURLEvent + '/' + id,
		dataType: "json",
		success: function(data){
			
			console.log('findByEventId success: ' + data.name);
			currentEvent = data;
			renderEventListDetails(currentEvent);
		}
	});
}

$('#cancel').click(function() {
	window.open("http://localhost:8080/Plannersversion1/admin_home.jsp","_self");	
	return false;
});




/*$('#addEvent').click(function() {
	
	$('#addGuestlabel').hide();
	$('#updateGuestlabel').hide();
	$('#updateEventlabel').hide();
	$('#addEventlabel').show();

	$('#eventdiv').hide();

	
	$('#guestdiv').hide();
	
	$('#guestadddiv').hide();
	$('#eventadddiv').show();
	return false;
});*/

var currentEventId ;

$('#eventList a').live('click', function() {
	currentEventId = $(this).data('identity');
	
	$('.selectedEvent').each(function() {
	    $(this).removeClass('selectedEvent');
	    $(this).addClass('nothing');
	});
	$("#event_"+currentEventId).attr('class', 'selectedEvent');
});


function renderEventList(data) {
	//alert(data);
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#eventList li').remove();
	$.each(list, function(index, event) {
		$('#eventList').append('<li><a href="#" data-identity="' + event.id + '">'+'<div id="event_'+event.id+'" class="nothing" style="width:783px;height:20px;"><div style="padding-left: 40px;width:160px;float:left">'+event.name+'</div><div style="width:150px;float:left;">'+event.venue+
				'</div><div style="width:210px;float:left;">'+event.date+
				'</div><div style="width:120px;float:left;">'+event.customerName+'</div></div></a></li>');
	});
}

function renderEventListDetails(event) {
	
	$('#updateEventlabel').hide();
	$('#addEventlabel').hide();
	$('#addGuestlabel').hide();
	$('#updateGuestlabel').hide();
	$('#updateEventlabel').show();

	$('#guestdiv').hide();
	$('#eventadddiv').hide();
	$('#guestadddiv').hide();
	$('#eventdiv').show();
	
	
	$('#eventid').val(event.id);
	$('#eventname').val(event.name);
	$('#venue').val(event.venue);
	$('#date').val(event.date);
	
	
}

/*var rootURLCustomer = "http://localhost:8080/Plannersversion1/rest/customers";

function findByCustomerId(id) {
	console.log('findByCustomerId: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURLCustomer + '/' + id,
		dataType: "json",
		success: function(data){
			
			console.log('findByCustomerId success: ' + data.name);
			$('#customerName').val(data.name);
		}
	});
}*/


$('#addEvent').click(function() {
	
	window.open("http://localhost:8080/Plannersversion1/addEvent.jsp","_self");	
	return false;
});

$('#createEvent').click(function() {
	
	
	createEvent();
	return false;
});

function createEvent() {
	console.log('addEvent');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURLEvent,
		dataType: "json",
		data: eventformToJSON(),
		success: function(data, textStatus, jqXHR){
			
			
			$('#eventid').val(data.id);
			alert('event created successfully');
			$("#uploadFileformconstraints").submit();
			setTimeout(
					  function() 
					  {
						  $("#uploadFileform").submit();
					  }, 2000);
			
		},
error: function(jqXHR, textStatus, errorThrown){
			
			console.log(errorThrown);
  	        console.log("error " + textStatus);
  	        console.log("incoming Text " + jqXHR.responseText);
		}
	});
}

function eventformToJSON() {
	var eventid = $('#eventid').val();
	//alert( $('#customerss').val());
	return JSON.stringify({
		"id": eventid == "" ? null : eventid, 
		"name": $('#eventname').val(), 
		"venue": $('#venue').val(),
		"date": $('#date').val(),
		"tablesize": $('#tablesize').val(),
		"totalTables": $('#totaltables').val(),
		"customerid": $('#customerss').val(),
		"maxperson": $('#maxperson').val()
		});
}

$('#deleteEvent').click(function() {
	
	$.ajax({
		type: 'DELETE',
		url: rootURLEvent + '/' +currentEventId,
		success: function(data, textStatus, jqXHR){
			alert('event deleted successfully');
			window.open("http://localhost:8080/Plannersversion1/admin_home.jsp","_self");	
		},
		error: function(jqXHR, textStatus, errorThrown){
			console.log(errorThrown);
  	        console.log("error " + textStatus);
  	        console.log("incoming Text " + jqXHR.responseText);
		}
	});
	
	return false;
});

$('#updateEvent').click(function() {
	var  url = new URL("http://localhost:8080/Plannersversion1/updateEvent.jsp?updateid="+currentEventId);
	window.open(url,"_self");
	
	return false;
});

function renderUpdateEventDetails(event) {

	
	$('#eventid').val(event.id);
	$('#eventname').val(event.name);
	$('#venue').val(event.venue);
	$('#date').val(event.date);
	$('#customerss').val(event.customerid);
	$('#maxperson').val(event.maxperson);
	$('#tablesize').val(event.tablesize);
	$('#totaltables').val(event.totalTables);
}

$('#generateSeatingArrangement').click(function() {
	alert('generating seating Plan');
	generateSeating();
	return false;
});



