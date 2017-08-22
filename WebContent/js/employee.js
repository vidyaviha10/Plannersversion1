var rootURLEmployee = "http://localhost:8080/Plannersversion1/rest/employees";

var currentEmployee;

findEmployee();

function findEmployee() {
	console.log('find Employee');
	$.ajax({
		type: 'GET',
		url: rootURLEmployee,
		dataType: "json", // data type of response
		success: renderEmployeeList,
		error: function(jqXHR, textStatus, errorThrown){
			alert(' error: ' + textStatus);
			console.log(errorThrown);
  	        console.log("error " + textStatus);
  	        console.log("incoming Text " + jqXHR.responseText);
		}
	});
}

function findByEmployeeId(id) {
	console.log('findByEmployeeId: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURLEmployee + '/' + id,
		dataType: "json",
		success: function(data){
			
			console.log('findByEmployeeId success: ' + data.name);
			currentEmployee = data;
			renderEmployeeListDetails(currentEmployee);
		}
	});
}

$('#cancel').click(function() {
	window.open("http://localhost:8080/Plannersversion1/employeeHome.jsp","_self");	
	return false;
});


/*$('#addEmployee').click(function() {
	
	$('#addGuestlabel').hide();
	$('#updateGuestlabel').hide();
	$('#updateEmployeelabel').hide();
	$('#addEmployeelabel').show();

	$('#employeediv').hide();

	
	$('#guestdiv').hide();
	
	$('#guestadddiv').hide();
	$('#employeeadddiv').show();
	return false;
});*/

var currentEmployeeId ;

$('#employeeList a').live('click', function() {
	currentEmployeeId = $(this).data('identity');
	
	$('.selectedEmployee').each(function() {
	    $(this).removeClass('selectedEmployee');
	    $(this).addClass('nothing');
	});
	$("#employee_"+currentEmployeeId).attr('class', 'selectedEmployee');
});


function renderEmployeeList(data) {
	//alert(data);
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#employeeList li').remove();
	$.each(list, function(index, employee) {

		$('#employeeList').append('<li><a href="#" data-identity="' + employee.id + '">'+'<div id="employee_'+employee.id+'" class="nothing" style="width:783px;height:20px;"><div style="padding-left: 50px;width:160px;float:left">'+employee.name+'</div><div style="padding-left: 20px;min-width:150px;float:left;">'+employee.id+
				'</div></div></a></li>');
	});
}

function renderEmployeeListDetails(employee) {
	
	$('#updateEmployeelabel').hide();
	$('#addEmployeelabel').hide();
	$('#addGuestlabel').hide();
	$('#updateGuestlabel').hide();
	$('#updateEmployeelabel').show();

	$('#guestdiv').hide();
	$('#employeeadddiv').hide();
	$('#guestadddiv').hide();
	$('#employeediv').show();
	
	
	$('#employeeid').val(employee.id);
	$('#employeename').val(employee.name);
	$('#venue').val(employee.venue);
	$('#date').val(employee.date);
	
	
}

/*var rootURLEmployee = "http://localhost:8080/Plannersversion1/rest/employee";

function findByEmployeeId(id) {
	console.log('findByEmployeeId: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURLEmployee + '/' + id,
		dataType: "json",
		success: function(data){
			
			console.log('findByEmployeeId success: ' + data.name);
			$('#employeeName').val(data.name);
		}
	});
}*/


$('#addEmployee').click(function() {
	
	window.open("http://localhost:8080/Plannersversion1/addEmployee.jsp","_self");	
	return false;
});

$('#createEmployee').click(function() {
	
	
	createEmployee();
	return false;
});

function createEmployee() {
	console.log('addEmployee');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURLEmployee,
		dataType: "json",
		data: employeeformToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('employee created successfully');
			
		},
error: function(jqXHR, textStatus, errorThrown){
			
			console.log(errorThrown);
  	        console.log("error " + textStatus);
  	        console.log("incoming Text " + jqXHR.responseText);
		}
	});
}

function employeeformToJSON() {
	var employeeid = $('#employeeid').val();
	//alert( $('#employees').val());
	return JSON.stringify({
		"id": employeeid == "" ? null : employeeid, 
		"name": $('#employeename').val(), 
		"email": $('#email').val(),
		"username":$("#username").val(),
		"password": $('#password').val(),
		});
}

$('#deleteEmployee').click(function() {
	
	$.ajax({
		type: 'DELETE',
		url: rootURLEmployee + '/' +currentEmployeeId,
		success: function(data, textStatus, jqXHR){
			alert('employee deleted successfully');
			window.open("http://localhost:8080/Plannersversion1/employeeHome.jsp","_self");	
		},
		error: function(jqXHR, textStatus, errorThrown){
			console.log(errorThrown);
  	        console.log("error " + textStatus);
  	        console.log("incoming Text " + jqXHR.responseText);
		}
	});
	
	return false;
});

$('#updateEmployee').click(function() {
	var  url = new URL("http://localhost:8080/Plannersversion1/updateEmployee.jsp?updateid="+currentEmployeeId);
	window.open(url,"_self");
	
	return false;
});

function renderUpdateEmployeeDetails(employee) {

	
	$('#employeeid').val(employee.id);
	$('#employeename').val(employee.name);
	$('#email').val(employee.email);
	$('#username').val(employee.username);
	$('#password').val(employee.password);

	
}




