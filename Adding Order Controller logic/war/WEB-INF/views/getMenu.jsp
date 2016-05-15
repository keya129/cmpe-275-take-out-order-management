<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%
BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		
		<c:forEach items="${menuitems}" var="menuitem" varStatus="loop">     

					
				
						<img src=<c:out value ='${menuitem.picture}'/>	 alt="photo" height="200" width="200">
						
						<h4><c:out value ='${menuitem.name}'/></h4>
	
						<input type="number" name="qty" id="qty">
						<input type="datetime" name="date" id="date" value = "pickupTime">
						<c:out value ='${menuitem.name}'/><div id="menuid"><c:out value ='${menuitem.menuid}'/></div>
						<div id="menucat"><c:out value ='${menuitem.category}'/></div>
						<div id="menupreptime"><c:out value ='${menuitem.preptime}'/></div>
						<div id="price"><c:out value ='${menuitem.price}'/></div></div>
						
						</div>
					</div>
				
   
</c:forEach>
			<div class="clearfix"></div>
				</div>
</body>
</html>