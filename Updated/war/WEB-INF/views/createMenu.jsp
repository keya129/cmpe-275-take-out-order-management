<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%
BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<html>

<body>
	<h1>Add Customer</h1>
	
    <form action="<%= blobstoreService.createUploadUrl("/createMenu") %>" method="post" enctype="multipart/form-data">
        <p> CategoryType :</p>
        <input type="text" style="width: 185px;" 
                                              maxlength="30" name="category"  />
         <p>Item : </p>
         <input type="text" style="width: 185px;" 
                                              maxlength="30" name="name" />
         <p>Price : </p>   
         <input type="text" style="width: 185px;" 
                                              maxlength="30" name="price" />
         <p> Calories :</p>   
         <input type="text" style="width: 185px;" 
                                              maxlength="30" name="calories" />  
         <p>Preparation Time : </p>   
         <input type="text" style="width: 185px;" 
                                            maxlength="30" name="prepTime" /> 
         <p> Image: </p>                         
        <input type="file" name="myFile">
       <input type="submit" value="Submit">
    </form>
</body>
</html>