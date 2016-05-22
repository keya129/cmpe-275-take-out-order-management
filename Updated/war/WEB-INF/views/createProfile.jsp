<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ page session="false"%>
<html>
<head>
<title>Create HomePage</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

<link rel="stylesheet" type="text/css" href="css/style.css" />

<style>
#home_page {
	
}

#home_page td, #home_page th {
	padding: 3px 7px 2px 7px;
}

#home_page th {
	font-size: 1.1em;
	text-align: left;
	padding-top: 5px;
	padding-bottom: 4px;
}

#home_page tr.alt td {
	
}
</style>


</head>
<body>
	<h1>USER HOMEPAGE APPLICATION</h1>
	<h3>Fill out the following form to create a new homepage for user</h3>
	<form:form modelAttribute="homePageForm" action="/profile" method="POST">
		<table id="home_page">
			
			<tr>
				<th>First Name</th>
				<td><input id="firstName" name="firstName" type="text" value="">
				</td>
			</tr>
			<tr>
				<th>Last Name</th>
				<td><input id="lastName" name="lastName" type="text" value="">
				</td>

			</tr>
			<tr>

				<th>E-mail</th>
				<td><input id="email" name="email" type="text" value="">
				</td>

			</tr>
			<tr>
				<th>Password</th>
				<td><input id="password" name="password" type="text" value="">
				</td>
			</tr>
						<tr>
				<th>Re-Password</th>
				<td><input id="re-password" name="re-password" type="text" value="">
				</td>
			</tr>
			

			<tr>
				<th></th>
				<td><input type="submit" value="Create"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>
