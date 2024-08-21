<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--Left Sidebar Content-->
<aside id="sb-left" class="l-sidebar l-sidebar-1 t-sidebar-1">
	<!--Switcher-->
	<div class="l-side-box">
		<a href="#" data-ason-type="sidebar" data-ason-to-sm="sidebar"
			data-ason-target="#sb-left"
			class="sidebar-switcher switcher t-switcher-side ason-widget"><i
			class="fa fa-bars"></i></a>
	</div>
	<div class="l-side-box">
		<!--Logo-->
		<div class="widget-logo logo-in-side">
			<h1>
				<a href="javascript:void(0)"><span
					class="logo-default visible-default-inline-block"><img
						src='<c:url value="/resources/img/logo.png"/>' alt="ClinIV"></span><span
					class="logo-medium visible-compact-inline-block"><img
						src='<c:url value="/resources/img/logo_medium.png"/>' alt="ClinIV"
						title="ClinIV"></span> <spanl
						class="logo-small visible-collapsed-inline-block">
			</h1>
		</div>
	</div>
	<!--Main Menu-->
	<nav class="sidebar sidebar-offcanvas" id="sidebar">

		<div class="l-side-box">
			<p
				style="text-align: center; padding: 10px 0; background: #362160; color: white;">Logged
				in as Sponsor Admin User</p>
		</div>
		<div class="l-side-box">


			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#">Home</a>
						</h4>
					</div>
					<div id="home" class="panel-collapse collapse in">
						<ul data-ason-type="menu" class="ason-widget nav">
							<li><a href="../sponsor/dashboard"><i
									class="icon fa fa-dashboard"></i><span class="title">Home</span>
							</a></li>
						</ul>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#">Admin
								Management</a>
						</h4>
					</div>
					<div id="users" class="panel-collapse collapse in">
						<ul data-ason-type="menu" class="ason-widget nav">
							<li><a href="../sponsor/sponsorTeam"><i
									class="icon fa fa-user"></i><span class="title">Sponsor
										Team</span> </a></li>
						</ul>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="#">Study
								Management</a>
						</h4>
					</div>
					<div id="studys" class="panel-collapse collapse in">
						<ul data-ason-type="menu" class="ason-widget nav">
							<li><a href="../sponsor/studyInfo"><i
									class="icon fa fa-outdent"></i><span class="title">Study
										Information</span> </a></li>
							<li><a href="../sponsor/studyDocuments"><i
									class="icon fa fa-outdent"></i><span class="title">Study
										Documents</span> </a></li>
							<li><a href="../sponsor/products"><i
									class="icon fa fa-medkit"></i><span class="title">Study
										Drugs &amp; Methodology</span> </a></li>
							<li><a href="../sponsor/crf"><i
									class="icon fa fa-outdent"></i><span class="title">Study
										CRF</span> </a></li>

							<li><a href="../sponsor/trailSites"><i
									class="icon fa fa-user-md"></i><span class="title">Trial
										Sites</span> </a></li>
							<li><a href="../sponsor/subjects"><i
									class="icon fa fa-user"></i><span class="title">Subjects</span>
							</a></li>
						</ul>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="">Adverse
								Events</a>
						</h4>
					</div>
					<div id="reports" class="panel-collapse collapse in">
						<ul data-ason-type="menu" class="ason-widget nav">

							<li><a href="../sponsor/adverseEventsAll"><i
									class="icon fa fa-exclamation-triangle"></i><span class="title">Adverse
										Events</span> </a></li>
						</ul>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="">Reports</a>
						</h4>
					</div>
					<div id="reports" class="panel-collapse collapse in">
						<ul data-ason-type="menu" class="ason-widget nav">
							<li><a href="../sponsor/labReports"><i
									class="icon fa fa-medkit"></i><span class="title">Lab
										Reports</span> </a></li>
							<li><a href="../sponsor/studyReports"><i
									class="icon fa fa-medkit"></i><span class="title">Recruitment
										Status</span> </a></li>
							<!-- <li><a href="../sponsor/visitReports"><i
									class="icon fa fa-medkit"></i><span class="title">Monitoring Reports/Study Status</span>
							</a></li> -->
							<li><a href="../sponsor/logReports"><i
									class="icon fa fa-user"></i><span class="title">Log
										Reports</span> </a></li>

						</ul>
					</div>
				</div>

				<!-- <div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" href="">General</a>
						</h4>
					</div>
					<div id="reports" class="panel-collapse collapse in">
						<ul data-ason-type="menu" class="ason-widget nav">
							<li><a href="../sponsor/userManuals"><i
									class="icon fa fa-outdent"></i><span class="title">User
										Manuals</span> </a></li>
							<li><a href="../sponsor/studyReports.do"><i
									class="icon fa fa-medkit"></i><span class="title">Training / Demo</span>
							</a></li>
							<li><a href="../sponsor/studyFaqs"><i
									class="icon fa fa-outdent"></i><span class="title">FAQS</span>
							</a></li>

						</ul>
					</div>
				</div> -->
			</div>


		</div>
	</nav>
	<div class="l-side-box">
		<p style="text-align: center; padding: 20px;">** Application is in
			IST time zone</p>
	</div>
</aside>