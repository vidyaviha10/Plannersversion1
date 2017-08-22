		<a id="event" href="#Event">Event</a> <a id="customer" href="#Customer">Customer</a>
		 <%if("admin".equals((String)request.getSession().getAttribute("user")))
			 {%>
		 <a id="employee"
			href="#Employee">Employee</a><%} %>
			<a id="logout" href="#Logout">Logout</a>
			
