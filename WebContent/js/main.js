//var rootURL = "http://localhost:8080/Planners/rest/login";




function renderHomePageDetails() {
	
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json", // data type of response,
		success: function(data, textStatus, jqXHR){
			//alert('renderHomePage '+data+ ' status '+textStatus);
			
			$('#name').val(data.userName);
			
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addGuest error: ' + textStatus+' error '+errorThrown+' jqXHR '+jqXHR);
		}
	});
	
	
	

	
}

// The root URL for the RESTful services




// Retrieve guest list when application starts 


// Nothing to delete in initial application state
$('#btnDelete').hide();

// Register listeners
$('#btnSearch').click(function() {
	search($('#searchKey').val());
	return false;
});

// Trigger search when pressing 'Return' on search key input field
$('#searchKey').keypress(function(e){
	if(e.which == 13) {
		search($('#searchKey').val());
		e.preventDefault();
		return false;
    }
});







$('#btnSave').click(function() {
	if ($('#guestId').val() == '')
		addGuest();
	else
		updateGuest();
	return false;
});

$('#btnDelete').click(function() {
	deleteGuest();
	return false;
});



$('#customerList a').live('click', function() {
	findEventsByCustomerId($(this).data('identity'));
});











// Replace broken images with generic guest bottle
$("img").error(function(){
  //$(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
}

function newGuest() {
	$('#btnDelete').hide();
	currentGuest = {};
	renderDetails(currentGuest); // Display empty form
}





function findCustomers() {
	console.log('findCustomers');
	$.ajax({
		type: 'GET',
		url: rootURLGuest,
		dataType: "json", // data type of response
		success: renderCustomerList
	});
}





function findGuests() {
	console.log('findguests');
	$.ajax({
		type: 'GET',
		url: rootURLGuest,
		dataType: "json", // data type of response
		success: renderList
	});
}

function findByName(searchKey) {
	console.log('findByName: ' + searchKey);
	$.ajax({
		type: 'GET',
		url: rootURLGuest + '/search/' + searchKey,
		dataType: "json",
		success: renderList 
	});
}



function addGuest() {
	console.log('addGuest');
	$.ajax({
		type: "POST",
		dataType: "json",
		contentType: "application/json",
		url: rootURLGuest+'/post',
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Guest created successfully');
			$('#btnDelete').show();
			$('#guestId').val(data.id);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addGuest error: ' + textStatus);
			console.log(errorThrown);
  	        console.log("error " + textStatus);
  	        console.log("incoming Text " + jqXHR.responseText);
		}
	});
}

function updateGuest() {
	console.log('updateGuest');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json; charset=utf-8',
		url: rootURLGuest + '/' + $('#guestId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Guest updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateGuest error: ' + textStatus+' errorThrown '+errorThrown);
		}
	});
}

function deleteGuest() {
	console.log('deleteGuest');
	$.ajax({
		type: 'DELETE',
		url: rootURLGuest + '/' + $('#guestId').val(),
		success: function(data, textStatus, jqXHR){
			alert('Guest deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteGuest error');
		}
	});
}

function renderCustomerList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#customerList li').remove();
	$.each(list, function(index, customer) {
		$('#customerList').append('<li><a href="#" data-identity="' + customer.id + '">'+customer.name+'</a></li>');
	});
}












// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var guestId = $('#guestId').val();
	var data =  JSON.stringify({
		"id": guestId == "" ? null : guestId, 
		"name": $('#name').val(), 
		"email": $('#email').val(),
		"phone": $('#phone').val(),
		"weight": $('#weight').val(),
		"description": $('#description').val()
		});
	
	//alert(data);
	return data;
}
