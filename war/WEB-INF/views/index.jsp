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
						$(".scroll").click(function(event){		
							event.preventDefault();
							$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
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
							var email=$(this).parent().find('#email').val();
							//alert("/profile/"+email);
							$.post("/profile/"+email);
							$('.hidenow').show();
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
</head>
<body>
<%
   Cookie cookie = null;
   Cookie[] cookies = null;
   // Get an array of Cookies associated with this domain
   cookies = request.getCookies();
   if( cookies != null ){
      out.println("<h2> Found Cookies Name and Value</h2>");
      for (int i = 0; i < cookies.length; i++){
         cookie = cookies[i];
         out.print("Name : " + cookie.getName( ) + ",  ");
         out.print("Value: " + cookie.getValue( )+" <br/>");
      }
  }else{
      out.println("<h2>No cookies founds</h2>");
  }
%>

	<!--- Header Starts Here --->
	<div class="header" id="home">
		<div class="container">
		<div class="blackstrip">
<p><c:out value ='${Errormsg}'/> <c:out value ='${message}'/></p>
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
<% if (username == "admin") { %>
					<li><a class="play-icon popup-with-zoom-anim" href="#small-dialog2">Add Category</a></li>
					
<% } %>
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
							<input type="text" value="Email" name="email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Email';}" />
							<input type="password" value="Password" name="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}"/>
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
							<input type="email" class="email" id="email" name="email" value="Enter Email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Enter Email';}"  />
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
							<form:form   modelAttribute="categoryform" id="categoryform" action="" method="post" enctype="multipart/form-data">
						
							<h3>Add new category</h3>
							<h4>Enter Your Details Here</h4>
							Category Type:
							<select name="category1" onchange="GetSelectedTextValue(this)" id="cat">
  <option value="Appetizer" selected="selected">Appetizer</option>
  <option value="Maincourse">Maincourse</option>
  <option value="Drink">Drink</option>
  <option value="Desert">Desert</option>
    <option value="Desert">Desert</option>
  
</select>
							<input type="hidden" value="CategoryType" name="category" id="categoryType" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'CategoryType';}" />
							<input type="text" required placeholder="Item" value="Item" name="name" id="name" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'name';}" />
							<input type="file" required name="menuImage" id="url" value="URL">
							<input type="number"  required value="Price" placeholder="Price" name="price" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'price';}"/>
							<input type="number" required value="Calories" placeholder="Calories" name="calories" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'calories';}"/>
							<input type="number" required value="PrepTime" placeholder="Preparation Time" name="preptime" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'prepTime';}"/>
							<input type="submit"  value="Add"/>
							</form:form>
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
							<h3>Menu</h3>
							<a href="#home" class="scroll">Home</a>
							<a href="#about" class="scroll">About</a>
							<a href="#portfolio" class="scroll">Portfolio</a>
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
					<a href="#">Get Started</a>
					<p class="reward">OR SEND US YOUR OWN RECIPES AND <u>GET REWARDED!</u></p>
				</div>
		</div>
	</div>
	<!--- Header Ends Here --->
	<!-- Aboutus Starts Here --->
	<div class="aboutus" id="about">
		<div class="container">
			<div class="row aboutus-row">
				<div class="col-md-4 aboutus-row-column">
					<i class="icon1"></i>
					<h3>GET INSPIRED</h3>
					<span class="line-red"></span>
					<p>Lorem ipsum dolor sit amet, consectetur adipisc Pellentesque vel enim a elit viverra elementuma.Aliquam erat volutpat. </p>
				</div>
				<div class="col-md-4 aboutus-row-column">
					<i class="icon2"></i>
					<h3>GET REWARDED</h3>
					<span class="line-red"></span>
					<p>Lorem ipsum dolor sit amet, consectetur adipisc Pellentesque vel enim a elit viverra elementuma.Aliquam erat volutpat. </p>
				</div>
				<div class="col-md-4 aboutus-row-column">
					<i class="icon3"></i>
					<h3>GET SOCIAL</h3>
					<span class="line-red"></span>
					<p>Lorem ipsum dolor sit amet, consectetur adipisc Pellentesque vel enim a elit viverra elementuma.Aliquam erat volutpat. </p>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- Aboutus Ends Here --->
	<!-- Interduce Starts Here  ---->
	<div class="interduce">
		<div class="container">
			<div class="row interduce-row">
				<div class="col-md-6 inter-row-column2">
					<img src="images/iphone-1.png" alt=""/>
				</div>
				<div class="col-md-6 inter-row-column">
					<h4><span>INTRODUCING</span> THE FUDI APP</h4>
					<p>Morbi eget posuere dolor. Pellentesque cursus aliquet aliquet. Aeneanet felis sit amet diam mollis ullamcorper. Nullam consequat sem a ante vest ibulum tristique. Suspendisse tristique lacus ac mattis porta. </p>
					<p>Vivamus ligula quam, vehicula non lacinia sed, faucibus sit amet libero. In libero dui, eleifend eu nisi id, egestas fringilla odio. In varius quam a massa hendrerit ullamcorper a eu justo. Suspendisse porta mattis convallis.Aenean tempus ligula ac odio rhoncus, quis aliquam dolor accumsan. </p>
					<p>Suspendisse aliquet felis consectetur libero congue, sed pulvinar diam malesuada. Duis vehicula a nibh id hendrerit. Donec sit amet ultricesante, a mattis massa. </p>
					<ul class="inter-duce">
						<li><i class="icon4"></i></li>
						<li><i class="icon5"></i></li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- Interduce Ends Here  ---->
	<!--- Slider Starts Here --->
	<div class="slider">
		<div class="container">
			<div class="slider-content">
				<script src="../js/responsiveslides.min.js"></script>
			 <script>
			    // You can also use "$(window).load(function() {"
			    $(function () {
			      // Slideshow 4
			      $("#slider4").responsiveSlides({
			        auto: true,
			        pager: true,
			        nav: true,
			        speed: 500,
			        namespace: "callbacks",
			        before: function () {
			          $('.events').append("<li>before event fired.</li>");
			        },
			        after: function () {
			          $('.events').append("<li>after event fired.</li>");
			        }
			      });
			
			    });
			  </script>
			<!----//End-slider-script---->
			<!-- Slideshow 4 -->
			    <div  id="top" class="callbacks_container">
			      <ul class="rslides" id="slider4">
			        <li>
			          <div class="slider-top">
			          	<img src="images/1.jpg" alt=""/>
			          	<p>"I am so happy because I found this recipe, and it just made my life easier.<br>Thanks  so much for sharing!"</p>
			          	<p class="below">- Michael Dawson, San Francisco, CA -</p>
			          </div>
			        </li>
			        <li>
			          <div class="slider-top">
			          <img src="images/2.jpg" alt=""/>
			          	<p>"I am so happy because I found this recipe, and it just made my life easier.<br>Thanks  so much for sharing!"</p>
			          	<p class="below">- Aaron Dawson, San Francisco, CA -</p>
			          </div>
			        </li>
			        <li>
			          <div class="slider-top">
			          	<img src="images/3.jpg" alt=""/>
			          	<p>"I am so happy because I found this recipe, and it just made my life easier.<br>Thanks  so much for sharing!"</p>
			          	<p class="below">- Johnson Dawson, San Francisco, CA -</p>
			          </div>
			        </li>
			      </ul>
			    </div>
			    <div class="clearfix"> </div>
	  			<!--- banner Slider Ends Here ---> 
			</div>
			</div>
			</div>
		</div>
	</div>
	<!--- Slider Ends Here --->
	<!--- Portfolio Starts Here --->
	<div class="portfolio" id="portfolio">
		<div class="container">
			<div class="portfolio-top">
				<h3>BROWSE BY CUISINES</h3>
				<span class="linet-red"></span>
			</div>
		</div >
		<div class="portfolio-start">
				<div class="portfolio-img">
					<a href="#small-dialog-it" class="play-icon popup-with-zoom-anim"><img src="images/s1.jpg" alt=""/></a>
					<div id="small-dialog-it" class="mfp-hide">
						<div class="portfolio-items">
						<img src="images/s1.jpg" alt="">
						<h4>Italian Food</h4>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. </p>
						</div>
					</div>
				</div>
				<div class="portfolio-img">
					<a href="#small-dialog-in" class="play-icon popup-with-zoom-anim"><img src="images/s2.jpg" alt=""/></a>
					<div id="small-dialog-in" class="mfp-hide">
						<div class="portfolio-items">
						<img src="images/s2.jpg" alt="">
						<h4>Indian Food</h4>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. </p>
						</div>
					</div>
				</div>
				<div class="portfolio-img">
					<a href="#small-dialog-fr" class="play-icon popup-with-zoom-anim"><img src="images/s3.jpg" alt=""/></a>
					<div id="small-dialog-fr" class="mfp-hide">
						<div class="portfolio-items">
						<img src="images/s3.jpg" alt="">
						<h4>French Food</h4>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. </p>
						</div>
					</div>
				</div>
				<div class="portfolio-img">
					<a href="#small-dialog-sh" class="play-icon popup-with-zoom-anim"><img src="images/s4.jpg" alt=""/></a>
					<div id="small-dialog-sh" class="mfp-hide">
						<div class="portfolio-items">
						<img src="images/s4.jpg" alt="">
						<h4>Steak House Food</h4>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. </p>
						</div>
					</div>
				</div>
				<div class="portfolio-img">
					<a href="#small-dialog-sf" class="play-icon popup-with-zoom-anim"><img src="images/s5.jpg" alt=""/></a>
					<div id="small-dialog-sf" class="mfp-hide">
						<div class="portfolio-items">
						<img src="images/s5.jpg" alt="">
						<h4>Sea Food</h4>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. </p>
						</div>
					</div>
				</div>
				<div class="clearfix">
				<div class="portfolio-img">
					<a href="#small-dialog-su" class="play-icon popup-with-zoom-anim"><img src="images/s6.jpg" alt=""/></a>
					<div id="small-dialog-su" class="mfp-hide">
						<div class="portfolio-items">
						<img src="images/s6.jpg" alt="">
						<h4>Sushi Food</h4>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. </p>
						</div>
					</div>
				</div>
				<div class="portfolio-img">
					<a href="#small-dialog-me" class="play-icon popup-with-zoom-anim"><img src="images/s7.jpg" alt=""/></a>
					<div id="small-dialog-me" class="mfp-hide">
						<div class="portfolio-items">
						<img src="images/s7.jpg" alt="">
						<h4>Mexican Food</h4>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. </p>
						</div>
					</div>
				</div>
				<div class="portfolio-img">
					<a href="#small-dialog-ch" class="play-icon popup-with-zoom-anim"><img src="images/s8.jpg" alt=""/></a>
					<div id="small-dialog-ch" class="mfp-hide">
						<div class="portfolio-items">
						<img src="images/s8.jpg" alt="">
						<h4>Chinese Food</h4>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. </p>
						</div>
					</div>
				</div>
				<div class="portfolio-img">
					<a href="#small-dialog-pi" class="play-icon popup-with-zoom-anim"><img src="images/s9.jpg" alt=""/></a>
					<div id="small-dialog-pi" class="mfp-hide">
						<div class="portfolio-items">
						<img src="images/s9.jpg" alt="">
						<h4>PIZZA</h4>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. </p>
						</div>
					</div>
				</div>
				<div class="portfolio-img">
					<a href="#small-dialog-am" class="play-icon popup-with-zoom-anim"><img src="images/s10.jpg" alt=""/></a>
					<div id="small-dialog-am" class="mfp-hide">
						<div class="portfolio-items">
						<img src="images/s10.jpg" alt="">
						<h4>American Food</h4>
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. </p>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
		</div>
	</div>
		<div class="container">
			<ul class="numbers">
				<li>
					<div class="number-top">
						<h4>23,567</h4>
						<p>Recipes Available</p>
					</div>
				</li>
				<li>
					<div class="number-top">
						<h4>431,729</h4>
						<p>Active Users</p>
					</div>
				</li>
				<li>
					<div class="number-top">
						<h4>892,173</h4>
						<p>Positive Reviews</p>
					</div>
				</li>
				<li>
					<div class="number-top">
						<h4>56,581</h4>
						<p>Photos & Videos</p>
					</div>
				</li>
				<li>
					<div class="number-top">
						<h4>3,182</h4>
						<p>Spices and Herbs</p>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<!--- Portfolio Ends Here --->
	<!-- Footer Starts Here ---->
	<div class="footer">
		<div class="container">
			<div class="footer-top">
				 <a href="index.html"><img src="images/logo-bot.png" class="img-responsive" alt=""/></a>
			</div>
			<p class="footer-head">2016 Website</p>
			<div class="clearfix"></div>
		</div>
	</div>
	<!-- Footer Ends Here ---->
</body>
</html>
	
