<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Your Receipt</h1>
    <h2>Order Id:${orderid} </h2>
    <h2>User Id :${userid}</h2>
    <h2>Pick up Date :${pickup}</h2> 
    <h2>Fullfilment Date:${fdate}</h2>
    <h2>Ready Date:${readydt}</h2>
    <h2>Order Date:${orderdt}</h2>
    <h2> Order Status:${orderstatus} </h2>
    <h2>Order Item </h2>
 <table id ="pass" border="1" style="width:50%">
  <tr>
    <th>Category</th>
    <th>Menu name</th>
    <th>Quantity</th> 
    <th>Amount</th>
  </tr>
  <c:forEach items="${orderitems}" var="orderitem" varStatus="loop">  
  <tr>
  
    <td ><c:out value ='${orderitem.category}'/></td>
    <td ><c:out value ='${orderitem.mname}'/></td>
    <td ><c:out value ='${orderitem.qty}'/></td>
    <td ><c:out value ='${orderitem.amount}'/> </td>
      </tr>
       </c:forEach>
</table>
<p>${message} </p>
</body>
</html>