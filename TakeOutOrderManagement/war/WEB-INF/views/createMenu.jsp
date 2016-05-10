<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%
BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<%@ page session="false"%>
<html>
<body>
	<h1>Add Customer</h1>
	
	<form action="<%= blobstoreService.createUploadUrl("/menu/createMenu") %>" method="post" enctype="multipart/form-data">
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
			
			<tr>
				<td>
					Image :
				</td>
				<td>
                    <input type="text" name="filename"/>
                    <input type="file" name="menuImage"/>
                    <input type="submit" value="Submit"/>
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>