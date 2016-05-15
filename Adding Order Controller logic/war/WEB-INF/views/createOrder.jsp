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

    <h1> Order Items</h1>
<form action = "/getOrders" method = "get" onSubmit = "return(createList());">
    <table id ="pass" border="1" style="width:50%">
  <tr>
    <th>Menu</th>
    <th>Menu ID </th>
    <th>Picture</th> 
    <th>Preptime</th>
    <th>Price </th>
    <th>Category </th>
    <th> Quantity </th>
    <th>Add Item </th>
  </tr>
  <c:forEach items="${menuitems}" var="menuitem" varStatus="loop">  
  <tr>
  
    <td ><c:out value ='${menuitem.name}'/></td>
    <td ><c:out value ='${menuitem.menuid}'/></td>
    <td ><img id = "menu"  src = <c:out value ='${menuitem.picture}' /> alt = "photo" /></td> 
    <td ><c:out value ='${menuitem.preptime}'/></td>
    <td ><c:out value ='${menuitem.price}'/> </td>
    <td ><c:out value ='${menuitem.category}'/> </td>
    <td ><input type = "text" name = "Quantity" />
    <td ><input type="checkbox" name="add" value="add"> Add</td>
      </tr>
       </c:forEach>
</table>
<br> <br>
<p> Deliver on </p>
<input name ="date_input" dateformat="MM/DD/YYYY" type="date"/>
 <input type="time" name="usr_time" step = "1" />
<br><br>
<input type = "hidden" id = "pat" name = "path" />
<input type = "submit" name = "submit" value = "Confirm Order" />
</form> 
<script type="text/javascript">

function createList(){

    var prodTable = document.getElementById('pass');
    // create 2 arrays, 1 for prod, other for amt
    var orders = new Array(); 
   
    
    for ( var i = 1; row = prodTable.rows[i]; i++ ) {
        row = prodTable.rows[i];
        var inputs = row.getElementsByTagName("input");
        var inputslengte = inputs.length;
        for(var j = 0; j < inputslengte; j++){
            var inputval = inputs[j].value;  
            if(inputs[j].type != 'checkbox')
            var qty = inputval;
            if(inputs[j].type == 'checkbox')
            {
            	
            	 if(inputs[j].checked == true)
                {
            		
            		
                         
                 		
                 	      var price = row.cells[4].innerHTML;
                 	      var amt = price.toString().trim();
                 	      var str = amt.replace(/[\n\r]/g, '');
                 		   alert(str);
                 		 orders.push({
                 			 'menu' : row.cells[0].innerHTML,
                 			 'menuid': row.cells[1].innerHTML,
                 			 'price' : row.cells[4].innerHTML,
                 			 'qty' : qty,
                 			 'preptime' :row.cells[3].innerHTML,
                 			 'category':row.cells[5].innerHTML
                 		 });
                 		
                 		
                 		 
                }
            }
            
        }  
       
     }
    var path = JSON.stringify(orders);
    document.getElementById("pat").value = path;
    }

</script>                     

</body>
</html>