<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Cliniv - Demo</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <!-- ===== FAVICON =====-->
    <link rel="shortcut icon" href="favicon.ico">
    <!-- ===== CSS =====-->
    <!-- General-->


    <link href='<c:url value="/resources/css/bootstrap.min.css"/>' rel="stylesheet">
    <link href='<c:url value="/resources/css/jquery.fancybox.css"/>' rel="stylesheet">
    <link href='<c:url value="/resources/css/flickity.css"/>' rel="stylesheet" >
    <link href='<c:url value="/resources/css/animate.css"/>' rel="stylesheet">
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Nunito:400,300,700' rel='stylesheet' type='text/css'>
    <link href='<c:url value="/resources/css/styles.css"/>' rel="stylesheet">
    <link href='<c:url value="/resources/css/queries.css"/>' rel="stylesheet">
    <link rel="shortcut icon" href='<c:url value="/resources/img/fave-icon.png"/>'>

    <script src='<c:url value="/resources/js/modernizr.custom.js"/>'></script>
    <script src='<c:url value="/resources/js/basic/jquery.min.js"/>'></script>
    <script src='<c:url value="/resources/js/basic/jquery-migrate.min.js"/>'></script>
    <script src='<c:url value="/resources/js/basic/jquery-ui.js"/>'></script>

    <!-- General-->

    <script src='<c:url value="/resources/js/basic/modernizr.min.js"/>'></script>
    <script src='<c:url value="/resources/js/basic/bootstrap.min.js"/>'></script>
    <script src='<c:url value="/resources/js/shared/jquery.asonWidget.js"/>'></script>
    <script src='<c:url value="/resources/js/plugins/plugins.js"/>'></script>


    <!-- General-->
    <!--[if lt IE 9]>
    <script src="js/basic/respond.min.js"></script>
    <script src="js/basic/html5shiv.min.js"></script>
    <![endif]-->

    <style type="text/css">

    .login-wrapper {
    margin-top: 10%;
    position: relative;
    width: 100%;
    z-index: 9999;
}
form{
    margin: 50px auto 0;
    width: 400px;
}
.login-container {
    margin: 20px auto;
    width: 500px;
}
.login-logo {
    margin: 15px 0 20px;
    text-align: center;
}
.form-group {
    margin-bottom: 15px;
}
.login-form input {
    border: 1px solid #fff;
}
.login-form input {
    border-radius: 2px;
    height: auto;
    padding: 15px 10px;
    width: 94%;
}
.login-form .btn {
    padding: 12px;
}
.btn-dark {
    background: #14c39c;
    border: medium none;
    border-radius: 20px;
    color: #fff;
    width: 130px;
}
.login-options {
    line-height: 1;
    margin: 30px 0 0;
}
.login-options a {
    color: #fff;
    text-decoration: none;
}
.login-options a:hover{
  color:#73d0da;
  }
.align-right, .fr {
    float: right;
}

.notificationmsg {
    position: fixed;
    top: 10%;
    right: 30%;
    padding: 4px 131px 9px 116px;
    font-family: Arial;
    background: #FFFEA1;
    border: 1px solid #FC0;
}
    </style>

  </head>
  <body class="login-bg">

	<tiles:insertAttribute name="body" />
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src='<c:url value="/resources/js/min/toucheffects-min.js"/>'></script>
    <script src='<c:url value="/resources/js/flickity.pkgd.min.js"/>'></script>
    <script src='<c:url value="/resources/js/jquery.fancybox.pack.js"/>'></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src='<c:url value="/resources/js/retina.js"/>'></script>
    <script src='<c:url value="/resources/js/waypoints.min.js"/>'></script>
    <script src='<c:url value="/resources/js/bootstrap.min.js"/>'></script>
    <script src='<c:url value="/resources/js/min/scripts-min.js"/>'></script>

  </body>
</html>

