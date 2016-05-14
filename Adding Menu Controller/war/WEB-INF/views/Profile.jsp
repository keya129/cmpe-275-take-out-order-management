<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ page session="false"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<title>Group 5 : Profile</title>
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
		function update() {
			var firstName = document.getElementById("firstName").value;
			var lastName = document.getElementById("lastName").value;
			var email = document.getElementById("email").value;
			var address = document.getElementById("password").value;
			var homePageForm = document.forms['homePageForm'];
			document.getElementById("methodHttpSet").value = "POST";
			homePageForm.action = "/profile/"+"?firstName="+firstName+"&lastName="+lastName+"&email="+email;
			homePageForm.submit();
		}
			
			/*function deletePage() {
				var userId = document.getElementById("pageId").value;
				document.getElementById("methodHttpSet").value = "POST";
				var homePageForm = document.forms['homePageForm'];
				homePageForm.action = "http://localhost:8888/spring/profile/"+userId;
				homePageForm.submit();
				//alert();
				//window.location.replace("http://localhost:8888/profile/"+userId);
			}*/
			
			 $(document).ready(function(e){
				$=jQuery.noConflict();
				$('#delbtn').click(function(e){
					var userId = document.getElementById("pageId").value;
					$.ajax({
						type:'DELETE',
						url :'/profile/'+userId,			
						  success:function(viewName){
							 window.location.replace(viewName);
							  
						    }
					    
					});
				});	
				
			}); 
			
		</script>
</head>
<body>

	<h2>User Information</h2>

	<form:form modelAttribute="homePageForm">
		<input type="hidden" id="methodHttpSet" name="_method" value="" />
		<c:if test="${json}">
			<pre>
					{
						"firstName":"<c:out value='${homePageForm.firstName}' />",
						"lastName":"<c:out value='${homePageForm.lastName}' />",
						"email":"<c:out value='${homePageForm.email}' />"
				}
				</pre>
		</c:if>

		<c:if test="${htmlPage}">
			<table id="home_page">
								<tr>
					<th>FirstName</th>

					<td><input id="firstName" name="firstName" type="text"
						value=<c:out value ='${homePageForm.firstName}'/>></td>
				</tr>
				<tr>
					<th>LastName</th>

					<td><input id="lastName" name="lastName" type="text"
						value=<c:out value ='${homePageForm.lastName}'/>></td>
				</tr>
				<tr>
					<th>Email</th>

					<td><input id="email" name="email" type="text"
						value=<c:out value ='${homePageForm.email}'/>></td>
				</tr>
				<tr>
					<th>Password</th>

					<td><input id="password" name="password" type="password"
						value=<c:out value ='${homePageForm.password}'/>></td>
				</tr>
				<tr>
					<th>ReType Password</th>

					<td><input id="password-re" name="password-re" type="password-re"
						value=<c:out value ='${homePageForm.password-re}'/>></td>
				</tr>
				
				
								<tr>
					<td></td>
					<td><input type="button" value="Update"
						onclick="javascript:update();"> </td>
				</tr>

			</table>
		</c:if>
	</form:form>
</body>
</html>
