<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
function writeTable() {
var array = [1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8];
var count = 0;
var totalCells = 8;
    // cache <tbody> element:
    var tbody = $('#body');
    for (var i = 0; i < array.length / 8; i++) {
        // create an <tr> element, append it to the <tbody> and cache it as a variable:
        var tr = $('<tr/>').appendTo(tbody);
        for (var j = 0; j < totalCells; j++) {
            // append <td> elements to previously created <tr> element:
            tr.append('<td>' + array[count] + '</td>');
            count++;
        }
    }
    // reset the count:
    count = 0;
}



</script>
</head>
<body>
<table id='summaryOfResults' border='1'>
        <tbody id="body">
            <tr>
                <th>#</th>
                <th>n<sub>i</sub></th>
                <th>n<sub>f</sub></th>
                <th>E<sub>i</sub> (J)</th>
                <th>E<sub>f</sub> (J)</th>
                <th>&Delta;E (J)</th>
                <th>&Delta;E (kJ/mol)</th>
                <th>&lambda; (nm)</th>
            </tr>
        </tbody>
    </table>
     <button id='write' onclick='writeTable();'>Write Table</button>
</body>
	<script src="js/jquery-1.7.1.min.js"></script>
	<script>
	
	$(document).ready(function(){
		$('tr', 'table.tbl tbody').click(function(){
		    alert($(this).text());
		});
	});
	</script>
</html>