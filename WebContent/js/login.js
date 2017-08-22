var rootURL = "http://localhost:8080/Plannersversion1/rest/login";

$('#login').click(function() {
	console.log('login start');
	login();
	return false;
});

function login() {
	console.log('login');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: loginformToJSON(),
		success: function(data, textStatus, jqXHR){
			if(data == false)
			{
			alert("invalid credentials");
			}
		else
			window.open("http://localhost:8080/Plannersversion1/admin_home.jsp","_self");
			
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert(' error: ' + textStatus+' error '+errorThrown+' jqXHR '+jqXHR);
		}
	});
}

function loginformToJSON() {
	return JSON.stringify({
		"userName": $('#userName').val(), 
		"password": $('#password').val()
		});
}