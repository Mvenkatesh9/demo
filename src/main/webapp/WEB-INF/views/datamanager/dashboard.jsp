
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
table, th, td {
	border: 1px solid #dadada9c;
	border-collapse: collapse;
	background: #95deff17;
}

th, td, b {
	padding: 10px;
	text-align: left;
	font-size: 16px;
	font-family: poppins;
	width: 5%;
	line-height: 24px;
	margin: -10px;
}
</style>
<div
	style="background-image: url('/ClinivDemo/resources/img/dashboard-bg.jpg'); height: 100%; background-size: cover;">
	<!-- Page Summary Widget-->
	<div class="widget-page-summary">
		<div class="l-col-lg-8">
			<h2 class="page-title">
				Welcome <span> ${FirstName}<c:if
						test="${not empty MiddleName}"> ${MiddleName}</c:if> ${LastName}
				</span>.
			</h2>
			<h4 class="page-sub-title">
				Thanks for Choosing <span id="rotating-text">Cliniv</span>
			</h4>
			<a href="#" class="page-summary-info-switcher"><i
				class="fa fa-bars"></i></a>
		</div>
		<!-- <div class="l-col-lg-4 page-summary-info" style="margin-top:15px; text-align: center ">
			<img
				src="/REGENACIP/resources/landing_page/images/cipla.png"
				style="width:35%" />

		</div> 
		<div class="l-col-lg-4 page-summary-info" style="margin-top:15px ">
			<img
				src="/REGENACIP/resources/landing_page/images/brandLogo.png"
				style="width:65%" />

		</div> -->
	</div>
	<!-- slider -->
	<div class="l-spaced">
		<div class="row">
			<div class="col-md-12" style="text-align: center;">
				<h2 style="font-family: poppins; font-size: 30px;">Cliniv
					Application</h2>
			</div>
			<div class="col-md-12 text-center company-dash"></div>
			<div class="row">
				<div class="col-md-12 doctor-dash">
					<div class="col-md-12">
						<table style="width: 100%">
							<tr>
								<th>Study Name</th>
								<th><b>Demo Study</b></th>
							</tr>
							<tr>
								<td>Phase of The Study</td>
								<td><b> IV </b></td>
							</tr>
							<tr>
								<td>Protocol Number</td>
								<td><b> CLIN </b></td>
							</tr>
							<tr>
								<td>Therapeutic Area</td>
								<td><b> General Medicine </b></td>
							</tr>

						</table>
					</div>

				</div>
			</div>

			<div class="row" style="margin-top: 20px;">
				<div class="col-md-6 stretch-card grid-margin">
					<div class="card bg-gradient-danger card-img-holder text-white">
						<div class="card-body">
							
							<h4 class="font-weight-normal mb-3">Total Sites</h4>
							<i class="icon fa fa-hospital-o float-right"></i>
							<h2 class="mb-5">${reportModel.siteCount}</h2>
						</div>
					</div>
				</div>
				<div class="col-md-6 stretch-card grid-margin">
					<div class="card bg-gradient-sites card-img-holder text-white">
						<div class="card-body">
							
							<h4 class="font-weight-normal mb-3">
								Total Patients  </h4>
								<i class="fa fa-pencil-square-o float-right"></i>
							<h2 class="mb-5">${reportModel.patCount}</h2>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>
