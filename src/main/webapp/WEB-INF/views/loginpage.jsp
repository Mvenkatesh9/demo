<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>Cliniv - Demo</title>
		<!-- Bootstrap -->
		<script src='<c:url value="/resources/js/modernizr.custom.js"/>'></script>
		<link href='<c:url value="/resources/landing_page/css/bootstrap.min.css"/>' rel="stylesheet">
		<link href='<c:url value="/resources/landing_page/css/jquery.fancybox.css"/>' rel="stylesheet">
		<link href='<c:url value="/resources/landing_page/css/flickity.css"/>' rel="stylesheet" >
		<link href='<c:url value="/resources/landing_page/css/animate.css"/>' rel="stylesheet">
		<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
		<link href='http://fonts.googleapis.com/css?family=Nunito:400,300,700' rel='stylesheet' type='text/css'>
		<link href='<c:url value="/resources/landing_page/css/styles.css"/>' rel="stylesheet">
		<link href='<c:url value="/resources/landing_page/css/queries.css"/>' rel="stylesheet">
		<link rel="shortcut icon" href='<c:url value="/resources/img/fave-icon.png"/>'>
		
	</head>
	<body>
     
      <div class="limiter">
		<div class="container-login100" style="background-image: url('/ClinivDemo/resources/landing_page/images/bg-01.jpg')">
			<div class="wrap-login100">
          <form id="loginForm" role="form" action="../../j_spring_security_check" class="login-form" method="post">
                    	<label id="login-error" class="error" style="margin: 10px 0;color: white;">${error}</label>
          
					<span class="login100-form-logo">
					<img class="zmdi zmdi-landscape" style="width:75%" src='<c:url value="/resources/landing_page/images/login_logo.png"/>' alt="ClinIV">
					</span>

					<div class="wrap-input100 validate-input" data-validate = "Enter username">
						<input id="j_username" type="email" name="j_username" placeholder="Email"  class="input100"  placeholder="Username">
					</div>

					<div class="wrap-input100 validate-input" data-validate="Enter password">
						<input class="input100" id="j_password" type="password" name="j_password" placeholder="Password">
					</div>
					<div class="container-login100-form-btn">
					 <button type="submit" class="login100-form-btn" value="Login">Log In</button>
					
					</div>
				</form>
			</div>
		</div>
	</div>
	
      
    <!-- ===== JS =====-->
    <!-- jQuery-->

    <!-- Semi general-->
    <script type="text/javascript">
    $(document).ready(function(){
    	
    	$("#fgpass").click(function(){
			
    		var uname = $("#j_username").val(); 			
 			if(uname != ''){
 				$.ajax({
 		  		   url: '${pageContext.request.contextPath}/web/auth/sendFPLinkToEmail.do',
 		  		   type: 'GET',
 		  		   data: 'email='+uname,
 		  		   dataType: "text",
 		  		   success: function(data) { 		  			  
 		  			if(data=='true'){
 		  				alert("Mail sent successfully");
 		  			}else{
 		  				$("#login-error").html("You have not entered an valid User Name");
 		  			}
 		  		   } 		  		  
 				});
 				
 				
 			}else{
 				$("#login-error").html("You have entered an invalid User Name or Study was Closed");
 			}
 			
 			 

    	});
    	
    });
      var paceSemiGeneral = { restartOnPushState: false };
      if (typeof paceSpecific != 'undefined'){
      	var paceOptions = $.extend( {}, paceSemiGeneral, paceSpecific );
      	paceOptions = paceOptions;
      }else{
      	paceOptions = paceSemiGeneral;
      }
      
      function sendFPLinkToMail(emailId){
    	  
      }
    </script>
    <script src='<c:url value="/resources/js/plugins/pageprogressbar/pace.min.js"/>'></script>
    <!-- Specific-->
    <script src='<c:url value="/resources/js/plugins/forms/validation/jquery.validate.min.js"/>'></script>
    <script src='<c:url value="/resources/js/plugins/forms/validation/jquery.validate.additional.min.js"/>'></script>
    <script src='<c:url value="/resources/js/calls/page.login.js"/>'></script>
    
    <!-- Forgot Passowrd Hidden form  -->
	<form action="sendFPLink.do" id="fpform" method="post">
		<input type="hidden" id="fpemail" name="fpemail" value="" />
	</form>
	<!-- Forgot Password Hidden form END  -->
	</body>
	</html>
	
	
