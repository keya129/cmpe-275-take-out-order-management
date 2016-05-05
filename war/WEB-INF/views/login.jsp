<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ page session="false"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<title>Login</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.css" />

<link rel="stylesheet" type="text/css" href="../css/style.css" />

<style>
#home_page {
	width: 100%;
	border-collapse: collapse;
}

#home_page td, #home_page th {
	font-size: 1em;
}

#home_page th {
	font-size: 1.1em;
	text-align: left;
}

#home_page tr.alt td {
	
}
</style>
<script type="text/javascript">
				</script>
</head>
<body>

	<h2 class="title">Login</h2>

	<form:form modelAttribute="homePageForm" id="loginform" action="/login">

			<table id="home_page">
								<tr>
					<th>Email</th>

					<td><input id="email" name="email" type="text"
						value=""></td>
				</tr>
				<tr>
					<th>Password</th>

					<td><input id="password" name="password" type="password"
						value=""></td>
				</tr>
								<tr>
					<td></td>
					<td><input type="submit" value="Login"
						> </td>
				</tr>

			</table>
	</form:form>
</body>
</html>
