<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Create Orders</title>
</head>
<body>
<% String username = (String)request.getSession().getAttribute("user"); %>
<h1>Welcome, <%=username %></h1>
<form name="category" id="category" method ="get" action="/getMenus" id="FORM_ID" >
   <input type="radio" name="cat" value = "Desert">Desert<br>
  <input type="radio" name="cat"  value = "Drink"> Drink<br>
<input type="radio" name="cat"  value = "MainCourse" >MainCourse<br>
<input type="radio" name="cat"  value = "Appetizer">Appetizer<br>
<input type = "submit" name = "submit" value = "Select Category" />
</form>   


</body>
</html>