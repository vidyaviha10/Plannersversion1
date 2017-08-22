<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		request.getSession().setAttribute("user", null);
	%>
	<form>

		<div id="ed-indexContainer"
			style="width: 645px; height: 300px; margin-top: 30px;">
			<div>
				<img src="images//accountIcon.gif" width="322" height="62"
					title="Are you a User Sign in to your account below."
					alt="Are you a User Sign in to your account below.">
			</div>
			<div class="ed-inputGroup screenname" style="margin-top: 60px;">
				<label for="userName">UserName</label>
				<div class="ed-inputWrapper"
					style="width: 211px; height: 38px; padding-right: 8px;">
					<input type="text" id="userName" name="userName" tabindex="1">
				</div>
			</div>

			<div class="ed-inputGroup password" style="margin-top: 60px;">
				<div style="float: left;">
					<label style="padding: 0 0px 0 0px;" for="password">Password</label>
				</div>
				<div class="ed-inputWrapper"
					style="float: right; width: 200px; height: 38px;">
					<input type="password" id="password" name="password"
						maxlength="104" tabindex="2">
				</div>
				<!-- 			<a href="javascript:document.clickLostPasswordForm.submit()"
					class="forgot"><img src="images/greenArrow.gif" width="11"
					height="11" border="0" alt=""> Forgot password? </a> -->
			</div>

			<div class="ed-clearfix"></div>




			<div class="ed-clearfix"></div>
			<div role="button" aria-label="Sign In" id="btnSignIn"
				class="ed-signIn" tabindex="3">
				<!-- <button id="login" type="submit">Login</button> -->
				<table cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td></td>

							<td><a tabindex="-1" role="button" href="#" name="login"
								id="login">Sign In</a></td>
							<td>
								<div class="ed-icon"></div>
							</td>
						</tr>
					</tbody>
				</table>
				
			</div>

		</div>

	</form>
	<script src="js/jquery-1.7.1.min.js"></script>
	<script src="js/login.js"></script>


	<link href="css/styles-index.css" rel="stylesheet" type="text/css">
</body>