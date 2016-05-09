<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ page session="false"%>
<html>
<body>
	<h1>Add Customer</h1>
	
	<form method="post" action="createMenu" >
		<table>
			<tr>
				<td>
					CategoryType :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                              maxlength="30" name="categoryType" id="categoryType" />
				</td>
			</tr>
			<tr>
				<td>
					Item :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                              maxlength="30" name="name" id="name" />
				</td>
			</tr>
			<tr>
				<td>
					Url :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                              maxlength="30" name="url" id="url" />
				</td>
			</tr>
			<tr>
				<td>
					Price :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                              maxlength="30" name="price" id="price" />
				</td>
			</tr>
			<tr>
				<td>
					Calories :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                              maxlength="30" name="calories" id="calories" />
				</td>
			</tr>
			<tr>
				<td>
					Preparation Time :
				</td>
				<td>
					<input type="text" style="width: 185px;" 
                                            maxlength="30" name="prepTime" id="prepTime" />
				</td>
			</tr>
		</table>
		<input type="submit" class="save" title="Save" value="Save" />
	</form>
	
</body>
</html>