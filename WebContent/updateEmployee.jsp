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
		style="top: 80px; position: absolute; width: 100%; padding-top: 0px;">
		<div style="float: left; width: 450px">
			<span style="float: right"><h2>Update Employee</h2></span>
		</div>
	</div>
<div style="
    top: 130px;
    height: 350px;
    margin-left: 450px;
    width:400px;
    background-color:#D3D3D3;
    border: solid 1px #000000;
    overflow-y: hidden;
    "
		class="leftArea" >
  <form >

  <input type="hidden" name="employeeid" id="employeeid" value="" />
     <span style="margin-right:43px;margin-left:20px;">Name:</span>
    <input type="text" id="employeename" name="employeename" placeholder="Name.."><br/>

   <span style="margin-right:47px;margin-left:20px;">Email:</span>
    <input type="text" id="email"  name="email" placeholder="Email.."><br/>
    
    <span style="margin-right:7px;margin-left:20px;">UserName:</span>
    <input type="text" id="username" name="username" placeholder="UserName.."><br/>
    
     <span style="margin-right:15px;margin-left:20px;">Password:</span>
    <input type="password" id="password" name="password" placeholder="Password"><br/>

  
    <input id="update_employee" style="margin-top: 40px;width:90px;margin-left:50px;" type="submit" value="Update">
    <input style=" width:90px;margin-top:  40px;margin-left:105px;" type="submit" id="cancel" value="Cancel">
   
  </form>
  </div>

  <script src="js/jquery-1.7.1.min.js"></script>
	<script src="js/employee.js"></script>
<script src="js/menu.js"></script>
			
			<script>
			
			
			 var updateId = <%=request.getParameter("updateid")  %>
			  
			 $.ajax({
			     type: "GET",
			     url:"http://localhost:8080/Plannersversion1/rest/employees"+ '/' + updateId,
			     dataType: "json",
			     success: function (data) {renderUpdateEmployeeDetails(data)
			         },
			 		error: function(jqXHR, textStatus, errorThrown){
			 			
			 			console.log(errorThrown);
			   	        console.log("error " + textStatus);
			   	        console.log("incoming Text " + jqXHR.responseText);
			 		}
			         
			   });
			   
			 $('#update_employee').click(function() {
			 	//alert("updateId: "+updateId);
			 	var url = "http://localhost:8080/Plannersversion1/rest/employees" + '/' + updateId;
			 	//alert("url: "+url);
			 	$.ajax({
			 		type: 'PUT',
			 		contentType: 'application/json',
			 		url: url,
			 		dataType: "json",
			 		data: employeeformToJSON(),
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
			
			
			
			
			</script>

</body>
</html>