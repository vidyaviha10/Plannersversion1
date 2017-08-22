<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<link rel="stylesheet" href="css/styles.css" />
</head>
<body>


	<div
		style="top: 10px; position: absolute; width: 100%; padding-top: 10px;">
		<div style="float: left; width: 440px">
			<span style="float: right">Guest wise Report</span>
		</div>
	</div>
	<div id = "ss" style="top: 40px; height: 400px; margin-left: 450px;width:430px;overflow-y: hidden;overflow-x: hidden;"
		class="leftArea">
		<span style="background-color:grey;padding-right: 97px;">
		<span style="margin-left: 50px;">Guest Name</span>
		<span style="margin-left: 40px;">Table no</span>
<span style="margin-left: 40px;">Seated with</span>
		</span><ul id="guestwisereportList"></ul>

	</div>

	<div   style="top: 520px; width: 920px; position: absolute;">
		<div style="float: left;">
			<input id="print"
				style="margin-left: 90px;margin-top: 30px;margin-left: 460px;width: 90px;margin-right: 20px;"
				type="submit" value="Print ">
		</div>

		
		
		<div style="float: right;">
			<input id="cancel1" style="margin-right: 25px;margin-top: 30px;width: 90px;margin-left: 0px;"
				type="submit" value="Cancel" onclick="self.close()">
		</div>


	</div>
	
		<div id="printdiv" style="display:none;">
	
	<table id="printTable" border="1" cellpadding="3">
	<tbody><tr>
		<th>Guest Name</th>
		<th>Table no</th>	
		<th>Seated with</th>		
	</tr>

</tbody></table>
	
	</div>


	<script src="js/jquery-1.7.1.min.js"></script>

<script>



$.ajax({
    type: "GET",
    url:"http://localhost:8080/Plannersversion1/rest/events/generateguestwisereport",
    dataType: "json",
    success: function (data) {
    	var list = data == null ? [] : (data instanceof Array ? data : [data]);

    	$('#guestwisereportList li').remove();
    	$.each(list, function(index, report) {
    		$('#guestwisereportList').append('<li>'+'<div class="nothing" style="width:783px;height:20px;"><div style="padding-left: 40px;width:160px;float:left">'+report.name+'</div><div style="width:150px;float:left;">'+report.tableno+
    				'</div><div style="width:210px;float:left;">'+report.seatedWith+
    				'</div></div></li>');
    		
    		$('#printTable').append('<tr><td>'+report.name+'</td><td>'+report.tableno+'</td><td>'+report.seatedWith+
			'</td></tr>');
    	});
        },
		error: function(jqXHR, textStatus, errorThrown){
			
			console.log(errorThrown);
  	        console.log("error " + textStatus);
  	        console.log("incoming Text " + jqXHR.responseText);
		}
        
  });
  
function printData()
{
   var divToPrint=document.getElementById("printTable");
   newWin= window.open("");
   newWin.document.write(divToPrint.outerHTML);
   $('#printdiv').hide();
   newWin.print();
   newWin.close();

}

$('#print').click(function() {
	$('#printdiv').show();
	printData();
	return false;
});

</script>
</body>
</html>