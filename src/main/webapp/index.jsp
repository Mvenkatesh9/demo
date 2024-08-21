<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		
		
<script>
	document.onkeydown = function(e) {
		if (e.KeyboardEvent == 123) {
			return false;
		} else if (e.ctrlKey && e.shiftKey
				&& e.KeyboardEvent == 'I'.charCodeAt(0)) {
			return false;
		} else if (e.ctrlKey && e.shiftKey
				&& e.KeyboardEvent == 'J'.charCodeAt(0)) {
			return false;
		} else if (e.ctrlKey && e.KeyboardEvent && 'U'.charCodeAt(0)) {
			return false;
		}
	}
</script>
	</head>
	<body>
		<header>
			<section class="container-login100" style="background-image: url('/ClinivDemo/resources/landing_page/images/bg-01.jpg')">
				<div class="container">
						<div class="col-md-12" style="text-align:center; margin:40px 0">
							<h1 class="animated fadeInDown" style="color:#0593d8">Cliniv Demo<span style="color:#0fc0d4"> Application </span></h1>
						</div>
						<div class="col-md-12" style="text-align:center">
							<a href="./web/auth/login.do" class="animated fadeInUp login-btn-landing">Log In</a>
							</div>
						<%-- <div class="col-md-12" style="text-align:center; margin:40px 0">
							<div class="col-md-12" style="text-align:center;">
							<p class="animated fadeInUp"> To get the Mobile App please click below</p>
								<a href="./web/auth/login.do" class="animated fadeInUp"> 
									<img style="width:180px;" src="<c:url value="/resources/landing_page/images/play_store.png"/>" />
								</a>
								<a href="./web/auth/login.do" class="animated fadeInUp">
									<img style="width:180px;" src="<c:url value="/resources/landing_page/images/app_store.png"/>" />
								</a>
							</div>
						</div> --%>
						</div>
			</section>
		</header>

</html>
