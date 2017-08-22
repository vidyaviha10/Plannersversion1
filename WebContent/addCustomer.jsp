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
			<span style="float: right"><h2>Add Customer</h2></span>
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

  <input type="hidden" name="customerid" id="customerid" value="" />
    <span style="margin-right:97px;margin-left:20px;">Name:</span>
    <input type="text" id="customername" name="customername" placeholder="Name.."><br/>

  <span style="margin-right:100px;margin-left:20px;">Email:</span>
    <input type="text" id="email"  name="email" placeholder="Email.."><br/>
    
    <input style="margin-left:25px;" type="radio" name="gender" value="male"> Male<br>
  <input style="margin-left:25px;" type="radio" name="gender" value="female"> Female<br>
    
    <span style="margin-right:15px;margin-left:20px;">Contact Number:</span>
    <input type="text" id="number" name="number" placeholder="Contact Number.."><br/>

  
    <input id="createCustomer" style="margin-top: 20px;width:90px;margin-left:50px;" type="submit" value="Add">
    <input style=" width:90px;margin-top:  20px;margin-left:105px;" type="submit" id="cancel" value="Cancel">
   
  </form>
  </div>

  <script src="js/jquery-1.7.1.min.js"></script>
	<script src="js/customer.js"></script>
  			<script src="js/menu.js"></script>

</body>
</html>