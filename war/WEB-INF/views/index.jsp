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
<title>Foodie</title>
<link href="../css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery.min.js"></script>
<!-- Custom Theme files -->
<link rel="stylesheet" type="text/css" href="../css/main-style.css" />

<!-- Custom Theme files -->
<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800,300' rel='stylesheet' type='text/css'>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script type="text/javascript" src="../js/move-top.js"></script>
<script type="text/javascript" src="../js/easing.js"></script>
					<script type="text/javascript">
				


					function GetSelectedTextValue(ddlFruits) {
				        var selectedText = ddlFruits.options[ddlFruits.selectedIndex].innerHTML;
				        var selectedValue = ddlFruits.value;
				        //alert("Selected Text: " + selectedText + " Value: " + selectedValue);
				        document.getElementById("categoryType").value=selectedValue;
				    }

					jQuery(document).ready(function($) {
						var orders = new Array(); 

						$('#order').click(function(e){
							alert("I am ordering");
							 $.ajax({
						          type:'GET',
						           url: "/getOrders",
						           type: "GET",
						           dataType: "html",
						           data:{
						        	 path:document.getElementById("pat").value,
						        	 date_input:document.getElementById("odate").value,
						        	 usr_time:document.getElementById("otime").value	 
						           },
						          success:function(data) {
						                if(data) {   // DO SOMETHING     
//alert(data.find('.header-bottom').html());
						                alert(data);
						                $("#receipt").html(data);
						                } 
						                else { // DO SOMETHING 
						                	}
						              }
							 

						    });	
						});
						$(window).load(function(e){
						    $.ajax({
						          type:'GET',
						          url:'/getallOrders',
						          dataType: "html",
						          
						          success:function(data) {
						                if(data) {   // DO SOMETHING     
//alert(data.find('.header-bottom').html());
						                $("#displayorders").html(data);
						                } 
						                else { // DO SOMETHING 
						                	}
						              }

						    }); 
						       });
	
						var ordermap={"menu":"","menuid":"","price":"","qty":"","preptime":"","category":""};
						$(".addcart").click(function(e){
							e.preventDefault();
							if($(this).parent().find('#qty').val()==""){
								$(this).parent().find('#qty').val(1);
							}
							ordermap['menu']=ordermap['menu']+$(this).parent().find('h4').text()+",";
							ordermap['menuid']=ordermap['menuid']+$(this).parent().find('#menuid').text()+",";
							ordermap['price']=ordermap['price']+$(this).parent().find('#price').text()+",";
							ordermap['qty']=ordermap['qty']+$(this).parent().find('#qty').val()+",";
							ordermap['preptime']=ordermap['preptime']+$(this).parent().find('#menupreptime').text()+",";
							ordermap['category']=ordermap['category']+$(this).parent().find('#menucat').text()+",";
							 
							 orders.push({
	                 			 'menu' : $(this).parent().find('h4').text(),
	                 			 'menuid': $(this).parent().find('#menuid').text(),
	                 			 'price' : $(this).parent().find('#price').text(),
	                 			 'qty' : $(this).parent().find('#qty').val(),
	                 			 'preptime' :$(this).parent().find('#menupreptime').text(),
	                 			 'category':$(this).parent().find('#menucat').text()
	                 		 });
							 var path = JSON.stringify(orders);
							 console.log(path);
							 $(this).hide();
							 $(this).parent().find('.removecart').show();
							 $('#mtable').append("<tr><td>"+$(this).parent().find('h4').text()+"</td><td>"+$(this).parent().find('#qty').val()+"</td><td>"+$(this).parent().find('#price').text()+"</td></tr>");
							 document.getElementById("pat").value = path;
							
							
							});
						$(".removecart").click(function(e){
							var index = orders.map(function(e) { return e.menuid; }).indexOf($(this).parent().find('#menuid').text());
							console.log(index);
							orders.splice(index, 1);
							 var path = JSON.stringify(orders);
							 console.log(path);
							 $(this).hide();
							 $(this).parent().find('.addcart').show();
							var cmpstr=$(this).parent().find('h4').text();
							 $('#mtable td').each(function(e){
								 if(cmpstr==$(this).html()){
								alert($(this).html()); 
						        $(this).parent().remove();
						        }
								 });
															
						});
						
						
						
						$(".scroll").click(function(event){		
							event.preventDefault();
							$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
						});
						$("#orderdetails").click(function(event){		
							event.preventDefault();
							$(document).scrollTo('#orderdetails');
						});
						$("#logout").click(function(e){
						//alert();
							 e.preventDefault();
							//var url=window.location.href+"/logout";
							//alert(url);
							$.get("/logout",function(){
							    location.reload(); 
							  
							}); 
							

						});
						$("a#register").click(function(e){
							e.preventDefault();
							var emailp=$(this).parent().find('#email').val();
							//alert("/profile/"+email);
							 $.ajax({
						          type:'POST',
						           url: "/profile/emailp",
						           dataType: "html",
						           data:{
						        	 email:emailp
						        	  },
						          success:function(data) {
						              }
							 

						    });
							$('.hidenow').show();
						});
						$(".deleteMenu").click(function(e){
							e.preventDefault();
							var id=$(this).parent().find('#menuid').text();
							//alert("/deleteMenu/"+id);
						
							$.post("/deleteMenu/"+id);
						    location.reload(); 
						    $(this).css('display','none');
						    $(this).parent().find('.enableMenu').css('display','block');

						    
							});
							
						
						$(".enableMenu").click(function(e){
							e.preventDefault();
							var id=$(this).parent().find('#menuid').text();
							//alert("/deleteMenu/"+id);
						
							$.post("/enableMenu/"+id);
						    location.reload(); 
						    $(this).css('display','none');
						    $(this).parent().find('.deleteMenu').css('display','block');

						});
						
					});
					</script>
<script type="text/javascript" src="../js/jquery.jscrollpane.min.js"></script>
		<script type="text/javascript" id="sourcecode">
			$(function()
			{
				$('.scroll-pane').jScrollPane();
			});
			
		</script>
		<style>
		.portfolio-img.enable{opacity:0.2;}
		</style>
</head>
<body>
<%
   Cookie cookie = null;
   Cookie[] cookies = null;
   String user="";
   // Get an array of Cookies associated with this domain
   cookies = request.getCookies();
   if( cookies != null ){
      //out.println("<h2> Found Cookies Name and Value</h2>");
      for (int i = 0; i < cookies.length; i++){
         cookie = cookies[i];
         if(cookie.getName()=="user"){
      	    user=cookie.getValue();
      	}
  }
   }
%>
<% String message = (String)request.getSession().getAttribute("message"); %>
	<!--- Header Starts Here --->
	<div class="header" id="home">
		<div class="container">
		<div class="blackstrip">
<p>
<c:out value ='${Errormsg}'/>&nbsp;&nbsp; 
<c:out value ='${message}'/>&nbsp;&nbsp;
<c:out value ='${Pickuptime}'/>&nbsp;&nbsp;
<c:out value ='${TotalAmount}'/>
</p>
</div>
			<div class="logo">
				 <a href="index.html"><img src="images/logo.png" alt=""></a>
			</div>
			<div class="menu">
			<% String username = (String)request.getSession().getAttribute("user"); %>
			
			
			
			
				<ul class="menu-top">
				
				<% if (username != null) { %>
					<li><a class="play-icon popup-with-zoom-anim" href="" class="logout" id="logout">Log Out</a></li>
				
					<% }else{ %>
					<li><a class="play-icon popup-with-zoom-anim" href="#small-dialog">Log In</a></li>
					
					<% } %>
					<li><a class="play-icon popup-with-zoom-anim" href="#small-dialog1">Sign up</a></li>
<c:if test="${sessionScope.user eq 'admin'}">
					<li><a class="play-icon popup-with-zoom-anim" href="#small-dialog2">Add Menu</a></li>
					
</c:if>
					<li><div class="main">
								<section>
									<button id="showRight" class="navig"></button>
								</section>
						</div></li>
				</ul>	
				<!---pop-up-box---->
					  <script type="text/javascript" src="../js/modernizr.custom.min.js"></script>    
					<link href="../css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
					<script src="../js/jquery.magnific-popup.js" type="text/javascript"></script>
					<!---//pop-up-box---->
				<div id="small-dialog" class="mfp-hide">
						<div class="login">
	<form:form  modelAttribute="Errormsg" id="loginform" action="/login">
							<h3>Log In</h3>
							<h4>Already a Member</h4>
							<input type="text" required value="Email" name="email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email';}" />
							<input type="password" required value="Password" name="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}"/>
							<input type="submit" value="Login" />
	</form:form>
						</div>
					</div>
					<div id="small-dialog1" class="mfp-hide">
						<div class="signup">
							<form:form   id="profileform" action="/profile">
						
							<h3>Sign Up</h3>
							<h4>Enter Your Details Here</h4>
							<input type="text" value="First Name" name="firstName" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'First Name';}" />
							<input type="text" value="Second Name" name="lastName" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Second Name';}" />
							<input type="text" class="email" id="email" name="email" value="Enter Email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Enter Email';}"  />
							<input type="password" value="Password" name="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}"/>
							<a href="#" id="register">Register</a>
							<div class="hidenow">
							<input type="number" placeholder="Verification Code" value="" name="code" />
							<input type="submit"  value="SignUp"/>
							</div>
							</form:form>
						</div>
					</div>
					<div id="small-dialog2" class="mfp-hide">
						<div class="signup">
							 <form action=<%= blobstoreService.createUploadUrl("/createMenu") %> method="post"  enctype="multipart/form-data">
						
							<h3>Add new Menu</h3>
							<h4>Enter Your Details Here</h4>
							Category Type:
							<select name="category1" onchange="GetSelectedTextValue(this)" id="cat">
  <option value="Appetizer" selected="selected">Appetizer</option>
  <option value="Maincourse">Maincourse</option>
  <option value="Drink">Drink</option>
  <option value="Desert">Desert</option>
   
  
</select>
							<input type="hidden" name="category" id="categoryType" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'CategoryType';}" />
							<input type="text" required placeholder="Item" value="Item" name="name" id="name" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'name';}" />
							<input type="file" required name="url" id="url" value="URL">
							<input type="number"  required value="Price" placeholder="Price" name="price" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'price';}"/>
							<input type="number" required value="Calories" placeholder="Calories" name="calories" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'calories';}"/>
							<input type="number" required value="PrepTime" placeholder="Preparation Time" name="preptime" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'prepTime';}"/>
							<input type="submit"  value="Add"/>
							</form>
						</div>
					</div>	
				 <script>
						$(document).ready(function() {
						$('.popup-with-zoom-anim').magnificPopup({
							type: 'inline',
							fixedContentPos: false,
							fixedBgPos: true,
							overflowY: 'auto',
							closeBtnInside: true,
							preloader: false,
							midClick: true,
							removalDelay: 300,
							mainClass: 'my-mfp-zoom-in'
						});
																						
						});
				</script>															
				<!--- Navigation from Right To Left --->
				<link rel="stylesheet" type="text/css" href="../css/component.css" />
					<script src="../js/modernizr.custom.js"></script>
					<script type="text/javascript">
			
					  var _gaq = _gaq || [];
					  _gaq.push(['_setAccount', 'UA-7243260-2']);
					  _gaq.push(['_trackPageview']);
			
					  (function() {
					    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
					    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
					    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
					  })();
			
					</script>
					<div class="cbp-spmenu-push">
						<nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-right" id="cbp-spmenu-s2">
							<h3>Foodie</h3>
							<a href="#home" class="scroll">Home</a>
							
							<a href="#portfolio" class="scroll">Menu</a>
							<% if (username == "admin") { %>
							<a href="#displayorders" class="scroll">Orders</a>
							<%}else{ 	
						%>		<a href="#displayorders" class="scroll">Orders</a>					<a href="#cart" class="scroll">Cart</a>
						
							<%} %>
							
						</nav>
				</div>
				<script src="../js/classie.js"></script>
					<script>
						var menuRight = document.getElementById( 'cbp-spmenu-s2' ),
							showRight = document.getElementById( 'showRight' ),
							body = document.body;
			
						showRight.onclick = function() {
							classie.toggle( this, 'active' );
							classie.toggle( menuRight, 'cbp-spmenu-open' );
							disableOther( 'showRight' );
						};
			
						function disableOther( button ) {
							if( button !== 'showRight' ) {
								classie.toggle( showRight, 'disabled' );
							}
						}
					</script>
				<!--- Navigation from Right To Left --->
					<script type="text/javascript" src="../js/easing.js"></script>
								
				</div> 
				<div class="clearfix"></div>
				<div class="header-bottom">
					<p>Find your favorite</p>
					<h1>RECIPES</h1>
					
				</div>
		</div>
	</div>
	<!--- Header Ends Here --->
	<% if (username == "admin") { %>
					<div class="portfolio" id="portfolio">
		<div class="container">
			<div class="portfolio-top">
				<h3>BROWSE BY CUISINES-Admin</h3>
				<span class="linet-red"></span>
			</div>
		</div >
		<div class="portfolio-start">
						<h1>Appetizer</h1>
		
		<c:forEach items="${menuitems}" var="menuitem" varStatus="loop">  
		
		<c:if test="${menuitem.category eq 'Appetizer'}">
		<c:if test="${menuitem.mstatus eq 'false'}">
		<div class="portfolio-img enable">
		</c:if>
		<c:if test="${menuitem.mstatus eq 'true'}">
		<div class="portfolio-img disable">
		</c:if>
					<a href="#small-dialog-it${loop.index}" class="play-icon popup-with-zoom-anim"><img src=<c:out value ='${menuitem.picture}'/> alt="" width="374" height="263"/></a>
					<div id="small-dialog-it${loop.index}" class="small-dialog-it mfp-hide">
						<div class="portfolio-items">
						<img src=<c:out value ='${menuitem.picture}'/> alt="photo" height="200" width="200">
						
	<c:if test="${menuitem.mstatus eq 'false'}"><c:out value ='${menuitem.mstatus}'/>
						<a href="" class="enableMenu" >Enable Menu</a>
								
						</c:if>
						<c:if test="${menuitem.mstatus eq 'true'}"><c:out value ='${menuitem.mstatus}'/>
						<a href="" class="deleteMenu" >Disable Menu</a>
								
						</c:if>	
						
						
						<h4><c:out value ='${menuitem.name}'/></h4>
			
						
						<c:out value ='${menuitem.name}'/><div id="menuid"><c:out value ='${menuitem.menuid}'/></div>
						<div id="menucat"><c:out value ='${menuitem.category}'/></div>
						<div id="menupreptime"><c:out value ='${menuitem.preptime}'/></div>
						<div id="price"><c:out value ='${menuitem.price}'/></div></div>
						
						</div>
					</div>
		</c:if>
		   
			</c:forEach>
						<div class="clearfix"></div>
			
								<h1>Maincourse</h1>
			
			<c:forEach items="${menuitems}" var="menuitem" varStatus="loop">  
			
		<c:if test="${menuitem.category eq 'Maincourse'}">
		<c:if test="${menuitem.mstatus eq 'false'}">
		<div class="portfolio-img enable">
		</c:if>
		<c:if test="${menuitem.mstatus eq 'true'}">
		<div class="portfolio-img disable">
		</c:if>
					<a href="#small-dialog-it${loop.index}" class="play-icon popup-with-zoom-anim"><img src=<c:out value ='${menuitem.picture}'/> alt="" width="374" height="263"/></a>
					<div id="small-dialog-it${loop.index}" class="small-dialog-it mfp-hide">
						<div class="portfolio-items">
						<img src=<c:out value ='${menuitem.picture}'/> alt="photo" height="200" width="200">
						
						
	<c:if test="${menuitem.mstatus eq 'false'}"><c:out value ='${menuitem.mstatus}'/>
						<a href="" class="enableMenu" >Enable Menu</a>
								
						</c:if>
						<c:if test="${menuitem.mstatus eq 'true'}"><c:out value ='${menuitem.mstatus}'/>
						<a href="" class="deleteMenu" >Disable Menu</a>
								
						</c:if>						
						<h4><c:out value ='${menuitem.name}'/></h4>
			
						
						<c:out value ='${menuitem.name}'/><div id="menuid"><c:out value ='${menuitem.menuid}'/></div>
						<div id="menucat"><c:out value ='${menuitem.category}'/></div>
						<div id="menupreptime"><c:out value ='${menuitem.preptime}'/></div>
						<div id="price"><c:out value ='${menuitem.price}'/></div></div>
						
						</div>
					</div>
		</c:if>
		   
			</c:forEach>
						<div class="clearfix"></div>
			
					<h1>Drink</h1>
			
			<c:forEach items="${menuitems}" var="menuitem" varStatus="loop">  
		<c:if test="${menuitem.category eq 'Drink'}">
		<c:if test="${menuitem.mstatus eq 'false'}">
		<div class="portfolio-img enable">
		</c:if>
		<c:if test="${menuitem.mstatus eq 'true'}">
		<div class="portfolio-img disable">
		</c:if>
					<a href="#small-dialog-it${loop.index}" class="play-icon popup-with-zoom-anim"><img src=<c:out value ='${menuitem.picture}'/> alt="" width="374" height="263"/></a>
					<div id="small-dialog-it${loop.index}" class="small-dialog-it mfp-hide">
						<div class="portfolio-items">
						<img src=<c:out value ='${menuitem.picture}'/> alt="photo" height="200" width="200">
						
						
					<c:if test="${menuitem.mstatus eq 'false'}"><c:out value ='${menuitem.mstatus}'/>
						<a href="" class="enableMenu" >Enable Menu</a>
								
						</c:if>
						<c:if test="${menuitem.mstatus eq 'true'}"><c:out value ='${menuitem.mstatus}'/>
						<a href="" class="deleteMenu" >Disable Menu</a>
								
						</c:if>	
						
						<h4><c:out value ='${menuitem.name}'/></h4>
			
						
						<c:out value ='${menuitem.name}'/><div id="menuid"><c:out value ='${menuitem.menuid}'/></div>
						<div id="menucat"><c:out value ='${menuitem.category}'/></div>
						<div id="menupreptime"><c:out value ='${menuitem.preptime}'/></div>
						<div id="price"><c:out value ='${menuitem.price}'/></div></div>
						
						</div>
					</div>
		</c:if>
		   
			</c:forEach>
						<div class="clearfix"></div>
			
					<h1>Desert</h1>
			
			<c:forEach items="${menuitems}" var="menuitem" varStatus="loop">  
		<c:if test="${menuitem.category eq 'Desert'}">
		<c:if test="${menuitem.mstatus eq 'false'}">
		<div class="portfolio-img enable">
		</c:if>
		<c:if test="${menuitem.mstatus eq 'true'}">
		<div class="portfolio-img disable">
		</c:if>
					<a href="#small-dialog-it${loop.index}" class="play-icon popup-with-zoom-anim"><img src=<c:out value ='${menuitem.picture}'/> alt="" width="374" height="263"/></a>
					<div id="small-dialog-it${loop.index}" class="small-dialog-it mfp-hide">
						<div class="portfolio-items">
						<img src=<c:out value ='${menuitem.picture}'/> alt="photo" height="200" width="200">
						
						
						<c:if test="${menuitem.mstatus eq 'false'}"><c:out value ='${menuitem.mstatus}'/>
						<a href="" class="enableMenu" >Enable Menu</a>
								
						</c:if>
						<c:if test="${menuitem.mstatus eq 'true'}"><c:out value ='${menuitem.mstatus}'/>
						<a href="" class="deleteMenu" >Disable Menu</a>
								
						</c:if>	
						
						<h4><c:out value ='${menuitem.name}'/></h4>
			
						
						<c:out value ='${menuitem.name}'/><div id="menuid"><c:out value ='${menuitem.menuid}'/></div>
						<div id="menucat"><c:out value ='${menuitem.category}'/></div>
						<div id="menupreptime"><c:out value ='${menuitem.preptime}'/></div>
						<div id="price"><c:out value ='${menuitem.price}'/></div></div>
						
						</div>
					</div>
		</c:if>
		   
			</c:forEach>
			<div class="clearfix"></div>
				</div>
			
		</div>
					
<% }else{ %>			
	<!--- Portfolio Starts Here --->
	<div class="portfolio" id="portfolio">
		<div class="container">
			<div class="portfolio-top">
				<h3>BROWSE BY CUISINES</h3>
				<span class="linet-red"></span>
			</div>
		</div >
		<div class="portfolio-start">
						<h1>Appetizer</h1>
		
		<c:forEach items="${menuitems}" var="menuitem" varStatus="loop">  
		
		<c:if test="${menuitem.category eq 'Appetizer'}">
		<c:if test="${menuitem.mstatus eq 'true'}">
		<div class="portfolio-img">
					<a href="#small-dialog-it${loop.index}" class="play-icon popup-with-zoom-anim"><img src=<c:out value ='${menuitem.picture}'/> alt="" width="374" height="263"/></a>
					<div id="small-dialog-it${loop.index}" class="small-dialog-it mfp-hide">
						<div class="portfolio-items">
						<img src=<c:out value ='${menuitem.picture}'/> alt="photo" height="200" width="200">
						<a href="#" class="addcart">Add to Cart</a>
						<a href="#" class="removecart" style="display:none">Remove from Cart</a>
						
						<h4><c:out value ='${menuitem.name}'/></h4>
			
						<input type="number" name="qty" id="qty" placeholder="Quantity">
						<c:out value ='${menuitem.name}'/><div id="menuid"><c:out value ='${menuitem.menuid}'/></div>
						<div id="menucat"><c:out value ='${menuitem.category}'/></div>
						<div id="menupreptime"><c:out value ='${menuitem.preptime}'/></div>
						<div id="price"><c:out value ='${menuitem.price}'/></div></div>
						
						</div>
					</div>
		</c:if>
		</c:if>
		   
			</c:forEach>
						<div class="clearfix"></div>
			
								<h1>Maincourse</h1>
			
			<c:forEach items="${menuitems}" var="menuitem" varStatus="loop">  
			
		<c:if test="${menuitem.category eq 'Maincourse'}">
		<c:if test="${menuitem.mstatus eq 'true'}">
		<div class="portfolio-img">
					<a href="#small-dialog-it${loop.index}" class="play-icon popup-with-zoom-anim"><img src=<c:out value ='${menuitem.picture}'/> alt="" width="374" height="263"/></a>
					<div id="small-dialog-it${loop.index}" class="small-dialog-it mfp-hide">
						<div class="portfolio-items">
						<img src=<c:out value ='${menuitem.picture}'/> alt="photo" height="200" width="200">
						
					<a href="#" class="addcart">Add to Cart</a>
						<a href="#" class="removecart" style="display:none">Remove from Cart</a>
						
						<h4><c:out value ='${menuitem.name}'/></h4>
			
						<input type="number" name="qty" id="qty" placeholder="Quantity" value="1" max="100">
						<c:out value ='${menuitem.name}'/><div id="menuid"><c:out value ='${menuitem.menuid}'/></div>
						<div id="menucat"><c:out value ='${menuitem.category}'/></div>
						<div id="menupreptime"><c:out value ='${menuitem.preptime}'/></div>
						<div id="price"><c:out value ='${menuitem.price}'/></div></div>
						
						</div>
					</div>
		</c:if></c:if>
		   
			</c:forEach>
						<div class="clearfix"></div>
			
					<h1>Drink</h1>
			
			<c:forEach items="${menuitems}" var="menuitem" varStatus="loop">  
		<c:if test="${menuitem.category eq 'Drink'}">
		<c:if test="${menuitem.mstatus eq 'true'}">
		<div class="portfolio-img">
					<a href="#small-dialog-it${loop.index}" class="play-icon popup-with-zoom-anim"><img src=<c:out value ='${menuitem.picture}'/> alt="" width="374" height="263"/></a>
					<div id="small-dialog-it${loop.index}" class="small-dialog-it mfp-hide">
						<div class="portfolio-items">
						<img src=<c:out value ='${menuitem.picture}'/> alt="photo" height="200" width="200">
						
						
						<a href="#" class="addcart">Add to Cart</a>
						<a href="#" class="removecart" style="display:none">Remove from Cart</a>
						
						<h4><c:out value ='${menuitem.name}'/></h4>
			
						<input type="number" name="qty" id="qty" placeholder="Quantity">
						<c:out value ='${menuitem.name}'/><div id="menuid"><c:out value ='${menuitem.menuid}'/></div>
						<div id="menucat"><c:out value ='${menuitem.category}'/></div>
						<div id="menupreptime"><c:out value ='${menuitem.preptime}'/></div>
						<div id="price"><c:out value ='${menuitem.price}'/></div></div>
						
						</div>
					</div>
		</c:if>
		  </c:if> 
			</c:forEach>
						<div class="clearfix"></div>
			
					<h1>Desert</h1>
			
			<c:forEach items="${menuitems}" var="menuitem" varStatus="loop">  
		<c:if test="${menuitem.category eq 'Desert'}">
			<c:if test="${menuitem.mstatus eq 'true'}">
		<div class="portfolio-img">
					<a href="#small-dialog-it${loop.index}" class="play-icon popup-with-zoom-anim"><img src=<c:out value ='${menuitem.picture}'/> alt="" width="374" height="263"/></a>
					<div id="small-dialog-it${loop.index}" class="small-dialog-it mfp-hide">
						<div class="portfolio-items">
						<img src=<c:out value ='${menuitem.picture}'/> alt="photo" height="200" width="200">
						
					<a href="#" class="addcart">Add to Cart</a>
						<a href="#" class="removecart" style="display:none">Remove from Cart</a>
						
						<h4><c:out value ='${menuitem.name}'/></h4>
			
						<input type="number" name="qty" id="qty" placeholder="Quantity">
						
						<c:out value ='${menuitem.name}'/><div id="menuid"><c:out value ='${menuitem.menuid}'/></div>
						<div id="menucat"><c:out value ='${menuitem.category}'/></div>
						<div id="menupreptime"><c:out value ='${menuitem.preptime}'/></div>
						<div id="price"><c:out value ='${menuitem.price}'/></div></div>
						
						</div>
					</div>
		</c:if>
		   </c:if>
			</c:forEach>
			<div class="clearfix"></div>
				</div>
			
		</div><% } %>
	</div>
	
	</div>
	<!--- Portfolio Ends Here --->
	<div class="aboutus" id="aboutus">
		<div class="container">
	
		<div id="displayorders">
		<h1>Check all Orders here:</h1>
		  
 
		
		</div>
		
		
	
		<% if (username != "admin") { %>
		<div class="orderdetais" id="orderdetais">
		
		<form action = "/getOrders" method = "get" id="form-mine">
	
		<br> <br>
<p> Deliver on </p>

 <input name ="date_input" dateformat="YYYY-MM-DD" type="date" id="odate" required  min="" max""/>
 <input type="time" name="usr_time" step = "1" id="otime" min="06:00" max="21:00" required />
<br><br>
<input type = "hidden" id = "pat" name = "path" />
		<input type="button" value="order" id="order" />
		
		</form>
		

		
		
		<div class="show">
		<h1 id="cart">Your Cart Details</h1>
		<table id="mtable" style="background:#f5f5f5;color:#0000;">
		<thead>
		<tr>
		<th width="30%" align="left">Item</th>
		<th width="10%" align="left">Quantity</th>
		<th width="30%" align="left">Price</th>
		</tr>
		<thead>
		<tbody>
		
		</tbody>
		</table>
		<div id="receipt">
		
		</div>
		</div>
		</div>
		<%} %>
		</div></div>
	<!-- Footer Starts Here ---->
	<div class="footer">
		<div class="container">
			<div class="footer-top">
				 <a href="index.html"><img src="images/logo-bot.png" class="img-responsive" alt=""/></a>
			</div>
			<p class="footer-head">2016 Foodie Website</p>
			<div class="clearfix"></div>
		</div>
	</div>
	<!-- Footer Ends Here ---->
</body>
<script type="text/javascript">
				
					/*Date limiting function*/
var today = new Date();
var dd = today.getDate();
var mm = today.getMonth()+1; //January is 0!
var yyyy = today.getFullYear();
if(dd<10){
dd='0'+dd;
} 
if(mm<10){
mm='0'+mm;
} 

today = yyyy+'-'+mm+'-'+dd;

var next = new Date();
next.setDate(next.getDate()+30);

var d = next.getDate();
var m = next.getMonth()+1; //January is 0!
var yy = next.getFullYear();
if(d<10){
d='0'+d;
} 
if(m<10){
m='0'+m;
} 


next = yy+'-'+m+'-'+d;


document.getElementById("odate").setAttribute("min", today);
document.getElementById("odate").setAttribute("max", next);
</script>
</html>
	
