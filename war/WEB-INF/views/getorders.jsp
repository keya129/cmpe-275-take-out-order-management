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
<%@ page session="true"%>
    
<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
<table id ="pass" border="1" style="width:50%">
  <tr>
    <th>Orderid</th>
    <th>user ID </th>
    <th>Pickup date</th> 
     <th>Fulfillment date</th> 
    <th>Ready date</th>
    <th>Order Date</th>
    <th>Total Amount</th>
    <th> Order status </th>
        <th> Order Items </th>
    
   
  </tr>
  <c:forEach items="${orders}" var="orders" varStatus="loop">  
  <tr>
  
    <td ><c:out value ='${orders.orderid}'/></td>
    <td ><c:out value ='${orders.userid}'/></td>
   
    <td ><c:out value ='${orders.pickupdt}'/></td>
     <td ><c:out value ='${orders.fulfildt}'/></td>
    <td ><c:out value ='${orders.readydt}'/> </td>
    <td ><c:out value ='${orders.orderdt}'/> </td>
   
    <td ><c:out value ='${orders.totalamount}'/> </td>
  
  <td ><c:out value ='${orders.ostatus}'/> </td>
  <td >
  <c:forEach items="${orders.orderItems}" var="order" varStatus="loop">
   <c:out value ='${order.mname}'/><br/>  
  </c:forEach>
  </td>
      </tr>
       </c:forEach>
</table>
</body>
</html>