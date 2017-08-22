var rootURLCustomer = "http://localhost:8080/Plannersversion1/rest/customers";

var currentCustomer;

findCustomers();

function findCustomers() {
	console.log('find Customers');
	$.ajax({
		type: 'GET',
		url: rootURLCustomer,
		dataType: "json", // data type of response
		success: renderCustomerList,
		error: function(jqXHR, textStatus, errorThrown){
			alert(' error: ' + textStatus);
			console.log(errorThrown);
  	        console.log("error " + textStatus);
  	        console.log("incoming Text " + jqXHR.responseText);
		}
	});
}

function findByCustomerId(id) {
	console.log('findByCustomerId: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURLCustomer + '/' + id,
		dataType: "json",
		success: function(data){
			
			console.log('findByCustomerId success: ' + data.name);
			currentCustomer = data;
			renderCustomerListDetails(currentCustomer);
		}
	});
}

$('#cancel').click(function() {
	window.open("http://localhost:8080/Plannersversion1/customerHome.jsp","_self");	
	return false;
});


/*$('#addCustomer').click(function() {
	
	$('#addGuestlabel').hide();
	$('#updateGuestlabel').hide();
	$('#updateCustomerlabel').hide();
	$('#addCustomerlabel').show();

	$('#customerdiv').hide();

	
	$('#guestdiv').hide();
	
	$('#guestadddiv').hide();
	$('#customeradddiv').show();
	return false;
});*/

var currentCustomerId ;

$('#customerList a').live('click', function() {
	currentCustomerId = $(this).data('identity');
	
	$('.selectedCustomer').each(function() {
	    $(this).removeClass('selectedCustomer');
	    $(this).addClass('nothing');
	});
	$("#customer_"+currentCustomerId).attr('class', 'selectedCustomer');
});


function renderCustomerList(data) {
	//alert(data);
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#customerList li').remove();
	$.each(list, function(index, customer) {

		$('#customerList').append('<li><a href="#" data-identity="' + customer.id + '">'+'<div id="customer_'+customer.id+'" class="nothing" style="width:783px;height:20px;"><div style="padding-left: 50px;width:160px;float:left">'+customer.name+'</div>'+
				'</div></a></li>');
	});
}

function renderCustomerListDetails(customer) {
	
	$('#updateCustomerlabel').hide();
	$('#addCustomerlabel').hide();
	$('#addGuestlabel').hide();
	$('#updateGuestlabel').hide();
	$('#updateCustomerlabel').show();

	$('#guestdiv').hide();
	$('#customeradddiv').hide();
	$('#guestadddiv').hide();
	$('#customerdiv').show();
	
	
	$('#customerid').val(customer.id);
	$('#customername').val(customer.name);
	$('#venue').val(customer.venue);
	$('#date').val(customer.date);
	
	
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


$('#addCustomer').click(function() {
	
	window.open("http://localhost:8080/Plannersversion1/addCustomer.jsp","_self");	
	return false;
});

$('#createCustomer').click(function() {
	
	
	createCustomer();
	return false;
});

function createCustomer() {
	console.log('addCustomer');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURLCustomer,
		dataType: "json",
		data: customerformToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('customer created successfully');
			
		},
error: function(jqXHR, textStatus, errorThrown){
			
			console.log(errorThrown);
  	        console.log("error " + textStatus);
  	        console.log("incoming Text " + jqXHR.responseText);
		}
	});
}

function customerformToJSON() {
	var customerid = $('#customerid').val();
	//alert( $('#customerss').val());
	return JSON.stringify({
		"id": customerid == "" ? null : customerid, 
		"name": $('#customername').val(), 
		"email": $('#email').val(),
		"gender":$("input[name='gender']:checked").val(),
		"number": $('#number').val(),
		});
}

$('#deleteCustomer').click(function() {
	
	$.ajax({
		type: 'DELETE',
		url: rootURLCustomer + '/' +currentCustomerId,
		success: function(data, textStatus, jqXHR){
			alert('customer deleted successfully');
			window.open("http://localhost:8080/Plannersversion1/customerHome.jsp","_self");	
		},
		error: function(jqXHR, textStatus, errorThrown){
			console.log(errorThrown);
  	        console.log("error " + textStatus);
  	        console.log("incoming Text " + jqXHR.responseText);
		}
	});
	
	return false;
});

$('#updateCustomer').click(function() {
	var  url = new URL("http://localhost:8080/Plannersversion1/updateCustomer.jsp?updateid="+currentCustomerId);
	window.open(url,"_self");
	
	return false;
});

function renderUpdateCustomerDetails(customer) {

	
	$('#customerid').val(customer.id);
	$('#customername').val(customer.name);
	$('#email').val(customer.email);
	$('#number').val(customer.number);
	$("input[name=gender][value=" + customer.gender + "]").attr('checked', 'checked');

	
}




