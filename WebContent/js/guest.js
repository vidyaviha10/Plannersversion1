var rootURLGuest = "http://localhost:8080/Plannersversion1/rest/guests";

var currentGuest;

function findById(id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURLGuest + '/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: ' + data.name);
			currentGuest = data;
			renderDetails(currentGuest);
		}
	});
}

function findGuestByEventId(id) {
	console.log('findGuestByEventId: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURLGuest + '/event/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findGuestByEventId success: ' + data.name);
			
			renderGuestList(data)
		}
	});
}

$('#addGuest').click(function() {
	
	
	$('#updateGuestlabel').hide();
	$('#updateEventlabel').hide();
	$('#addEventlabel').hide();
	$('#addGuestlabel').show();
	
	$('#eventdiv').hide();

	$('#guestadddiv').hide();
	$('#guestdiv').hide();
	$('#eventadddiv').hide();
	$('#guestadddiv').show();
	return false;
});

$('#guestList a').live('click', function() {
	findById($(this).data('identity'));
});

function renderGuestList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#guestList li').remove();
	$.each(list, function(index, guest) {
		$('#guestList').append('<li><a href="#" data-identity="' + guest.id + '">'+guest.name+'</a></li>');
	});
}

function renderDetails(guest) {


	$('#updateEventlabel').hide();
	$('#addEventlabel').hide();
	$('#addGuestlabel').hide();
	$('#updateGuestlabel').show();
	
	$('#eventdiv').hide();
	$('#eventadddiv').hide();
	$('#guestadddiv').hide();
	$('#guestdiv').show();
	
	$('#guestId').val(guest.id);
	$('#guestname').val(guest.name);
/*	$('#email').val(guest.email);
	$('#phone').val(guest.phone);
	$('#weight').val(guest.weight);
	$('#description').val(guest.description);*/
	
	
	 $('#not_sit_list').val(['1', '2']);
}